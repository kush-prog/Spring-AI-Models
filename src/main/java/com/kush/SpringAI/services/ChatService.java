package com.kush.SpringAI.services;

import org.springframework.ai.chat.model.ChatModel;
import org.springframework.ai.chat.model.ChatResponse;
import org.springframework.ai.chat.prompt.Prompt;
import org.springframework.ai.openai.OpenAiChatOptions;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.Map;
import java.util.Objects;

@Service
public class ChatService {
    private final ChatModel chatModel;
    private final WebClient webClient;

    @Value("${gemini.api.base-url}")
    private String geminiUrl;
    @Value("${gemini.api.key}")
    private String geminiKey;

    // Constructor for dependency injection
    public ChatService(ChatModel chatModel, WebClient.Builder webClient) {
        this.chatModel = chatModel;
        this.webClient = webClient.build();
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
                                .model("llama3-70b-8192")
                                .temperature(0.4)
                                .maxTokens(200)
                                .build()
                ));
        return response;
    }

    public String getAnswer(String question) {
        Map<String, Object> requestBody = Map.of(
                "contents", new Object[] {
                        Map.of("parts", new Object[] {
                                Map.of("text", question)
                        })
        });
        String response = webClient.post()
                .uri(geminiUrl + geminiKey)
                .header("Content-Type", "application/json")
                .bodyValue(requestBody)
                .retrieve()
                .bodyToMono(String.class)
                .block();

        return response ;
    }
}
