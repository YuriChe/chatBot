package com.example.chatbot.bot;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.DefaultBotOptions;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.objects.Update;

import java.util.List;
@Component
public class GetMailingMessage extends TelegramLongPollingBot {

    private static final Logger LOG = LoggerFactory.getLogger(GetMailingMessage.class);

    public GetMailingMessage(@Value("${bot.token}") String botToken) {
        super(botToken);
    }

    public GetMailingMessage(DefaultBotOptions options, String botToken) {
        super(options, botToken);
    }

    //обработка команд
    @Override
    public void onUpdateReceived(Update update) {

    }

    @Override
    public void onUpdatesReceived(List<Update> updates) {
        super.onUpdatesReceived(updates);
    }

    @Override
    public String getBotUsername() {
        return null;
    }

    @Override
    public void onRegister() {
        super.onRegister();
    }
}
