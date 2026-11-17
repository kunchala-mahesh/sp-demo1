package com.example.api.config;

import com.example.api.model.Post;
import com.example.api.model.Product;
import com.example.api.model.User;
import com.example.api.repository.PostRepository;
import com.example.api.repository.ProductRepository;
import com.example.api.repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.math.BigDecimal;
import java.util.List;

@Configuration
public class DataInitializer {

    @Bean
    public CommandLineRunner initDatabase(
            UserRepository userRepository,
            PostRepository postRepository,
            ProductRepository productRepository) {
        return args -> {
            // Seed Users
            if (userRepository.count() == 0) {
                User user1 = new User("alice_w", "alice@example.com", "Alice Williams", "ADMIN");
                User user2 = new User("bob_dev", "bob@example.com", "Bob Jenkins", "USER");
                User user3 = new User("charlie_k", "charlie@example.com", "Charlie Kelly", "USER");
                userRepository.saveAll(List.of(user1, user2, user3));
            }

            // Seed Posts
            if (postRepository.count() == 0) {
                Post post1 = new Post(
                        "Getting Started with Spring Boot 3",
                        "Spring Boot 3 introduces full support for Java 17 and 21, Spring Framework 6, and native image compilation.",
                        "alice_w",
                        "Engineering"
                );
                post1.setLikes(14);

                Post post2 = new Post(
                        "Building Clean REST APIs",
                        "Explore best practices for designing scalable RESTful APIs with proper validation and consistent error models.",
                        "bob_dev",
                        "Architecture"
                );
                post2.setLikes(29);

                Post post3 = new Post(
                        "Microservices vs Modular Monolith",
                        "When should you split your architecture into microservices, and when does a clean modular monolith win?",
                        "alice_w",
                        "Engineering"
                );
                post3.setLikes(8);

                postRepository.saveAll(List.of(post1, post2, post3));
            }

            // Seed Products
            if (productRepository.count() == 0) {
                Product prod1 = new Product(
                        "Wireless Noise-Cancelling Headphones",
                        "Premium over-ear headphones with 30-hour battery life and high fidelity audio.",
                        new BigDecimal("199.99"),
                        "Electronics",
                        45
                );

                Product prod2 = new Product(
                        "Ergonomic Mechanical Keyboard",
                        "Custom mechanical keyboard with hot-swappable switches and RGB backlighting.",
                        new BigDecimal("129.50"),
                        "Accessories",
                        70
                );

                Product prod3 = new Product(
                        "4K Ultra-Wide Monitor 34\"",
                        "Curved IPS panel with 144Hz refresh rate, USB-C hub, and HDR400 support.",
                        new BigDecimal("499.00"),
                        "Electronics",
                        25
                );

                Product prod4 = new Product(
                        "Adjustable Standing Desk",
                        "Dual-motor electric standing desk with digital memory preset keypad.",
                        new BigDecimal("349.95"),
                        "Furniture",
                        15
                );

                productRepository.saveAll(List.of(prod1, prod2, prod3, prod4));
            }
        };
    }
}
