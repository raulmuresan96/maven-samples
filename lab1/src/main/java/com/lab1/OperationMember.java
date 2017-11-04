package com.lab1;

public class OperationMember{
    private double firstNumber;
    private double secondNumber;
    private String operator;

    public OperationMember(double firstNumber, double second, String operator) {
        this.firstNumber = firstNumber;
        this.secondNumber = second;
        this.operator = operator;
    }

    public double getFirstNumber() {
        return firstNumber;
    }

    public void setFirstNumber(double firstNumber) {
        this.firstNumber = firstNumber;
    }

    public double getSecond() {
        return secondNumber;
    }

    public void setSecond(double second) {
        this.secondNumber = second;
    }

    public String getOperator() {
        return operator;
    }

    public void setOperator(String operator) {
        this.operator = operator;
    }
}