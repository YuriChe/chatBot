package com.example.chatbot.controller;

import org.springframework.beans.factory.annotation.Value;

public class SheetsClient {
    public String getMessage() {
        return message;
    }

    @Value("${Test message}")
    private String message;


}
