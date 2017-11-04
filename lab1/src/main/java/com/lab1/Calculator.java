package com.lab1;

import java.util.HashMap;
import java.util.Map;


public class Calculator {
    private static Map<String, BinaryOperation> operationsHashMap = new HashMap<>();
    private static Map<String, UnaryOperation> unaryOperationsHashMap = new HashMap<>();
    static {
        for(BinaryOperation binaryOperation: BinaryOperation.values()){
            operationsHashMap.put(binaryOperation.getOperator(), binaryOperation);
        }
        for(UnaryOperation unaryOperation: UnaryOperation.values()){
            unaryOperationsHashMap.put(unaryOperation.getOperator(),unaryOperation);
        }
    }

    private static boolean isNumeric(String string){
        if(string.charAt(0) == '-')
            string = string.substring(1);
        return string.chars().allMatch(Character::isDigit);
    }

    private OperationMember procesString(String[] words){
        if(isNumeric(words[0])){ //binary operators like(+, -, /, *)
            return new OperationMember( Double.parseDouble(words[0]), Double.parseDouble(words[2]), words[1]);

        }
        else {//min or max operation between two numbers
            return new OperationMember( Double.parseDouble(words[1]), Double.parseDouble(words[2]), words[0]);
        }
    }

    public double calculate(String operation){
        String[] words = operation.split(" ");
        if(words.length == 2){ //we have a unary operation(SQRT is the only one in our case)
            double number = Double.parseDouble(words[1]);
            String operator = words[0];
            return unaryOperationsHashMap.get(operator).apply(number);
        }
        else{
            OperationMember operationMember = procesString(words);
            return operationsHashMap.get(operationMember.getOperator()).apply(operationMember.getFirstNumber(), operationMember.getSecond());
        }
    }
}
