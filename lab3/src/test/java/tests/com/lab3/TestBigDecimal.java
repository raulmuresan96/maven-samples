package tests.com.lab3;
import lab3.BigDecimalOperations;
import lab3.MyBigDecimal;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;


import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;
import java.util.stream.LongStream;

import static org.hamcrest.Matchers.*;
import static org.junit.Assert.assertThat;

/**
 * Created by Raul on 04/11/2017.
 */
public class TestBigDecimal {

    private List<BigDecimal> bigDecimals = new ArrayList<>();
    private String fileName = "src/main/java/lab3/BigDecimals";
    private final int nrCount = 100;

    @Before
    public void setup() {
        IntStream.rangeClosed(1, nrCount)
                .forEach(i -> {
                    bigDecimals.add(new BigDecimal(String.valueOf(i)));
                });


    }

    @Test
    public void testAdd(){
        BigDecimalOperations.computeSum(bigDecimals);
        assertThat(BigDecimalOperations.computeSum(bigDecimals),is( new BigDecimal("5050")));
    }

    @Test
    public void testAverage(){
        BigDecimalOperations.computeAverage(bigDecimals);
        assertThat( BigDecimalOperations.computeAverage(bigDecimals) ,is( new BigDecimal("50.5")));
    }

    @Test
    public void testBiggestTenPercent(){
        List<BigDecimal> tenPercentBigDecimals = BigDecimalOperations.biggestTenPercent(bigDecimals);
        IntStream.range(0, tenPercentBigDecimals.size())
                .forEach(i ->{
                    assertThat( tenPercentBigDecimals.get(i), is( new BigDecimal(String.valueOf(nrCount - i))));
                });
    }

    @Test
    public void testSerialization() {
        BigDecimalOperations.serialize(fileName, bigDecimals);
        List<BigDecimal> readFromFileBigDecimals = BigDecimalOperations.deserialize(fileName, nrCount);
        IntStream.range(0, nrCount)
                .forEach(i -> {
                    assertThat( readFromFileBigDecimals.get(i) ,is( new BigDecimal(String.valueOf(i + 1))  ));

                });
    }

}
