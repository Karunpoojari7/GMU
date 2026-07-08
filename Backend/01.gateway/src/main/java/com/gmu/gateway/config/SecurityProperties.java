package com.gmu.gateway.config;

import java.util.List;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import lombok.Data;

@ConfigurationProperties(prefix = "security")
@Component
@Data
public class SecurityProperties {

    private List<String> publicEndpoints;
}