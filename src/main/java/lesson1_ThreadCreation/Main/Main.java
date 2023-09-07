package lesson1_ThreadCreation.Main;

public class Main {
    public static void main(String[] args) throws InterruptedException {

//        Thread thread=new Thread(()->{
//            for(int i=0;i<10;i++){
//                try {
//                    Thread.sleep(1000); //.sleep() кидає потік "спати" на 1сек
//                } catch (InterruptedException e) {
//                    throw new RuntimeException(e);
//                }
//                System.out.println(Thread.currentThread().getName()+":"+ i);
//            }
//        },"Thread name ");

        Runnable runnable =()->{  //2 спосіб створюючи обєкт runnable ми можемо його передавати в thread //runnable - це інструкція для потоку
            for(int i=0;i<10;i++){
                try {
                    Thread.sleep(1000); //.sleep() кидає потік "спати" на 1сек
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                System.out.println(Thread.currentThread().getName()+":"+ i);
            }
            while(true){ //через це тільки перший потік виконає свою роботу + через джойн метод

            }
        };

        Thread thread=new Thread(runnable,"Thread_name_0");
        Thread thread1=new Thread(runnable,"Thread_name_1");
        Thread thread2=new Thread(runnable,"Thread_name_2");

//        thread.run();// ми просто викликаємо метод run() його тіло у поточному потоці який має процесорний час,
//        // ми працюємо в методі мейн, отже і в потоці мейн, ми в потоці мейн зробили виклик метода тому потік не запустився
        thread.start(); //для того, щоб запустити потік викликаємо метод старт
//        thread.join(); // .join() - Заблоковує виконання поточного потоку до завершення іншого.(thread1 не буде стартувати поки не закінчить thread)
        thread.join(1000); //.join(mill) дозволяє перервати поток якщо він працяє забагато
        thread1.start();
        thread2.start();

        //різниця між методом ран і страт - ран виконає код який прописаний в методі ран,
        // старт запустить новий потік і в ньому виконає код прописаний в методі ран


    }
}
