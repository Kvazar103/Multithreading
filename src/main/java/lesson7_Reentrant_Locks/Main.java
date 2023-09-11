package lesson7_Reentrant_Locks;

import java.util.Arrays;

public class Main {

    public static void main(String[] args) throws InterruptedException {
        Runner runner=new Runner();

        Thread thread1=new Thread(runner::firstThreadJob);
        Thread thread2=new Thread(runner::secondThreadJob);

        thread1.start();
        thread2.start();

        thread1.join();
        thread2.join();

        runner.finishState();
    }
}
