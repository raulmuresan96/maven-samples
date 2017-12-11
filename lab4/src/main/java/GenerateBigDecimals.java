import lab3.BigDecimalOperations;
import lab3.MyBigDecimal;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.math.BigDecimal;
import java.time.Duration;
import java.util.stream.LongStream;

/**
 * Created by Raul on 27/11/2017.
 */
public class GenerateBigDecimals {
    public static void main(String[] args) {
        int nrCount = 10_000_000;
        String fileName = "../ToraCourse/lab4/src/main/resources/BigDecimals";

        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(fileName))){
            int counter = 0;
            LongStream.rangeClosed(1, nrCount)
                    .forEach(value -> {
                        String number = BigDecimalOperations.generateBigDecimal();
                        try {
                            oos.writeUnshared(new MyBigDecimal(number));
                            oos.reset();
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
}
