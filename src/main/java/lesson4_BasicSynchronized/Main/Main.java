package lesson4_BasicSynchronized.Main;

public class Main {

    public static void main(String[] args) throws InterruptedException {
        Worker worker=new Worker();
        Runnable runnable= () -> {
            for (int i=0;i<10;i++){
                worker.increment();
            }
        };

        Thread thread1=new Thread(runnable,"thread_1");
        Thread thread2=new Thread(runnable,"thread_2");
        //два потоки які незалежні одні від одного намагаються змінити одну й ту саму змінну
        // і тому виникають проблеми з синхронізацією. Для розв'язання проблеми треба додати до методу .increment() synchronized

        thread1.start();
        thread2.start();

        thread1.join();
        thread2.join();

        System.out.println("Final count: "+ worker.getCount());
    }
}
