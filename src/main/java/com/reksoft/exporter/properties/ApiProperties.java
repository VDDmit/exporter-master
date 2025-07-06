package com.reksoft.exporter.properties;

import jakarta.validation.constraints.NotBlank;
import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

@Data
@Validated
@ConfigurationProperties("api")
@Component
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ApiProperties {
    @NotBlank(message = "baseUrl must be set in application.properties")
    String baseUrl;
}