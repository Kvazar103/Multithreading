package Volatile;

public class Main {
    public static volatile boolean running = true;
    public static void main(String[] args) throws InterruptedException {

        //без volatile в змінні running два потік1 і потік2 взяли собі копію running
        // тобто running=false тільки в runnable1
        //а в runnable2 running=true так і лишився бо тут була взята окрема копія
        // а з volatile - забороняє потокам робити копію змінної з головної памяті
        Runnable runnable1=()->{
            for(int i=0;i<2000;i++){
                System.out.println("runnable1 value: "+i);
            }
            running=false;
            System.out.println("State has been changed to: "+running);
        };

        Runnable runnable2=()->{
            int i=0;
            while (running){
                i++;
            }
            System.out.println("Runnable2 final value: "+i);
        };

        Thread thread1=new Thread(runnable1);
        Thread thread2=new Thread(runnable2);

        thread1.start();
        thread2.start();

        thread1.join();
        thread2.join();

        System.out.println("All thread are completed!");

    }
}
