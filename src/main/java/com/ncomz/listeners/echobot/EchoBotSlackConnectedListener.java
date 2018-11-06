package com.ncomz.listeners.echobot;

import com.ullink.slack.simpleslackapi.SlackChannel;
import com.ullink.slack.simpleslackapi.SlackSession;
import com.ullink.slack.simpleslackapi.events.SlackConnected;
import com.ullink.slack.simpleslackapi.listeners.SlackConnectedListener;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class EchoBotSlackConnectedListener implements SlackConnectedListener {
    @Override
    public void onEvent(SlackConnected event, SlackSession session) {
        SlackChannel generalChannel = session.findChannelByName("general");
        session.sendMessage(generalChannel, "Echo Bot Connected");
    }
}
