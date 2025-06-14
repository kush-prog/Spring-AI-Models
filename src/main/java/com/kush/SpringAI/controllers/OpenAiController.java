package com.kush.SpringAI.controllers;

import com.kush.SpringAI.services.ChatService;
import com.kush.SpringAI.services.ImageService;
import com.kush.SpringAI.services.RecipeService;
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
public class OpenAiController {

    private final ChatService chatService;
    private final ImageService imageService;
    private final RecipeService recipeService;

    // Constructor for dependency injection
    public OpenAiController(ChatService chatService, ImageService imageService, RecipeService recipeService) {
        this.chatService = chatService;
        this.imageService = imageService;
        this.recipeService = recipeService;
    }

    // Endpoint to get a response from the AI chat service
    @GetMapping("/ask-AI")
    public String getResponse(@RequestParam String prompt) {
        return chatService.getResponse(prompt);
    }

    // Endpoint to generate a single image based on a prompt
    @GetMapping("/generate-image")
    public void generateImage(HttpServletResponse response, @RequestParam String prompt) throws IOException {
        ImageResponse imageResponse = imageService.generateImage(prompt);
        String imageURL = imageResponse.getResult().getOutput().getUrl();
        response.sendRedirect(imageURL);
    }

    // Endpoint to generate multiple image options based on a prompt
    @GetMapping("/generate-image-options")
    public List<String> generateImageOptions(HttpServletResponse response,
                                             @RequestParam String prompt,
                                             @RequestParam(defaultValue = "hd") String quality,
                                             @RequestParam(defaultValue = "1") int n,
                                             @RequestParam(defaultValue = "1024") int width,
                                             @RequestParam(defaultValue = "1024") int height) throws IOException {
        ImageResponse imageResponse = imageService.generateImageOptions(prompt, quality, n, width, height);

        // Extract URLs from the image response
        return imageResponse.getResults().stream()
                .map(result -> result.getOutput().getUrl())
                .toList();
    }

    // Endpoint to create a recipe based on provided ingredients and dietary restrictions
    @GetMapping("/recipe-creator")
    public String recipeCreator(@RequestParam String ingredients,
                                @RequestParam(defaultValue = "any") String cuisine,
                                @RequestParam(defaultValue = " ") String dietaryRestrictions) {
        return recipeService.createRecipe(ingredients, cuisine, dietaryRestrictions);
    }
}
