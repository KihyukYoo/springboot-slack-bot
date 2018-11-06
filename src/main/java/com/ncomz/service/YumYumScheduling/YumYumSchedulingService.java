package com.ncomz.service.YumYumScheduling;

import com.ncomz.service.SlackMessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Service
@EnableScheduling
public class YumYumSchedulingService {

    @Autowired
    SlackMessageService slackMessageService;

    // sec(0~59) min(0~59) hour(0~23) day of month(1~31) month(1~12) day of week(0~6, 0 is sunday)
    @Scheduled(cron = "0 0 12 * * 1-5")
    public void alarmLunchTime(){
        slackMessageService.sendMessage("YUMYUM TIME!");
    }
}
