package com.example.chatbot.service.impl;

import com.example.chatbot.service.GetMesService;
import org.springframework.stereotype.Service;


@Service
public class GetMesServiceImpl implements GetMesService {

    /*@Autowired
    private SheetsClient sheetsClient;*/

    @Override
    public String getNextMessage() {
        String str;
        if (((int) (Math.random() * 10) + 1) > 5) {
            str = """
                        Мне кажется ты был бы не прочь выпить,\n
                        но ты как разумный и прекрасный человек воздержишься!
                    """;
        } else {
            str = """
                        Ну подустал, конечно,\n
                        но выглядишь хорошо!
                    """;
        }
        return str;
    }
}
