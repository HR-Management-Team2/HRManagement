package com.hrmanagement.controller;

import com.hrmanagement.dto.request.CheckCompanyRequestDto;
import com.hrmanagement.dto.request.CreateCompanyRequestDto;
import com.hrmanagement.dto.request.UpdateCompanyRequestDto;
import com.hrmanagement.repository.entity.Company;
import com.hrmanagement.service.CompanyService;
import io.swagger.v3.oas.annotations.Hidden;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.hrmanagement.constant.RestApis.*;

@RestController
@RequestMapping(COMPANY)
@RequiredArgsConstructor
@CrossOrigin("*")
public class CompanyController {
    private final CompanyService companyService;

    @PostMapping(ADDCOMPANY)
    public ResponseEntity<Boolean> createCompany(@RequestBody CreateCompanyRequestDto dto){
        return ResponseEntity.ok(companyService.createCompany(dto));
    }

    @GetMapping(FINDALL)
    public ResponseEntity<List<Company>> findAll(){
        return ResponseEntity.ok(companyService.findAllCompanies());
    }

    @PutMapping(UPDATE)
    @CrossOrigin("*")
    public ResponseEntity<Company> updateCompany(@PathVariable String taxNumber, @RequestBody UpdateCompanyRequestDto dto){
        Company company = companyService.updateCompany(taxNumber, dto);
        return new ResponseEntity<>(company, HttpStatus.OK);
    }

    @DeleteMapping(DELETE)
    public ResponseEntity<Boolean> deleteCompany(@RequestBody @PathVariable String taxNumber){
        return ResponseEntity.ok(companyService.deleteCompany(taxNumber));
    }

    @Hidden
    @PostMapping(CHECKCOMPANY)
    public ResponseEntity<Boolean> checkCompany(@RequestBody CheckCompanyRequestDto dto){
        return ResponseEntity.ok(companyService.checkCompany(dto));
    }


}
