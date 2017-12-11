import lab3.MyBigDecimal;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.math.BigDecimal;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.BlockingQueue;
import java.util.stream.IntStream;

/**
 * Created by Raul on 27/11/2017.
 */
public class ProducerBigDecimal implements Runnable{

    private String fileName;
    private BlockingQueue<MyBigDecimal> blockingQueue;
    private int nrElements = 10_000_000;

    public ProducerBigDecimal(String fileName, BlockingQueue<MyBigDecimal> blockingQueue){
        this.fileName = fileName;
        this.blockingQueue = blockingQueue;
    }

    @Override
    public void run() {


        try(ObjectInputStream ois = new ObjectInputStream(new FileInputStream(fileName))){
            IntStream.rangeClosed(1, nrElements)
                    .forEach(i ->{
                        MyBigDecimal bd = null;
                        try {
                            bd = MyBigDecimal.deserialize(ois); //(MyBigDecimal)ois.readObject();
                            //System.out.println(bd);
                        } catch (IOException | ClassNotFoundException e) {
                            e.printStackTrace();
                        }
                        try {
                            blockingQueue.put(bd);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    });
        }catch(FileNotFoundException fnfe) {
            System.err.println("cannot create a file with the given file name ");
        } catch(IOException ioe) {
            System.err.println("an I/O error occurred while processing the file");
        }
        try {
            blockingQueue.put(new MyBigDecimal("-1"));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        ;


    }
}
