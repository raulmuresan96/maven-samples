package com.lab1;

import org.junit.Before;
import org.junit.Test;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;


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
        assertThat(expectedResult, is(calculator.calculate(operation)));
    }

    @Test
    public void testSubstraction() {
        String operation = "-1 - 2";
        double expectedResult = -3.0;
        assertThat(expectedResult, is(calculator.calculate(operation)));
    }

    @Test
    public void testMultiplication() {
        String operation = "2 * 5";
        double expectedResult = 10.0;
        assertThat(expectedResult, is(calculator.calculate(operation)));
    }

    @Test
    public void testDivision() {
        String operation = "3 / 6";
        double expectedResult = 0.5;
        assertThat(expectedResult, is(calculator.calculate(operation)));
    }

    @Test
    public void testMaximum() {
        String operation = "max 5 6";
        double expectedResult = 6.0;
        assertThat(expectedResult, is(calculator.calculate(operation)));
    }

    @Test
    public void testMinimum() {
        String operation = "min -5 6";
        double expectedResult = -5.0;
        assertThat(expectedResult, is(calculator.calculate(operation)));
    }
    @Test
    public void testSqrt() {
        String operation = "sqrt 9";
        double expectedResult = 3.0;
        assertThat(expectedResult, is(calculator.calculate(operation)));
    }
}
