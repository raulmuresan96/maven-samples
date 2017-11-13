package lab3;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;
import java.math.BigDecimal;
import java.util.LinkedList;

/**
 * Created by Raul on 12/11/2017.
 */
public class MyBigDecimal implements Externalizable{

    private BigDecimal bigDecimal;

//    private static BigDecimal zero = new BigDecimal("0");
//    private static BigDecimal base = new BigDecimal("1000000");


    public MyBigDecimal(String number){

        bigDecimal = new BigDecimal(number);
    }

    public MyBigDecimal(){
    }


    //Serializing just the toString representation
    @Override
    public void writeExternal(ObjectOutput out) throws IOException {
        String s = bigDecimal.toString();
        out.writeObject(s);
    }

    @Override
    public void readExternal(ObjectInput in) throws IOException, ClassNotFoundException {
        String s = (String) in.readObject();
        bigDecimal = new BigDecimal(s);
    }

    //Serializing the BigDecimal in base 10^6 - works 4-5 times slower than standard serialization

//    @Override
//    public void writeExternal(ObjectOutput out) throws IOException {
//        LinkedList<String> linkedList = new LinkedList<>();
//
//        while (bigDecimal.compareTo(zero) > 0){
//            linkedList.addFirst(bigDecimal.remainder(base).toString());
//            //System.out.println(bigDecimal);
//            bigDecimal = bigDecimal.divideToIntegralValue(base);
//        }
//        out.writeObject(linkedList);
//    }
//
//    @Override
//    public void readExternal(ObjectInput in) throws IOException, ClassNotFoundException {
//        LinkedList<String> linkedList = (LinkedList<String>) in.readObject();
//        bigDecimal = BigDecimal.ZERO;
//        BigDecimal current = BigDecimal.ONE;
//        while (linkedList.size() > 0){
//            bigDecimal = bigDecimal.add(new BigDecimal(linkedList.getLast()) .multiply(current)) ;
//            linkedList.removeLast();
//            current = current.multiply(base);
//        }
//    }

    public BigDecimal getBigDecimal() {
        return bigDecimal;
    }
}
