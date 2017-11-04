package tests.com.lab3;
import lab3.BigDecimalOperations;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;


import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.Matchers.*;
import static org.junit.Assert.assertThat;

/**
 * Created by Raul on 04/11/2017.
 */
public class TestBigDecimal {

    private List<BigDecimal> bigDecimals = new ArrayList<>();
    private String fileName = "src/main/java/lab3/BigDecimals";

    @Before
    public void setup() {
        for(int i = 1; i <= 100; i++){
            bigDecimals.add(new BigDecimal(String.valueOf(i)));
        }
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
        List<BigDecimal> bigIntegers = BigDecimalOperations.biggestTenPercent(bigDecimals);
        int nr = 100;
        for(BigDecimal bigDecimal: bigIntegers){
            assertThat( bigDecimal, is( new BigDecimal(String.valueOf(nr))));
            nr--;
        }
    }

    @Test
    public void testSerialization() {
        BigDecimalOperations.serialize(fileName, bigDecimals);
        List<BigDecimal> readFromFileBigDecimals = BigDecimalOperations.deserialize(fileName);
        for(int i = 0; i < 100; i++){
            assertThat( readFromFileBigDecimals.get(i) ,is( new BigDecimal(String.valueOf(i + 1))  ));
        }
    }

}
