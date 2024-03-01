package com.example.chatbot.service.impl;

import com.example.chatbot.controller.SheetsClient;
import com.example.chatbot.service.GetMesService;
import org.springframework.beans.factory.annotation.Autowired;

public class GetMesServiceImpl implements GetMesService {

    @Autowired
    private SheetsClient sheetsClient;
    @Override
    public String GetNextMessage() {
        return sheetsClient.getMessage();
    }
}
