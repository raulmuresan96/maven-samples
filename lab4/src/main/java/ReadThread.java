import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.BlockingQueue;

/**
 * Created by Raul on 21/11/2017.
 */
public class ReadThread implements Runnable {

    private String fileName;
    private BlockingQueue<Person> blockingQueue;

    public ReadThread(String fileName, BlockingQueue<Person> blockingQueue){
        this.fileName = fileName;
        this.blockingQueue = blockingQueue;
    }

    private boolean validatePerson(String[] fields){
        if(fields.length != 5 ||
                !Person.verifyName(fields[0]) || !Person.verifyName(fields[1]) || !Person.verifyName(fields[2]) ||
                !Person.verifyCNP(fields[3]) ||
                !Person.verifyEmail(fields[4])
                ) {
            return false;
        }
        return true;
    }

    private Person createPerson(String[] fields){
        List<String> nameList = new ArrayList<>();
        nameList.add(fields[0]);
        nameList.add(fields[1]);
        nameList.add(fields[2]);
        return new Person(nameList, fields[3], fields[4]);

    }

    private void createPersonsList(String text) {
        String[] strings = text.split("%");
        for(String line: strings){
            String[] fields = line.split("~");
            if(validatePerson(fields)){
                try {
                    //System.out.println("producer");
                    blockingQueue.put(createPerson(fields));
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    @Override
    public void run() {
        try {
            String text = new String(Files.readAllBytes(Paths.get(fileName)));
            createPersonsList(text);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
