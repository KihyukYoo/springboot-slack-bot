package com.ncomz.config;

import com.ncomz.listeners.echobot.EchoBotSlackConnectedListener;
import com.ncomz.listeners.echobot.EchoBotSlackMessagePostedListener;
import com.ncomz.properties.SlackProperties;
import com.ncomz.service.SlackMessageService;
import com.ullink.slack.simpleslackapi.SlackSession;
import com.ullink.slack.simpleslackapi.impl.SlackSessionFactory;
import com.ullink.slack.simpleslackapi.listeners.SlackConnectedListener;
import com.ullink.slack.simpleslackapi.listeners.SlackMessagePostedListener;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.IOException;

@Slf4j
@Configuration
public class SlackConfig {

    @Autowired
    SlackProperties slackProperties;

    @Bean
    SlackSession slackSession() throws IOException {
        SlackSession slackSession = SlackSessionFactory.createWebSocketSlackSession(slackProperties.token);
        slackSession.connect();

        if(!slackSession.isConnected()){
            log.debug("Slack Session Connect Failed!");
            return slackSession;
        }
        log.debug("Slack Session Connected!");

        // add listener to slack session
        slackSession.addSlackConnectedListener(handleEchoBotSlackConnectedEvent());
        slackSession.addMessagePostedListener(handleEchoBotSlackMessagePostedEvent());

        return slackSession;
    }

    @Bean
    SlackConnectedListener handleEchoBotSlackConnectedEvent() {
        return new EchoBotSlackConnectedListener();
    }

    @Bean
    SlackMessagePostedListener handleEchoBotSlackMessagePostedEvent() {
        return new EchoBotSlackMessagePostedListener();
    }
}
