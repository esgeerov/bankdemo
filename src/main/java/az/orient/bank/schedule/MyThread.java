package az.orient.bank.schedule;

public class MyThread extends Thread {

    @Override
    public void run() {
        while(true){
            System.out.println("success!!");
            try {
                Thread.sleep(3000);
            } catch (Exception e) {
               e.printStackTrace();
            }
        }
    }
}
