import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

/**
 * Created by Raul on 10/12/2017.
 */
public class ConnectionManager {
    private static Map<String, Integer> userPorts;
    private static List<String> users;
    private static Random random;

    public static void initializeUsers(Map<String, Integer> userPorts, List<String> users){
        ConnectionManager.userPorts = userPorts;
        ConnectionManager.users = users;
        random = new Random();
    }

    public synchronized static int getPortForUser(String username){
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

    public synchronized static void logOut(String username){
        userPorts.remove(username);
        users.remove(username);
    }


    public synchronized static List<String> getUsers() {
        return users;
    }

    public synchronized static Map<String, Integer> getUserPorts() {
        return userPorts;
    }
}
