package com.api.telegram.bot.telegrambot.registration;

import jakarta.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.updatesreceivers.DefaultBotSession;

import java.util.List;

@Slf4j
@Component
public class RegistrationComponent extends TelegramLongPollingBot {

    private final TelegramBotsApi botsApi = new TelegramBotsApi(DefaultBotSession.class);

    private final String username;

    public RegistrationComponent(@Value("${telegram.bot.token}") String token, @Value("${telegram.bot.username}") String username) throws TelegramApiException {
        super(token);
        this.username = username;
    }

    @PostConstruct
    private void init() throws TelegramApiException {
        botsApi.registerBot(this);
    }

    @Override
    public void onUpdateReceived(Update update) {
        System.out.println(update.getMessage().getText());
    }

    @Override
    public void onUpdatesReceived(List<Update> updates) {
        super.onUpdatesReceived(updates);
    }

    @Override
    public String getBotUsername() {
        return username;
    }

    @Override
    public void onRegister() {
        super.onRegister();
    }
}
