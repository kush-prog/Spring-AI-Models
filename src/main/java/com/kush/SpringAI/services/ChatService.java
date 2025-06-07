package com.kush.SpringAI.services;

import org.springframework.ai.chat.model.ChatModel;
import org.springframework.ai.chat.model.ChatResponse;
import org.springframework.ai.chat.prompt.Prompt;
import org.springframework.ai.openai.OpenAiChatOptions;
import org.springframework.stereotype.Service;

@Service
public class ChatService {
    private final ChatModel chatModel;

    // Constructor for dependency injection
    public ChatService(ChatModel chatModel) {
        this.chatModel = chatModel;
    }

    // Method to get a simple response from the chat model using a prompt
    public String getResponse(String prompt) {
        return chatModel.call(prompt);
    }

    // Method to get a detailed response with options from the chat model
    public ChatResponse getResponseOptions(String prompt) {
        ChatResponse response = chatModel.call(
                // Creating a Prompt object with options
                new Prompt(
                        prompt,
                        // Specifying options for the chat model
                        OpenAiChatOptions.builder()
                                .model("gpt-4o")
                                .temperature(0.4)
                                .build()
                ));
        return response;
    }
}
