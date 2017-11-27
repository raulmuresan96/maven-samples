import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingDeque;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingDeque;

/**
 * Created by Raul on 14/11/2017.
 */
public class Main {


    public static void main(String[] args) throws IOException {

        BlockingQueue<Person> blockingQueue = new LinkedBlockingDeque<>();
        ReadThread readThread = new ReadThread("../ToraCourse/lab4/src/main/resources/input4.txt", blockingQueue);
        WriteThread writeThread = new WriteThread("../ToraCourse/lab4/src/main/resources/validPersons.txt", blockingQueue);

       Thread thread1 = new Thread(readThread);
       Thread thread2 = new Thread(writeThread);

       thread1.start();
       thread2.start();

        try {
            thread1.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        writeThread.setFinishRead();
        try {
            thread2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }







//        String text = new String(Files.readAllBytes(Paths.get("../ToraCourse/lab4/src/main/resources/input4.txt")));
//        createPerson(text).forEach((value) -> {
//            System.out.println(value);
//        });

    }
}
