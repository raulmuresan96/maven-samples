import lab3.MyBigDecimal;

import java.io.*;
import java.math.BigDecimal;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;

/**
 * Created by Raul on 11/12/2017.
 */
public class ConsumerBigDecimal implements Runnable {
    private String fileName;
    private BlockingQueue<MyBigDecimal> blockingQueue;
    private BigDecimal poison = new BigDecimal("-1");

    public ConsumerBigDecimal(String fileName, BlockingQueue<MyBigDecimal> blockingQueue){
        this.fileName = fileName;
        this.blockingQueue = blockingQueue;
    }

    @Override
    public void run() {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(fileName))){
            while (true){
                try {
                    MyBigDecimal myBigDecimal = blockingQueue.take();
                    if(myBigDecimal.getBigDecimal().equals(poison)){
                        break;
                    }
                    //System.out.println(myBigDecimal);
                    oos.writeUnshared(myBigDecimal);
                    oos.reset();

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
