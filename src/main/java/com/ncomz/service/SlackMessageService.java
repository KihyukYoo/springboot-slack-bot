package com.ncomz.service;

import com.ullink.slack.simpleslackapi.SlackChannel;
import com.ullink.slack.simpleslackapi.SlackSession;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class SlackMessageService {

    private SlackSession slackSession;

    public SlackMessageService(SlackSession slackSession){
        this.slackSession = slackSession;
    }

    public void sendMessage(String message){
        SlackChannel generalChannel = slackSession.findChannelByName("general");
        slackSession.sendMessage(generalChannel, message);
    }
}
