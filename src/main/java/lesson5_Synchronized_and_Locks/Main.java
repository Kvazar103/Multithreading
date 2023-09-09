package lesson5_Synchronized_and_Locks;

public class Main {

    public static void main(String[] args) throws InterruptedException {
        Generator generator=new Generator();
        Runnable runnable= generator::generate; //generator.generate()

        System.out.println("Starting...");
        long startTime=System.currentTimeMillis();//поточний час в мілісекундах

        Thread t1=new Thread(runnable);
        Thread t2=new Thread(runnable);
        t1.start();
        t2.start();

        t1.join();
        t2.join();

        long endTime=System.currentTimeMillis();
        System.out.println("Time taken: "+ (endTime - startTime));
        System.out.println("List1: "+ generator.getList1().size()+ ";List2: "+generator.getList2().size());
    }
}
