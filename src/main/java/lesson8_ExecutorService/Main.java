package lesson8_ExecutorService;

import java.util.UUID;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Main {

    public static void main(String[] args) {
        ExecutorService executorService= Executors.newFixedThreadPool(3);
        //executorService - це обслуговування потоків які обслуговують поставлені нами задачі
        //executorservice помогають нам обробляти велику кількість поставлених задач
        long start=System.currentTimeMillis();//засікаєм час
        for(int i=0;i<300;i++){
            String uuid= UUID.randomUUID().toString();//рандомно генерує
            //submit i execute - різниця лише в тому що execute нічого не повертає а submit повертає future
            //submit() метод ExecutorService використовується для надсилання завдання на виконання та повертає об'єкт Future,
            // який дозволяє вам отримувати результати завдання або скасовувати завдання.
            MessageProcessor messageProcessor=new MessageProcessor(uuid);
            executorService.execute(messageProcessor);
        }
        executorService.shutdown();//він перестає виконувати свою задачу
        while (!executorService.isTerminated()){

        }
        long end=System.currentTimeMillis();
        System.out.printf("Time taken: %s ms",(end-start));
    }
}
