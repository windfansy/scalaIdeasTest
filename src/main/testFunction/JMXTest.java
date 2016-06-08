/**
 * Created by T440P on 2016/6/7.
 */
public class JMXTest {
    public static void main(String[] args){
        long startTime = System.currentTimeMillis();
        long duration = 1 * 60 * 1000; // 1 minute to mills
        while(System.currentTimeMillis() - startTime < duration)
            System.out.println("Time has not yet");
        System.out.println("Done");
    }
}
