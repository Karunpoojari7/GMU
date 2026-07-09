package com.gmu.app.config;

import java.util.HashSet;
import java.util.Set;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "security")
public class SecurityProperties {

    private Set<String> allowedServices = new HashSet<>();

    private Set<String> publicEndpoints = new HashSet<>();

    public Set<String> getAllowedServices() {
        return allowedServices;
    }

    public void setAllowedServices(Set<String> allowedServices) {
        this.allowedServices = allowedServices;
    }

    public Set<String> getPublicEndpoints() {
        return publicEndpoints;
    }

    public void setPublicEndpoints(Set<String> publicEndpoints) {
        this.publicEndpoints = publicEndpoints;
    }
}