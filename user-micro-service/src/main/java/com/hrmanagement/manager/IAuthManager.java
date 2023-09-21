package com.hrmanagement.manager;

import com.hrmanagement.dto.request.AuthEmployeeUpdateRequestDto;
import com.hrmanagement.dto.request.AuthManagerUpdateRequestDto;
import com.hrmanagement.dto.request.AuthUpdateRequestDto;
import com.hrmanagement.dto.request.UpdateUserInfoRequestDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(url = "${openfeign.auth-manager-url}", name = "user-auth")
public interface IAuthManager {
    @PutMapping("/update")
    public ResponseEntity<Boolean> updateAuth(@RequestBody AuthUpdateRequestDto dto);

    @PutMapping("/update-employee")
    public ResponseEntity<Boolean> updateAuthEmployee(@RequestBody AuthEmployeeUpdateRequestDto dto);
    @PutMapping("/update-manager")
    public ResponseEntity<Boolean> updateAuthManager(@RequestBody AuthManagerUpdateRequestDto dto);

    @PutMapping("/activate-status/{id}")
    public ResponseEntity<Boolean> activateStatusManager(@PathVariable Long id);

    @DeleteMapping("/delete-manager/{id}")
    public ResponseEntity<Boolean> deleteAuthManager(@PathVariable Long id);
}