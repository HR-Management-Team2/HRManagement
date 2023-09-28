package com.hrmanagement.manager;

import com.hrmanagement.dto.request.CheckCompanyRequestDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(url = "${openfeign.company-manager-url}", name = "auth-company")
public interface ICompanyManager {

    @PostMapping("/check-company")
    public ResponseEntity<Boolean> checkCompany(@RequestBody CheckCompanyRequestDto dto);

}
