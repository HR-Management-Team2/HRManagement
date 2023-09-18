package com.hrmanagement.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/fallback")
@CrossOrigin("*")
public class FallBackController {
    /**
     * CircuitBreaker --> Bu bir devre kesicidir. Gateway' e gelen isteklerde bir sorun olduğunda servisler için bir mesaj döner.
     * Hataları tespit ederek kullanıcıya hata dönmesi yerien bir mesaj dönmesini sağlar.
     */

    @GetMapping("/auth-service")
    public ResponseEntity<String> authServiceFallback(){
        return ResponseEntity.ok("Auth service şu anda hizmet verememektedir. Lütfen daha sonra tekrar deneyiniz.");
    }

    @GetMapping("/user-service")
    public ResponseEntity<String> userProfileServiceFallback(){
        return ResponseEntity.ok("Userprofile service şu anda hizmet verememektedir. Lütfen daha sonra tekrar deneyiniz.");
    }

    @GetMapping("/mail-service")
    public ResponseEntity<String> mailServiceFallback(){
        return ResponseEntity.ok("Mail service şu anda hizmet verememektedir. Lütfen daha sonra tekrar deneyiniz.");
    }


    @GetMapping("/company-service")
    public ResponseEntity<String> companyServiceFallback(){
        return ResponseEntity.ok("Company service şu anda hizmet verememektedir. Lütfen daha sonra tekrar deneyiniz.");
    }

}
