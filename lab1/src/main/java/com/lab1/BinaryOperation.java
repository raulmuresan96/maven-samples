package com.lab1;

interface Operation{
   double apply(double x, double y);
}

public enum BinaryOperation implements Operation{
    PLUS("+"){public double apply(double x, double y){return x + y;} },
    MINUS("-"){public double apply(double x, double y){return x - y;} },
    TIMES("*"){public double apply(double x, double y){return x * y;} },
    DIVIDE("/"){public double apply(double x, double y){return x / y;} },
    MAXIMUM("max"){public double apply(double x, double y){return Double.max(x, y);} },
    MINIMUM("min"){public double apply(double x, double y){return Double.min(x, y);} };

    private String operator;
    BinaryOperation(String operator){
        this.operator = operator;
    }

    public String getOperator(){
        return operator;
    }

}