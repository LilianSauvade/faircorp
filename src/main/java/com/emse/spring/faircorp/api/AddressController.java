package com.emse.spring.faircorp.api;

import com.emse.spring.faircorp.service.AddressSearchService;
import com.emse.spring.faircorp.service.dto.ApiGouvAddressDto;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import javax.transaction.Transactional;
import java.util.List;

@RestController
@RequestMapping("/api/address")
@Transactional
public class AddressController {
    private final AddressSearchService service;

    public AddressController(AddressSearchService service) {
        this.service = service;
    }

    @GetMapping
    public List<ApiGouvAddressDto> findAddress(String criteria) {
        return service.findAddress(List.of(criteria));
    }
}
