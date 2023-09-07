package ThreadJoin.Main;

public class CalculateReportJob implements Runnable{
    private String result;

    public String getResult() {
        return result;
    }

    @Override
    public void run() {
        System.out.println("Some calculate...");
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        this.result="New result";
    }
}
