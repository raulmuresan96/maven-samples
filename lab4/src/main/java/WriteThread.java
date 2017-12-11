import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;

/**
 * Created by Raul on 21/11/2017.
 */
public class WriteThread<T> implements Runnable {
    private volatile boolean  finishedRead = false;
    private BlockingQueue<T> blockingQueue;
    private String fileName;


    public WriteThread(String fileName, BlockingQueue<T> blockingQueue){
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
                    T entity = blockingQueue.poll(500, TimeUnit.MILLISECONDS);
                    //System.out.println(entity);
                    if(entity != null){
                        br.write(entity.toString() + "\n");
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
