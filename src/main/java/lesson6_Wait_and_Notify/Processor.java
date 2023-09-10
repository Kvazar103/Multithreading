package lesson6_Wait_and_Notify;

import java.util.LinkedList;

public class Processor {

    private LinkedList<Integer> list=new LinkedList<>();
    private final Object lock=new Object(); //для монітору локу ми використовуємо окремий обєкт

    private final int LIMIT=10;

    //wait і notify потрібні, щоб потоки інформувалися між собою
    //produce і consume повязані між собою локом і відповідно він може керувати нашими потоками

    public void produce(){
        int value=0;
        while (true){
            synchronized (lock){
                while (list.size()==LIMIT){
                    try {
                        lock.wait(); //thread постійно перебуває в стані runnable (див src/main/java/Java-Thread-State-with-Example.png)
                        // а з методом .wait() ми переводимо його в стан Waiting що є менш затратно
                        // отже як тільки ліст сайз = 10 ми відаємо замок в consume
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                }
                list.add(value++);
                lock.notify();
            }
        }
    }

    public void consume(){
        while(true){
            synchronized (lock){ //ставить лок тобто забороняє produce робити щось інакше
                while(list.isEmpty()){
                    try {
                        lock.wait();
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                }
                System.out.print("List size: "+list.size());
                int value=list.removeFirst(); //.removeFirst(); - це метод linked list який видаляє перший елемент
                System.out.println("; Remove value: "+value);
                lock.notify(); //пробуджуємо лок
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }
    //методи без .wait() і .notify() але більш затратні
//    public void produce(){
//        int value=0;
//        while (true){
//            synchronized (lock){
//                if(list.size()==LIMIT){
//                    continue;
//                }
//                list.add(value++);
//            }
//        }
//    }
//
//    public void consume(){
//        while(true){
//            synchronized (lock){ //ставить лок тобто забороняє produce робити щось інакше
//                if(list.isEmpty()){
//                    continue;
//                }
//                System.out.print("List size: "+list.size());
//                int value=list.removeFirst(); //.removeFirst(); - це метод linked list який видаляє перший елемент
//                System.out.println("; Remove value: "+value);
//                try {
//                    Thread.sleep(500);
//                } catch (InterruptedException e) {
//                    throw new RuntimeException(e);
//                }
//            }
//        }
//    }

}
