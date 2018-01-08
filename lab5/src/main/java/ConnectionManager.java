import java.io.IOException;
import java.net.Socket;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by Raul on 10/12/2017.
 */
public class ConnectionManager {
    private static Map<String, Integer> userPorts;
    private static List<String> users;//used to store all active users and also get a random user easily
    private static Random random;
    private static Map<String, Map<String, Socket>> connections;

    public static void initializeUsers(Map<String, Integer> userPorts, List<String> users){
        ConnectionManager.userPorts = new ConcurrentHashMap<>(userPorts);
        ConnectionManager.users = users;
        random = new Random();
        connections = new ConcurrentHashMap<>();
        users.forEach((user) -> connections.put(user, new ConcurrentHashMap<>()));
    }

    public static int getPortForUser(String username){
        Integer nr = userPorts.get(username);
        if(nr == null)
            nr = -1;
        return nr;
    }

    public synchronized static String getRandomUser(String currentUser){//returns a random user(different from currentUser) to chat with
        if(users.size() <= 1)
            return null;
        String user = currentUser;
        while (user.equals(currentUser)){
            int nr = random.nextInt(users.size());
            if(nr < 0)
                nr = -nr;
            user = users.get(nr);
        }
        return user;
    }

    public static void endConnection(String sender, String receiver){
        Map<String, Socket> stringSocketMap = connections.get(sender);
        if(stringSocketMap != null){
            stringSocketMap.remove(receiver);
        }
    }

    public static Socket startConnection(String sender, String receiver) throws IOException {
        int userPort = getPortForUser(receiver);
        if(userPort == -1) // user has logged out meanwhile
            return null;
        Socket socket = null;
        socket = new Socket("127.0.0.1", userPort);
        connections.get(sender).put(receiver, socket);//add a new connection for the sender
        return socket;
    }

    public static Socket checkExistingConnection(String sender, String receiver){
        return connections.get(sender).get(receiver);
    }

    public synchronized static Map<String, Socket> logOut(String username){
        synchronized (ConnectionManager.class){
            users.remove(username);
        }
        userPorts.remove(username);
        return connections.get(username);

    }

    public synchronized static List<String> getUsers() {
        return users;
    }
    public synchronized static int getUsersCount() {
        return users.size();
    }

    public synchronized static Map<String, Integer> getUserPorts() {
        return userPorts;
    }
}
