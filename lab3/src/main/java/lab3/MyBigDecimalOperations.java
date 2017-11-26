package lab3;

import java.io.*;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

/**
 * Created by Raul on 12/11/2017.
 */
public class MyBigDecimalOperations {

    public static void serialize(String fileName, List<MyBigDecimal> bigDecimals){

        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(fileName))){
            IntStream.range(0, bigDecimals.size())
                    .forEach(i -> {
                        try {
                            oos.writeObject(bigDecimals.get(i));
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

    public static List<MyBigDecimal> deserialize(String fileName, int nrElements){
        List<MyBigDecimal> bigDecimals = new ArrayList<>();
        try(ObjectInputStream ois = new ObjectInputStream(new FileInputStream(fileName))){
            IntStream.rangeClosed(1, nrElements)
                    .forEach(i ->{
                        MyBigDecimal bd = null;
                        try {
                            bd = MyBigDecimal.deserialize(ois); //(MyBigDecimal)ois.readObject();
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
}
