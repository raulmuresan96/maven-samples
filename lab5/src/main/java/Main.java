import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by Raul on 10/12/2017.
 */
public class Main {
    public static void main(String[] args) {

        int nrUsers = args.length;
        ExecutorService executorService = Executors.newFixedThreadPool(nrUsers);
        final int[] portNumber = {1024};
        List<String> users = new ArrayList<>();
        Map<String, Integer> userPorts = new HashMap<>();
        Arrays.stream(args)
                .forEach((value) ->{
                    users.add(value);
                    userPorts.put(value, portNumber[0]);
                    executorService.execute(new UserThread(portNumber[0]++, value));
                });
        ConnectionManager.initializeUsers(userPorts, users);
        executorService.shutdown();
    }
}
