package com.emse.spring.faircorp.service.dto;

public class ApiGouvFeatureDto {
    private String type;
    private ApiGouvAddressDto properties;

    public ApiGouvFeatureDto() {}

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public ApiGouvAddressDto getProperties() {
        return properties;
    }

    public void setProperties(ApiGouvAddressDto properties) {
        this.properties = properties;
    }
}