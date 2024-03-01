package com.example.chatbot.configuration;

import com.example.chatbot.bot.GetMailingMessage;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.updatesreceivers.DefaultBotSession;

@Configuration
public class GetMailingMessageConfiguration {

    @Bean
    public TelegramBotsApi telegramBotsApi(GetMailingMessage getMailingMessage) throws TelegramApiException {
        TelegramBotsApi api = new TelegramBotsApi(DefaultBotSession.class);
        api.registerBot(getMailingMessage);
        return api;
    }
}
