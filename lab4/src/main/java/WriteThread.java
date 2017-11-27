import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;

/**
 * Created by Raul on 21/11/2017.
 */
public class WriteThread implements Runnable {
    private volatile boolean  finishedRead = false;
    private BlockingQueue<Person> blockingQueue;
    private String fileName;


    public WriteThread(String fileName, BlockingQueue<Person> blockingQueue){
        this.blockingQueue = blockingQueue;
        this.fileName = fileName;
    }

    public void setFinishRead(){
        finishedRead = true;
    }

    @Override
    public void run() {

        try (BufferedWriter br = new BufferedWriter(new FileWriter(fileName))){

            while (!finishedRead || blockingQueue.size() > 0){
                try {
                    Person person = blockingQueue.poll(500, TimeUnit.MILLISECONDS);
                    if(person != null){
                        br.write(person.toString() + "\n");
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
        catch (IOException ioException){
            System.out.println(ioException);
        }

    }
}
