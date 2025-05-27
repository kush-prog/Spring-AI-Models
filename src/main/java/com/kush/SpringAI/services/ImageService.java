package com.kush.SpringAI.services;

import jakarta.websocket.OnOpen;
import org.springframework.ai.image.ImagePrompt;
import org.springframework.ai.image.ImageResponse;
import org.springframework.ai.openai.OpenAiImageModel;
import org.springframework.ai.openai.OpenAiImageOptions;
import org.springframework.stereotype.Service;

@Service
public class ImageService {

    private final OpenAiImageModel openAiImageModel;

    public ImageService(OpenAiImageModel openAiImageModel) {
        this.openAiImageModel = openAiImageModel;
    }

    public ImageResponse generateImage(String prompt){
        return openAiImageModel.call(
                new ImagePrompt(prompt)
                );
    }

    public ImageResponse generateImageOptions(String prompt, String quality, int n, int width, int height){
//        ImageResponse response = openAiImageModel.call(
//                new ImagePrompt("A light cream colored mini golden doodle",
//                        OpenAiImageOptions.builder()
//                                .model("dall-e-2")
//                                .quality("hd")
//                                .N(4)
//                                .height(1024)
//                                .width(1024).build())
//
//        );

        ImageResponse response = openAiImageModel.call(
                new ImagePrompt("A light cream colored mini golden doodle",
                        OpenAiImageOptions.builder()
                                .model("dall-e-2")
                                .quality(quality)
                                .N(n)
                                .height(height)
                                .width(width).build())

        );
        return response;
    }
}
