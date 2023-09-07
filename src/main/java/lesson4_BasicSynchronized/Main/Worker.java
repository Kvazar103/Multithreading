package lesson4_BasicSynchronized.Main;

public class Worker {
    private int count=0;

    public int getCount() {
        return count;
    }

    //synchronized робити те коли потік береться за роботу над синхронізованим методом то він здобуває лок(замок)
    //який каже всім потокам які хочуть звернутися до того самого методу того обєкту іде команда чекай поки не закінче роботу
    public synchronized void increment(){
        count=count+1;
        System.out.println("Thread in progress: "+Thread.currentThread().getName() + " and count is: "+ count);
        try {
            Thread.sleep(200);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
