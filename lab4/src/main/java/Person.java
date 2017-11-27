import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

/**
 * Created by Raul on 14/11/2017.
 */
public class Person {

    public static boolean verifyName(String name){
        return name.matches("^[a-zA-Z]+$");
    }

    public static boolean verifyCNP(String name){
        return name.matches("^^[1,2,5,6]{1}[0-9]{2}([0]{1}[1-9]{1}|[1]{1}[0-2]{1})(([0]{1}[1-9]{1})|([1,2]{1}[0-9]{1})|([3]{1}[0-1]{1}))[0-9]{6}");
    }

    public static boolean verifyEmail(String name){
        return name.matches("^[a-zA-Z]{1}[A-Za-z0-9._]+@[A-Za-z0-9]+\\.[A-Za-z]{2,}$");
    }

    private List<String> fullName;
    private String cnp;
    private String email;

    public Person(List<String> fullName, String cnp, String email) {
        this.fullName = fullName;
        this.cnp = cnp;
        this.email = email;
    }


    @Override
    public String toString() {
        return fullName.get(0) + " " + fullName.get(1) + " " + fullName.get(2) + " " + cnp + " " + email;
    }
}
