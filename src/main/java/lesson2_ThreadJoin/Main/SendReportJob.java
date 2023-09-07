package lesson2_ThreadJoin.Main;

public class SendReportJob extends Thread{

    private CalculateReportJob calculateReportJob;

    public SendReportJob(CalculateReportJob calculateReportJob) {
        this.calculateReportJob = calculateReportJob;
    }

    @Override
    public void run(){
        String result=calculateReportJob.getResult();
        System.out.printf("Sending report: %s\n",result);
        try {
            sleep(2000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        System.out.println("Report is sent!");
    }
}
