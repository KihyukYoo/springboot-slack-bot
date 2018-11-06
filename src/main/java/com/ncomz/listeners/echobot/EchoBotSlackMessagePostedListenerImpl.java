package com.ncomz.listeners.echobot;

import com.ullink.slack.simpleslackapi.SlackChannel;
import com.ullink.slack.simpleslackapi.SlackSession;
import com.ullink.slack.simpleslackapi.SlackUser;
import com.ullink.slack.simpleslackapi.events.SlackMessagePosted;
import com.ullink.slack.simpleslackapi.listeners.SlackMessagePostedListener;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class EchoBotSlackMessagePostedListenerImpl implements SlackMessagePostedListener {
    @Override
    public void onEvent(SlackMessagePosted event, SlackSession session) {
        SlackUser sender = event.getSender();

        if(sender.isBot()){
            return;
        }

        SlackChannel generalChannel = session.findChannelByName("general");
        String content = event.getMessageContent() + " said " + sender.getRealName();

        session.sendMessage(generalChannel, content);
    }
}
