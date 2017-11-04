package com.lab1;

interface SimpleOperation{
    double apply(double x);
}

public enum UnaryOperation implements SimpleOperation {
    SQRT("sqrt"){public double apply(double x){return Math.sqrt(x);} };

    private String operator;
    UnaryOperation(String operator){
        this.operator = operator;
    }

    public String getOperator(){
        return operator;
    }
}
