package com.example.chatbot.bot;

import com.example.chatbot.service.GetMesService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.time.Duration;
import java.time.LocalTime;
import java.util.List;

@Component
public class GetMailingMessage extends TelegramLongPollingBot {

    private static final Logger LOG = LoggerFactory.getLogger(GetMailingMessage.class);

    private static final String START = "/start";
    private static final String ASK = "/ask";
    private static final String LEFT = "/left";

    @Autowired
    private GetMesService getMesService;

    public GetMailingMessage(@Value("${bot.token}") String botToken) {
        super(botToken);
    }

    //обработка команд
    @Override
    public void onUpdateReceived(Update update) {
        if (!update.hasMessage() || !update.getMessage().hasText()) {
            return;
        }
        String request = update.getMessage().getText();
        Long chatId = update.getMessage().getChatId();

        switch (request) {
            case START: {
                String userName = update.getMessage().getChat().getUserName();
                startCommand(chatId, userName);
                break;
            }
            case ASK: {
                nextCommand(chatId);
                break;
            }
            case LEFT: {
                String userName = update.getMessage().getChat().getUserName();
                timeLeft(chatId, userName);
                break;
            }
            default: {
                String errorMes = "Неверная команда";
                sendMessage(chatId, errorMes);
            }
        }
    }

    private void sendMessage(Long chatId, String text) {
        String chatIdStr = String.valueOf(chatId);
        var sendMessage = new SendMessage(chatIdStr, text);
        try {
            execute(sendMessage);
        } catch (TelegramApiException e) {
            LOG.error("Ошибка отправки сообщения", e);
        }
    }

    private void timeLeft(Long chatId, String userName) {
        LocalTime currentTime = LocalTime.now();

        // Устанавливаем время 23:00
        LocalTime targetTime = LocalTime.of(23, 0);

        // Вычисляем разницу между текущим временем и 23:00
        Duration duration = Duration.between(currentTime, targetTime);

        // Получаем количество часов и минут в разнице
        long hours = duration.toHours();
        long minutes = duration.toMinutes() % 60;

        String timeLeft = "%s, тебе осталось пахать\n" +
                hours + " часов и " + minutes + " минут до 23:00.";

        String formattedStr = String.format(timeLeft, userName);
        sendMessage(chatId, formattedStr);
    }

    @Override
    public void onUpdatesReceived(List<Update> updates) {
        super.onUpdatesReceived(updates);
    }

    @Override
    public String getBotUsername() {
        return "RealEstDubai_Bot";
    }

    private void startCommand(Long chatId, String userName) {
        String text = """
                Hello, %s
                как работается?                                
                                
                /ask - узнать моё текущее мнение
                /left - подскажу сколько тебе въебывать
                """;
        String formattedText = String.format(text, userName);
        sendMessage(chatId, formattedText);
    }

    private void nextCommand(Long chatId) {
        String text = getMesService.getNextMessage();
        sendMessage(chatId, text);
    }

}
