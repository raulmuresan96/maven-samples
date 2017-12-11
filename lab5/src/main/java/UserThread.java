
import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by Raul on 10/12/2017.
 */
public class UserThread implements Runnable {
    private int portNumber;
    private String username;
    private Map<String, Socket> connections;
    private ServerSocket serverSocket;

    public UserThread(int portNumber, String username){
        this.portNumber = portNumber;
        this.username = username;
        connections = new ConcurrentHashMap<>();
    }

    private void handleRequest(Socket socket){
        /*
        If a connection is established between the two sender and the receiver, the receiver is waiting fo messages from sender
         */
        try (PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
               BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()))){

            String inputLine = in.readLine();
            String sender = inputLine.split(" ")[1];
            out.println("!ack");
            while ((inputLine = in.readLine()) != null) {//it returns null when the socket becomes closed
                if(inputLine.equals("!bye")){//This connection will be closed and also if the opposite connection exists it will be closed
                    //socket.close();
                    Socket reverseSocket = connections.get(sender);
                    if(reverseSocket != null){
                        reverseSocket.close();
                        connections.remove(sender);
                    }
                    break;
                }
                System.out.println(username + " received from " + sender + " the following message: " + inputLine);
            }
            System.out.println(username + " reading from " + sender +  " has stopped");
            socket.close();
        }
        catch (SocketException exception){
            System.out.println("Socket exception");
        }
        catch (IOException exception){
            System.out.println("Could not open connection");
        }
    }

    private void handleWrite(PrintWriter out, String receiver){
        Random random = new Random();
        double randomNr = random.nextDouble();
        if(randomNr < 0.2){ // 20% chances to close the current connection and 80% to print normal messages
            //System.out.println("BYE");
            out.println("!bye");
            connections.remove(receiver);
        }
        else{
            out.println("Salut");
            out.println("Ce faci?");
        }
    }

    @Override
    public void run() {
        ExecutorService executorService = Executors.newCachedThreadPool();
            executorService.execute(()->{ //every user has a thread which send messages to othe rusers
                for(int i = 0; i < 10; i++) {
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    if(ConnectionManager.getUsers().size() <= 1){
                        break;
                    }
                    String receiver = ConnectionManager.getRandomUser(username);
                    if(receiver == null)
                        break;
                    if (connections.get(receiver) == null) {// we do not have a connection yet
                        System.out.println("Initializing a new connection between " + username + " and " + receiver);
                        int userPort = ConnectionManager.getPortForUser(receiver);
                        if(userPort == -1)//
                            continue;
                        Socket socket = null;
                        try {
                            socket = new Socket("127.0.0.1", userPort);
                            PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
                            BufferedReader in = new BufferedReader(
                                    new InputStreamReader(socket.getInputStream()));
                            connections.put(receiver, socket);
                            out.println("!Hello " + username);
                            String response = in.readLine();
                            if (response.equals("!ack")) {

                                //System.out.println("connection esblished");
                                handleWrite(out, receiver);
                            }
                            else{
                                connections.remove(receiver);
                            }

                            System.out.println(username + " " + connections);
                        } catch (IOException e) {
                            System.out.println("Connection to " + receiver + " refused");
                        }

                    } else {
                        Socket socket = connections.get(receiver);

                        try {
                            PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
                            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                            handleWrite(out, receiver);
                        } catch (IOException exception) {
                            System.out.println(exception.getMessage());
                        }
                    }
                }
                try {
                    serverSocket.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                ConnectionManager.logOut(username);

                //simulating a !byebye signal from the current user
                connections.forEach((key, value) ->{
                    try (PrintWriter writer = new PrintWriter(value.getOutputStream(), true)){
                        //try with resources closes the writer and if the write is closed, the socket is closed also
                        writer.println("!bye");
                    }
                    catch (IOException execption){
                        System.out.println("Connection was already closed");
                    }
                });
                connections.clear();

                System.out.println(username + " writer has finished");
            });

        try {
            serverSocket = new ServerSocket(portNumber);
            System.out.println("User " + username + " has logged in!");
            try {
               while (true){
                    //System.out.println("ajunge la accept");
                    Socket clientSocket = serverSocket.accept();
                    Runnable task = () -> handleRequest(clientSocket);
                    executorService.execute(task);
                }
            }
            catch (SocketException socketException){
                System.out.println("Server " + username + " has been stopped");
            }
        }
        catch (IOException exception){

            System.out.println("EXCEPTIE" + exception.getMessage());
            System.out.println("Server could not be started");
        }
        executorService.shutdown();

    }
}
