  Spring-AI-Models

A demo project showcasing how to integrate and utilize AI models (such as OpenAI, Anthropic Claude, and others) within a Spring Boot application using the **Spring AI** framework.

---

##  What’s Inside

This repository contains sample code and configurations for integrating Spring AI into a Spring Boot application:

-  Demonstrations of prompt engineering and structured responses  
-  Usage of the `ChatClient` to interface with language models  
-  Examples of model portability—switching between providers like OpenAI, Ollama, or Mistral with minimal code changes :contentReference[oaicite:0]{index=0}

---

##  Tech Stack

| Layer            | Description                                               |
|------------------|-----------------------------------------------------------|
| Java              | Core language for the Spring Boot application            |
| Spring Boot       | Framework to build and run the server                    |
| Spring AI         | Abstraction layer for AI integration                     |
| AI Model Providers | OpenAI, Claude, Mistral, Ollama (as configured examples) :contentReference[oaicite:1]{index=1} |

---

##  Project Structure

Spring-AI-Models/
├── src/ # Application source code
├── pom.xml # Maven build and dependency config
├── README.md # Project documentation
└── .gitignore # Untracked file settings

yaml
Copy
Edit

---

##  Getting Started

1. **Clone the repository**  
   ```bash
   git clone https://github.com/kush-prog/Spring-AI-Models.git
   cd Spring-AI-Models
Add Spring AI dependency (if not already present in pom.xml):

xml
Copy
Edit
<dependency>
  <groupId>org.springframework.ai</groupId>
  <artifactId>spring-ai-template-st</artifactId>
  <version>1.0.1</version>
</dependency>
Maven Repository

Configure your AI provider in application.properties or application.yml:

properties
Copy
Edit
spring.ai.openai.api-key=${OPENAI_API_KEY}
Or for Ollama:

properties
Copy
Edit
spring.ai.ollama.api-key=${OLLAMA_API_KEY}
Switching model providers is as simple as altering the dependency and config—no code changes required. 
Dimitri's tutorials

Run the application:

bash
Copy
Edit
mvn spring-boot:run
Interact with the AI endpoints (e.g., test prompt-to-response flows).

Why Spring AI?
The Spring AI framework offers powerful features:

A consistent abstraction (ChatClient) across different AI model providers 
Dimitri's tutorials
HelloGitHub

Support for prompt templating, JSON structuring, and multi-provider portability 
Dimitri's tutorials
Toolify

Simplified orchestration for advanced AI patterns like RAG and structured output 
DeepWiki
HelloGitHub

Author
Kush Chauhan

GitHub: kush-prog

Passionate about Spring Boot, backend systems, and AI integration.
