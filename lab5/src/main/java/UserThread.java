
import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by Raul on 10/12/2017.
 */
public class UserThread implements Runnable {
    private int portNumber;
    private String username;
    private ServerSocket serverSocket;
    private Set<Socket> acceptedConnections = Collections.synchronizedSet(new HashSet<>());
    private static final int threadCount = 6;
    private final MyRandom random;


    public UserThread(int portNumber, String username){
        this.portNumber = portNumber;
        this.username = username;
        random = new MyRandom();
    }

    private void handleRequest(Socket socket){
        /*
        If a connection is established between the two sender and the receiver, the receiver is waiting for messages from sender
         */
        try {
            PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            String inputLine = in.readLine();
            if(!inputLine.substring(0, 7).equals("!Hello ")){//checks if the response message respects the protocol
                acceptedConnections.remove(socket);
                return;
            }
            String sender = inputLine.split(" ")[1];
            out.println("!ack");
            while ((inputLine = in.readLine()) != null) {//it returns null when the socket becomes closed
                if(inputLine.equals("!bye")){
                    break;
                }
                else if(inputLine.equals("!byebye")){
                    //if a user receives a !byebye signal, both connections will be removed from the ConnectionManager
                    ConnectionManager.endConnection(username, sender);
                    break;
                }
                System.out.println(username + " received from " + sender + " the following message: " + inputLine);
            }
            acceptedConnections.remove(socket);
            ConnectionManager.endConnection(sender, username);
            System.out.println(username + " reading from " + sender +  " has stopped");
        }
        catch (SocketException exception){//
           System.out.println("Socket has been already closed");
            //System.out.println(exception.getMessage());
        }
        catch (IOException exception){
            System.out.println("Could not open connection");
        }
    }

    private void handleWrite(PrintWriter out, String receiver, Socket socket){
        double randomNr = random.generateDouble();
        if(randomNr < 0.2){ // 20% chances to close the current connection and 80% to print normal messages
            out.println("!bye");
            ConnectionManager.endConnection(username, receiver);
            try {
                socket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        else{
            out.println("Salut");
            out.println("Ce faci?");
        }
    }

    @Override
    public void run() {
        ExecutorService executorService = Executors.newFixedThreadPool(threadCount);
            executorService.execute(()->{ //every user has a thread which send messages to other users
                for(int i = 0; i < random.generateInt(20); i++) {
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    if(ConnectionManager.getUsersCount() <= 1){//there is no user to chat with
                        break;
                    }
                    String receiver = ConnectionManager.getRandomUser(username);
                    if(receiver == null)
                        break;
                    Socket socket = ConnectionManager.checkExistingConnection(username, receiver);
                    if (socket == null) {// there is no connection yet and will try to initialize one
                        System.out.println("Initializing a new connection between " + username + " and " + receiver);
                        try {
                            socket = ConnectionManager.startConnection(username, receiver);
                            if (socket == null){
                                continue;
                            }
                            PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
                            BufferedReader in = new BufferedReader(
                                    new InputStreamReader(socket.getInputStream()));
                            out.println("!Hello " + username);
                            String response = in.readLine();
                            if (response != null && response.equals("!ack")) {
                                handleWrite(out, receiver, socket);
                            }
                            else{
                                ConnectionManager.endConnection(username, receiver);
                            }
                        } catch (IOException e) {
                            System.out.println("Connection to " + receiver + " refused");
                        }
                    } else {// there is an already opened connection between sender and receiver
                        try {
                            PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
                            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                            handleWrite(out, receiver, socket);
                        } catch (IOException exception) {
                            ConnectionManager.endConnection(username, receiver);
                            System.out.println(exception.getMessage());
                        }
                    }
                }
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                try {
                    serverSocket.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                Map<String, Socket> toCloseConnections = ConnectionManager.logOut(username);
                System.out.println("Accepted connections " + acceptedConnections.size());
                //simulating a !byebye signal from the current user
                //every connection active where the current user is the receiver will be closed;
                acceptedConnections.forEach(connection ->{
                    try {
                        connection.close();
                    } catch (IOException e) {
                        System.out.println("Connection was already closed");
                    }
                });
                acceptedConnections.clear();
                //all the connections where the current user is the sender will be closed
                toCloseConnections.forEach((key, value) ->{
                    try {
                        PrintWriter writer = new PrintWriter(value.getOutputStream(), true);
                        writer.println("!byebye");
                    }
                    catch (IOException execption){
                        System.out.println("Socket is currently closed");
                    }
                    finally {
                        try {//closing the socket and removing connection from ConnectionManager
                            ConnectionManager.endConnection(username, key);
                            value.close();
                        } catch (IOException e) {
                            System.out.println(e.getMessage());
                        }
                    }
                });
                System.out.println(username + " writer has finished");
            });

        try {
            serverSocket = new ServerSocket(portNumber);
            System.out.println("User " + username + " has logged in!");
            try {
               while (true){
                    Socket clientSocket = serverSocket.accept();
                    acceptedConnections.add(clientSocket);
                    Runnable task = () -> handleRequest(clientSocket);
                    executorService.execute(task);
                }
            }
            catch (SocketException socketException){
                System.out.println("Server " + username + " has been stopped");
            }
        }
        catch (IOException exception){
            System.out.println("Server could not be started");
        }
        executorService.shutdown();
    }
}
