import java.util.Random;

/**
 * Created by Raul on 07/01/2018.
 */
public class MyRandom {
    private final Random random;
    public MyRandom(){
        random = new Random();
    }

    public synchronized int generateInt(int upperBound){
        return random.nextInt(upperBound);
    }

    public synchronized double generateDouble(){
        return random.nextDouble();
    }

}
