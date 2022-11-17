package com.emse.spring.faircorp.service;

import com.emse.spring.faircorp.service.dto.ApiGouvAddressDto;
import com.emse.spring.faircorp.service.dto.ApiGouvFeatureDto;
import com.emse.spring.faircorp.service.dto.ApiGouvResponseDto;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.ArrayList;
import java.util.List;

@Service
public class AddressSearchService {
    private final RestTemplate restTemplate;

    public AddressSearchService(RestTemplateBuilder restTemplateBuilder) {
        this.restTemplate = restTemplateBuilder.rootUri("https://api-adresse.data.gouv.fr").build();
    }

    public List<ApiGouvAddressDto> findAddress(List<String> search) {
        String params = String.join("+", search);
        String uri = UriComponentsBuilder.fromUriString("/search").queryParam("q", params).queryParam("limit", 15).build().toUriString();

        List<ApiGouvFeatureDto> feature_result = restTemplate.getForObject(uri, ApiGouvResponseDto.class).getFeatures();
        List<ApiGouvAddressDto> address_result = new ArrayList<>();

        for(ApiGouvFeatureDto feature : feature_result) {
            address_result.add(feature.getProperties());
        }

        return address_result;
    }
}
