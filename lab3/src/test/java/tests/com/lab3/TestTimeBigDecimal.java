package tests.com.lab3;

import lab3.BigDecimalOperations;
import lab3.MyBigDecimal;
import lab3.MyBigDecimalOperations;
import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.stream.IntStream;
import java.util.stream.LongStream;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

/**
 * Created by Raul on 12/11/2017.
 */
public class TestTimeBigDecimal {


    private List<BigDecimal> bigDecimals = new ArrayList<>();
    private List<MyBigDecimal> customBigDecimals = new ArrayList<>();
    private String fileName = "src/main/java/lab3/LargeBigDecimals";
    private String fileNameMyBigDecimal = "src/main/java/lab3/MyBigDecimals";
    private final int nrCount = 1_000_000;



    @Before
    public void setup() {
        LongStream.rangeClosed(1, nrCount)
                .forEach(value -> {
                    String number = BigDecimalOperations.generateBigDecimal();
                    bigDecimals.add(new BigDecimal(number));
                    customBigDecimals.add(new MyBigDecimal(number));
                });
    }



    @Test
    public void testMyBigDecimal(){
        long startTime =  System.nanoTime();
        MyBigDecimalOperations.serialize(fileNameMyBigDecimal, customBigDecimals);

        List<MyBigDecimal> deserializedlist = MyBigDecimalOperations.deserialize(fileNameMyBigDecimal,nrCount);

        long timeElapsed = System.nanoTime() - startTime;
        System.out.println("MyBigDecimal: Elapsed time in Milliseconds: " + TimeUnit.NANOSECONDS.toMillis(timeElapsed));

        IntStream.range(0, deserializedlist.size())
                .forEach(i -> {
                    assertThat( deserializedlist.get(i).getBigDecimal() ,is( customBigDecimals.get(i).getBigDecimal()));
                });
    }

    @Test
    public void testBigDecimal(){
        long startTime =  System.nanoTime();
        BigDecimalOperations.serialize(fileName, bigDecimals);

        List<BigDecimal> deserializedlist = BigDecimalOperations.deserialize(fileName,nrCount);

        long timeElapsed = System.nanoTime() - startTime;
        System.out.println("Standard BigDecimal: Elapsed time in Milliseconds: " + TimeUnit.NANOSECONDS.toMillis(timeElapsed));

        IntStream.range(0, deserializedlist.size())
                .forEach(i -> {
                    assertThat( deserializedlist.get(i) ,is( bigDecimals.get(i)));
                });
    }


}
