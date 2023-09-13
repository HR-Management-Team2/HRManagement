package com.hrmanagement.controller;

import com.hrmanagement.dto.request.CreateCompanyRequestDto;
import com.hrmanagement.dto.request.UpdateCompanyRequestDto;
import com.hrmanagement.repository.entity.Company;
import com.hrmanagement.service.CompanyService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.hrmanagement.constant.RestApis.*;

@RestController
@RequestMapping(COMPANY)
@RequiredArgsConstructor
public class CompanyController {
    private final CompanyService companyService;

    @PostMapping(ADDCOMPANY)
    public ResponseEntity<Boolean> createCompany(CreateCompanyRequestDto dto){
        return ResponseEntity.ok(companyService.createCompany(dto));
    }

    @GetMapping(FINDALL)
    public ResponseEntity<List<Company>> findAll(){
        return ResponseEntity.ok(companyService.findAllCompanies());
    }

    @PostMapping(UPDATE)
    public ResponseEntity<Company> updateCompany(@PathVariable String taxNumber, @RequestBody UpdateCompanyRequestDto dto){
        Company company = companyService.updateCompany(taxNumber, dto);
        return new ResponseEntity<>(company, HttpStatus.OK);
    }




}
