package com.example.DigitalLibrary.config;

import lombok.*;
import lombok.experimental.FieldDefaults;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ConfigurationProperties(prefix = "application")
@Component
@FieldDefaults(level = AccessLevel.PRIVATE)
public class CommonApplicationProperties {
    @Value("${application.baseUrls}")
    String baseUrl;
    @Value("${application.assetPaths}")
    String assetPath;
}
