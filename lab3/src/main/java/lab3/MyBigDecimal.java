package lab3;

import java.io.*;
import java.math.BigDecimal;
import java.util.LinkedList;

/**
 * Created by Raul on 12/11/2017.
 */
public class MyBigDecimal implements Serializable{
    public static final long serialVersionUID = 1L;

    private BigDecimal bigDecimal;

    private static BigDecimal zero = new BigDecimal("0");
    private static BigDecimal base = new BigDecimal("1000000");


    public MyBigDecimal(String number){
        bigDecimal = new BigDecimal(number);
    }

    public MyBigDecimal(){
    }

    private Object writeReplace() {
        return bigDecimal.toString();
    }

    public static MyBigDecimal deserialize(ObjectInput io) throws IOException, ClassNotFoundException {
        //System.out.println("ajunge la read");
        String s = (String) io.readObject();
        //return null;
        return new MyBigDecimal(s);
    }

    private void readObject(ObjectInputStream s) throws InvalidObjectException {
        throw new InvalidObjectException("Deserialization via serialization delegate");
    }




    //Serializing just the toString representation
    /*@Override
    public void writeExternal(ObjectOutput out) throws IOException {
        //DataOutput output = out;
        //output.writeChars(bigDecimal.toString());
        //System.out.println("ajunge la write");
        String s = bigDecimal.toString();
        out.writeObject(s);
    }


    public static MyBigDecimal readExternalStatic(ObjectInput io) throws IOException, ClassNotFoundException {
        System.out.println("ajunge la read");
        String s = (String) io.readObject();
        //return null;
        return new MyBigDecimal(s);
    }

    @Override
    public void readExternal(ObjectInput in) throws IOException, ClassNotFoundException {
//        System.out.println("ajunge la readExternal");
//        throw new IOException("Serialization not allowed");
        String s = (String) in.readObject();
        bigDecimal = new BigDecimal(s);
    }*/

    //Serializing the BigDecimal in base 10^6 - works 4 to 10 times slower than standard serialization
    /*Times obtained:
    1.000.000 elements
    Standard BigDecimal: Elapsed time in Milliseconds: 24600
    MyBigDecimal: Elapsed time in Milliseconds: 226028

    100.000 elements

    Standard BigDecimal: Elapsed time in Milliseconds: 2872
    MyBigDecimal: Elapsed time in Milliseconds: 19827

     */

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
