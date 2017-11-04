package lab3;

import java.io.*;
import java.math.BigDecimal;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.function.BinaryOperator;

public class Main {

    public static BigDecimal computeSum(List<BigDecimal> BigDecimals){


        //BigDecimal sum = BigDecimal.ZERO;


        BigDecimal sum = BigDecimals.stream()
//                .reduce(BigDecimal.ZERO, (a, b) -> {
//                    a = a.add(b);
//                    return a;
//                });
                .reduce(BigDecimal.ZERO, BigDecimal::add);
        return sum;
    }

    public static void computeAverage(List<BigDecimal> BigDecimals){
        BigDecimal nrElements = new BigDecimal(String.valueOf(BigDecimals.size()));
        BigDecimal sum = computeSum(BigDecimals);
        System.out.println(sum.divide(nrElements));
    }

    public static void biggestTenPercent(List<BigDecimal> BigDecimals){
        int nrElementsToGet = BigDecimals.size() / 10;
        BigDecimals.stream()
                .sorted(Comparator.reverseOrder())
                .limit(nrElementsToGet)
                .forEach(System.out::println);
    }

    public static void main(String[] args) {
        int N = 1_000;
        List<BigDecimal> bigDecimals = new ArrayList<>();
        for(int i = 0 ; i < N; i++){
            bigDecimals.add(new BigDecimal(String.valueOf(i)));
        }

//        BigDecimal bigDecimal = BigDecimal.ZERO;
//        bigDecimal = bigDecimal.add(new BigDecimal("1.40"));
//        bigDecimal = bigDecimal.subtract(new BigDecimal("1.19"));
//        System.out.println(bigDecimal);
        //computeSum(BigDecimals);
        //computeAverage(BigDecimals);
        //biggestTenPercent(BigDecimals);


//        try (ObjectOutputStream oos = new ObjectOutputStream(new
//                FileOutputStream("/Users/Raul/Documents/toraCourse/ToraCourse/lab3/src/main/java/BigDecimals"))){
//            //oos.writeObject(usPresident);
//
//            BinaryOperator<BigDecimal> operator = ( BinaryOperator<BigDecimal> & Serializable) (a, b) -> {
//                a = a.add(b);
//                return a;
//            };
//            oos.writeObject(operator);
//
//
//
//
////            for(BigDecimal bigDecimal: bigDecimals) {
////                oos.writeObject(bigDecimal);
////            }
//        }
//        catch(FileNotFoundException fnfe) {
//            System.err.println("cannot create a file with the given file name ");
//        } catch(IOException ioe) {
//            System.out.println(ioe.getMessage());
//            System.err.println("an I/O error occurred while processing the file");
//        }



//        try(ObjectInputStream ois = new ObjectInputStream(new
//                FileInputStream("/Users/Raul/Documents/toraCourse/ToraCourse/lab3/src/main/java/BigDecimals"))){
//
//            BinaryOperator<BigDecimal> binaryOperator = (BinaryOperator<BigDecimal>) ois.readObject();
//
//
//            BigDecimal result = binaryOperator.apply(new BigDecimal("100"), new BigDecimal("50"));
//            System.out.println(result);
//
////            for(int  i = 0; i < N; i++) {
////                Object obj = ois.readObject();
////                BigDecimal bd = (BigDecimal)obj;
////                //System.out.println(bd);
////            }
//        }catch(FileNotFoundException fnfe) {
//            System.err.println("cannot create a file with the given file name ");
//        } catch(IOException ioe) {
//            System.err.println("an I/O error occurred while processing the file");
//
//        }
//        catch(ClassNotFoundException cnfe) {
//            System.err.println("Class not found");
//        }
    }
}
