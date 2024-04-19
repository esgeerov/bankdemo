package az.orient.bank.schedule;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class MySchedule {


    @Scheduled(fixedDelay = 2000)
    public void fixedRate(){
        System.out.println("Schedule fixed rate");
    }



}
