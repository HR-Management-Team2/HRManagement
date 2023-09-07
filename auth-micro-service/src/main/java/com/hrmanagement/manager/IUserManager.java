package com.hrmanagement.manager;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
@FeignClient(url = "${openfeign.user-manager-url}", name = "auth-userprofile")
public interface IUserManager {


    // @DeleteMapping("/delete-by-id/{authId}")
    // public ResponseEntity<Boolean> deleteUser(@PathVariable Long authId);

    @PutMapping("/activate-status/{authId}")
    public ResponseEntity<Boolean> activateStatus(@PathVariable Long authId);

}
