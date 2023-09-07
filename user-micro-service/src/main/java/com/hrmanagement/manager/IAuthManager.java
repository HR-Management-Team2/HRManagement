package com.hrmanagement.manager;

import com.hrmanagement.dto.request.AuthUpdateRequestDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(url = "${openfeign.auth-manager-url}", name = "userprofile-auth")
public interface IAuthManager {
    @PutMapping("/update")
    public ResponseEntity<Boolean> updateAuth(@RequestBody AuthUpdateRequestDto dto);

    @PutMapping("/activate-status/{id}")
    public ResponseEntity<Boolean> activateStatusManager(@PathVariable Long id);

}