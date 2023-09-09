package lesson5_Synchronized_and_Locks;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Generator {
    private Random random=new Random();

    private List<Integer> list1=new ArrayList<>();

    private List<Integer> list2=new ArrayList<>();

    //створюємо локи
    private Object lock1=new Object();
    private Object lock2=new Object();

    public List<Integer> getList1() {
        return list1;
    }

    public List<Integer> getList2() {
        return list2;
    }

    //так як t1 робить list1 і list2 так і t2 тоже отже два метода stageOne і stageTwo треба засинхронізувати
//    public synchronized void  stageOne() throws InterruptedException {
//        Thread.sleep(1);
//        list1.add(random.nextInt(100));
//    }
//    //через синхронізацію збільшився час роботи в два рази
//    public synchronized void stageTwo() throws InterruptedException {
//        Thread.sleep(1);
//        list2.add(random.nextInt(100));
//    }

    //робимо синхронізацію через блок
    public void stageOne() throws InterruptedException {
        synchronized (lock1){ //обєкт в дужках є локом/замком який ставить блок іншим потокам для роботи,(якщо поставити this то ми лок поставимо на обєкт Generator)
            // тобто жодин інший потік не зможе працювати з цим обєктом поки один потік має на ньому замок
            //в дужках передається обєкт який блокується
            Thread.sleep(1);
            list1.add(random.nextInt(100));
        }
    }
    //через синхронізацію збільшився час роботи в два рази
    public void stageTwo() throws InterruptedException {
        synchronized (lock2){
            Thread.sleep(1);
            list2.add(random.nextInt(100));
        }
    }

    public void generate(){
        try {
        for (int i=0;i<1000;i++){
                stageOne();
                stageTwo();
            }
        }catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
