package com.kush.SpringAI.services;

import org.springframework.ai.image.ImagePrompt;
import org.springframework.ai.image.ImageResponse;
import org.springframework.ai.openai.OpenAiImageModel;
import org.springframework.ai.openai.OpenAiImageOptions;
import org.springframework.stereotype.Service;

@Service
public class ImageService {
    private final OpenAiImageModel openAiImageModel;

    // Constructor for dependency injection
    public ImageService(OpenAiImageModel openAiImageModel) {
        this.openAiImageModel = openAiImageModel;
    }

    // Method to generate a single image based on a prompt
    public ImageResponse generateImage(String prompt) {
        return openAiImageModel.call(new ImagePrompt(prompt));
    }

    // Method to generate multiple image options based on a prompt and specified parameters
    public ImageResponse generateImageOptions(String prompt, String quality, int n, int width, int height) {
        ImageResponse response = openAiImageModel.call(
                new ImagePrompt(prompt,
                        OpenAiImageOptions.builder()
                                .model("dall-e-2")
                                .quality(quality)
                                .N(n)
                                .height(height)
                                .width(width)
                                .build())
        );
        return response;
    }
}
