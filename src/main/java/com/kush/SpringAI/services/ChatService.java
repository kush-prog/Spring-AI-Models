package com.kush.SpringAI.services;

import org.springframework.ai.chat.model.ChatModel;
import org.springframework.ai.chat.model.ChatResponse;
import org.springframework.ai.chat.prompt.Prompt;
import org.springframework.ai.openai.OpenAiChatOptions;
import org.springframework.stereotype.Service;

@Service
public class ChatService {
    private final ChatModel chatModel;

    public ChatService(ChatModel chatModel) {
        this.chatModel = chatModel;
    }

    // Here a simple call is being made using the call method
    // And this call is being overloaded
    public String getResponse(String prompt){
        return chatModel.call(prompt);
    }

    public ChatResponse getResponseOptions(String prompt){
        ChatResponse response = chatModel.call(
                // here making use of Prompt object
                // Passing the prompt and specifying some options.
                new Prompt(
                        prompt,
                        // based on this options the output will be given
                        OpenAiChatOptions.builder()
                                .model("gpt-4o")
                                .temperature(0.4)
                                .build()
                ));
        return response;
    }
}
