/**
 * Created by T440P on 2016/6/7.
 */
public class JMXTest {
    public static void main(String[] args){
        try {
            System.out.print("begin sleep for 1 minute");
            Thread.sleep(10000);
            System.out.print("end sleep");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
