package com.lab1;

import java.util.HashMap;
import java.util.Map;

public class Calculator {
    private static Map<String, BinaryOperation> operationsHashMap = new HashMap<>();
    static {
        for(BinaryOperation binaryOperation: BinaryOperation.values()){
            operationsHashMap.put(binaryOperation.getOperator(), binaryOperation);
        }
    }

    private static boolean isNumeric(String string){
        if(string.charAt(0) == '-')
            string = string.substring(1);
        return string.chars().allMatch(Character::isDigit);
    }

    public double calculate(String operation){
        String[] words = operation.split(" ");
        if(words.length == 2){ //we have a unary operation(SQRT is the only one in our case)
            double number = Double.parseDouble(words[1]);
            return Math.sqrt(number);
        }
        else{
            if(isNumeric(words[0])){ //binary operators like(+, -, /, *)
                double firstNumber = Double.parseDouble(words[0]);
                String operator = words[1];
                double secondNumber = Double.parseDouble(words[2]);
                return operationsHashMap.get(operator).apply(firstNumber, secondNumber);
            }
            else {//min or max operation between two numbers
                String operator = words[0];
                double firstNumber = Double.parseDouble(words[1]);
                double secondNumber = Double.parseDouble(words[2]);
                return operationsHashMap.get(operator).apply(firstNumber, secondNumber);
            }
        }
    }

}
