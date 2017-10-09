package com.lab1;

import com.lab1.Calculator;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.assertEquals;



public class TestCalculator {

    private Calculator calculator;

    @Before
    public void setup() {
        calculator = new Calculator();
    }

    @Test
    public void testAddition() {
        String operation = "1 + 2";
        double expectedResult = 3.0;
        assertEquals(expectedResult, calculator.calculate(operation),0D);
        //third parameter is for precision, two parameter method is deprecated
    }

    @Test
    public void testSubstraction() {
        String operation = "1 - 2";
        double expectedResult = -1.0;
        assertEquals(expectedResult, calculator.calculate(operation),0D);
    }

    @Test
    public void testMultiplication() {
        String operation = "2 * 5";
        double expectedResult = 10.0;
        assertEquals(expectedResult, calculator.calculate(operation), 0D);
    }

    @Test
    public void testDivision() {
        String operation = "3 / 6";
        double expectedResult = 0.5;
        assertEquals(expectedResult, calculator.calculate(operation), 0D);
    }

    @Test
    public void testMaximum() {
        String operation = "max 5 6";
        double expectedResult = 6.0;
        assertEquals(expectedResult, calculator.calculate(operation), 0D);
    }

    @Test
    public void testMinimum() {
        String operation = "min 5 6";
        double expectedResult = 5.0;
        assertEquals(expectedResult, calculator.calculate(operation), 0D);
    }
    @Test
    public void testSqrt() {
        String operation = "sqrt 9";
        double expectedResult = 3.0;
        assertEquals(expectedResult, calculator.calculate(operation), 0D);
    }


}
