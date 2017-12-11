/**
 * Created by Raul on 10/12/2017.
 */
public class WriteThread implements Runnable {
    @Override
    public void run() {
        System.out.println(ConnectionManager.getUsers());
        while (true){
            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
