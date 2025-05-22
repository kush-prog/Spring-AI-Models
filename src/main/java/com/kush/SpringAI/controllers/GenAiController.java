package com.kush.SpringAI.controllers;

import com.kush.SpringAI.services.ChatService;
import com.kush.SpringAI.services.ImageService;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.ai.image.ImageResponse;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("spring-ai")
public class GenAiController {


    ChatService chatService;
    ImageService imageService;

    public GenAiController(ChatService chatService){
        this.chatService = chatService;
        this.imageService = imageService;
    }

    @GetMapping("/ask-AI")
    public String getResponse(@RequestParam String prompt){
        return chatService.getResponse(prompt);
    }

    @GetMapping("/generate-image")
    public void generateImage(HttpServletResponse response, @RequestParam String prompt) throws IOException {
        ImageResponse imageResponse = imageService.generateImage(prompt);
        String imageURL = imageResponse.getResult().getOutput().getUrl();
        response.sendRedirect(imageURL);
    }

    @GetMapping("/generate-image")
    public List<String> generateImageOptions(HttpServletResponse response, @RequestParam String prompt) throws IOException {
        ImageResponse imageResponse = imageService.generateImage(prompt);

        // Streams to get Urls from image response
        List<String> imageUrls = imageResponse.getResults().stream()
                .map(result -> result.getOutput().getUrl())
                .toList();
        return imageUrls;
    }
}
