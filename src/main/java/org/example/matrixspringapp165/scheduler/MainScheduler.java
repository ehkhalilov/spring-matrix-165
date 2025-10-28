package org.example.matrixspringapp165.scheduler;


import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class MainScheduler {

    //    @Scheduled(cron = "")
    public void test() {
        System.out.println("Current date and time" + LocalDateTime.now());
    }
}
