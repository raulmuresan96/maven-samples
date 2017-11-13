package lab3;

import java.io.*;
import java.math.BigDecimal;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Random;
import java.util.function.BinaryOperator;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * Created by Raul on 03/11/2017.
 */
public class BigDecimalOperations {
    private static Random random = new Random();


    private static void serializeLambdaExpression(){
        BinaryOperator<BigDecimal> operator = ( BinaryOperator<BigDecimal> & Serializable) (a, b) -> {
                a = a.add(b);
                return a;
        };

        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("src/main/java/lab3/LambdaSerialization"))){
            oos.writeObject(operator);
        }
        catch(FileNotFoundException fnfe) {
            System.err.println("cannot create a file with the given file name ");
        } catch(IOException ioe) {
            System.err.println("an I/O error occurred while processing the file");
        }
    }

    private static BinaryOperator<BigDecimal> deserializeLambdaExpression(){
        BinaryOperator<BigDecimal> bigDecimalOperator = null;
        try(ObjectInputStream ois = new ObjectInputStream(new FileInputStream("src/main/java/lab3/LambdaSerialization"))){
            bigDecimalOperator = (BinaryOperator<BigDecimal>) ois.readObject();
        }
        catch(FileNotFoundException fnfe) {
            System.err.println("cannot create a file with the given file name ");
        }
        catch(IOException ioe) {
            System.err.println("an I/O error occurred while processing the file");
        }
        catch(ClassNotFoundException cnfe) {
            System.err.println("Class not found");
        }
        return bigDecimalOperator;
    }


    private static BinaryOperator<BigDecimal> getAdditionLambdaExpression(){
        serializeLambdaExpression();
        return deserializeLambdaExpression();
    }

    public static BigDecimal computeSum(List<BigDecimal> BigDecimals){
        BinaryOperator<BigDecimal> operator = getAdditionLambdaExpression();
        return  BigDecimals.stream()
                .reduce(BigDecimal.ZERO, operator);
    }

    public static BigDecimal computeAverage(List<BigDecimal> BigDecimals){
        BigDecimal nrElements = new BigDecimal(String.valueOf(BigDecimals.size()));
        BigDecimal sum = computeSum(BigDecimals);

        return sum.divide(nrElements);
    }

    public static List<BigDecimal> biggestTenPercent(List<BigDecimal> BigDecimals){
        int nrElementsToGet = BigDecimals.size() / 10;

        return BigDecimals.stream()
                        .sorted(Comparator.reverseOrder())
                        .limit(nrElementsToGet)
                        .collect(Collectors.toList())
                ;

    }


    public static void serialize(String fileName, List<BigDecimal> bigDecimals){

        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(fileName))){

            bigDecimals.forEach(bigDecimal ->{
                try {
                    oos.writeObject(bigDecimal);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            });

        }
        catch(FileNotFoundException fnfe) {
            System.err.println("cannot create a file with the given file name ");
        } catch(IOException ioe) {
            System.err.println("an I/O error occurred while processing the file");
        }
    }


    public static List<BigDecimal> deserialize(String fileName, int nrElements){
        List<BigDecimal> bigDecimals = new ArrayList<>();
        try(ObjectInputStream ois = new ObjectInputStream(new FileInputStream(fileName))){
            IntStream.rangeClosed(1, nrElements)
                    .forEach(i ->{

                        BigDecimal bd = null;
                        try {
                            bd = (BigDecimal)ois.readObject();
                        } catch (IOException | ClassNotFoundException e) {
                            e.printStackTrace();
                        }
                        bigDecimals.add(bd);
                    });

        }catch(FileNotFoundException fnfe) {
            System.err.println("cannot create a file with the given file name ");
        } catch(IOException ioe) {
            System.err.println("an I/O error occurred while processing the file");
        }

        return bigDecimals;
    }

    public static String generateBigDecimal(){
        int length = 50 + random.nextInt(50);
        StringBuilder stringBuilder = new StringBuilder();
        IntStream.range(0, length)
                .forEach(i -> stringBuilder.append(random.nextInt(10)));

        return stringBuilder.toString();
    }


}
