import lab3.MyBigDecimal;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingDeque;

/**
 * Created by Raul on 27/11/2017.
 */
public class ProducerConsumerBigDecimal {
    public static void main(String[] args) {
        BlockingQueue<MyBigDecimal> blockingQueue = new LinkedBlockingDeque<>();
        ProducerBigDecimal producer = new ProducerBigDecimal("../ToraCourse/lab4/src/main/resources/BigDecimals", blockingQueue);
        ConsumerBigDecimal consumer = new ConsumerBigDecimal("../ToraCourse/lab4/src/main/resources/ConsumedBigDecimals", blockingQueue);

        Thread thread1 = new Thread(producer);
        Thread thread2 = new Thread(consumer);

        thread1.start();
        thread2.start();

        try {
            thread1.join();
            thread2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }
}
