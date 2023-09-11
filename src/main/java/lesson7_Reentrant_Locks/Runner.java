package lesson7_Reentrant_Locks;

import java.util.Scanner;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Runner {

    private int count=0;
    private Lock reentrantLock=new ReentrantLock();
    private Condition condition=reentrantLock.newCondition();
    public void increment(){
        for(int i=0;i<10000;i++){
            count++;
        }
    }

    public void firstThreadJob(){
        reentrantLock.lock(); //відкрили synchronized
        System.out.println("Waiting...");
        try {
            condition.await();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        System.out.println("Woken up ");
        try {
            increment();
        }finally {
            //тут ми хочемо щоб лок знявся незважаючи на те чи успішно/не успішно виконався метод increment()
            reentrantLock.unlock();//тут закриваєм
        }
    }

    public void secondThreadJob(){
        reentrantLock.lock();
        System.out.println("Press return key!");
        new Scanner(System.in).nextLine(); //Стандартний потік введення (клавіатура) Java представлений об'єктом — System.in
//        Метод nextLine() звертається до джерела даних, знаходить там наступний рядок, який він ще не зчитував і повертає його
        System.out.println("Got return key!");
        condition.signal();
        try {
            increment();
            System.out.println("Second thread is done!");
        }finally {
            reentrantLock.unlock();
        }
    }

    public void finishState(){
        System.out.println("Final count: "+count);
    }

}
