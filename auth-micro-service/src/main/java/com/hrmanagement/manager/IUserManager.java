package com.hrmanagement.manager;
import com.hrmanagement.dto.request.PricingDatesRequestDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
@FeignClient(url = "${openfeign.user-manager-url}", name = "auth-user")
public interface IUserManager {
    @PutMapping("/activate-status/{authId}")
    public ResponseEntity<Boolean> activateStatus(@PathVariable Long authId);

    @PostMapping("/activate-status")
    public ResponseEntity<Boolean> activateStatus(@RequestHeader(value = "Authorization") String token);

    @PostMapping("/pricing-dates")
    public ResponseEntity<Boolean> pricingDates(@RequestBody PricingDatesRequestDto dto);

}
