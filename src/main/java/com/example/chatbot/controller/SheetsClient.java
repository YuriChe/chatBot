package com.example.chatbot.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

//@Component
public class SheetsClient {
    public String getMessage() {
        return message;
    }

//    @Value("${test.message}")
    private String message;
}
