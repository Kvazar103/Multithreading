package lesson6_Wait_and_Notify;

public class Main {

    public static void main(String[] args) throws InterruptedException {
        Processor processor=new Processor();
        Thread producer=new Thread(processor::produce,"Producer-Thread");
        Thread consumer=new Thread(processor::consume,"Consumer-Thread");

        producer.start();
        consumer.start();

        producer.join();
        consumer.join();
    }
}
