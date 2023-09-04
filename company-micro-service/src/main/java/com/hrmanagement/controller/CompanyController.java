package com.hrmanagement.controller;

import com.hrmanagement.dto.request.CreateCompanyRequestDto;
import com.hrmanagement.service.CompanyService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/company")
@RequiredArgsConstructor
public class CompanyController {
    private final CompanyService companyService;

    @PostMapping("/createcompany")
    public ResponseEntity<Boolean> createCompany(CreateCompanyRequestDto dto){
        return ResponseEntity.ok(companyService.createCompany(dto));
    }
}
