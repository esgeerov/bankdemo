package az.orient.bank;

import az.orient.bank.schedule.MyThread;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@SpringBootApplication
@EnableScheduling
public class BankdemoApplication {


    public static void main(String[] args) {
        SpringApplication.run(BankdemoApplication.class, args);
        BCryptPasswordEncoder passwordEncoder=new BCryptPasswordEncoder();
       System.out.println(passwordEncoder.encode("123Samir"));
        MyThread myThread=new MyThread();
        myThread.start();
    }

}