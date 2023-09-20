package com.hrmanagement.controller;

import com.hrmanagement.dto.request.*;
import com.hrmanagement.dto.response.LoginResponseDto;
import com.hrmanagement.dto.response.RegisterResponseDto;
import com.hrmanagement.service.AuthService;
import io.swagger.v3.oas.annotations.Hidden;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

import static com.hrmanagement.constant.ApiUrls.*;

@RestController
@RequiredArgsConstructor
@RequestMapping(AUTH)
@CrossOrigin("*")
public class AuthController {
    private final AuthService authService;


    @PostMapping(REGISTER)
    @CrossOrigin("*")
    public ResponseEntity<RegisterResponseDto> register(@RequestBody @Valid RegisterRequestDto dto){
        return ResponseEntity.ok(authService.doRegister(dto));
    }

    @PostMapping(REGISTER_MANAGER)
    @CrossOrigin("*")
    public ResponseEntity<RegisterResponseDto> registerManager(@RequestBody @Valid ManagerRegisterRequestDto dto){
        return ResponseEntity.ok(authService.doRegisterManager(dto));
    }

    @PostMapping(LOGIN)
    @CrossOrigin("*")
    public ResponseEntity<LoginResponseDto> login(@RequestBody LoginRequestDto dto){
        return ResponseEntity.ok(authService.doLogin(dto));
    }

    /*@PostMapping(ACTIVATE_STATUS)
    public ResponseEntity<Boolean> activateStatus(@RequestBody ActivateRequestDto dto){
        return ResponseEntity.ok(authService.activateStatus(dto));
    }*/

    @GetMapping(ACTIVATE_STATUS)
    public ResponseEntity<Boolean> activateStatus(@RequestParam String token){
        System.out.println("AuthController activateStatus' e gelen token: " + token);
        return ResponseEntity.ok(authService.activateStatus(token));
    }

    @Hidden
    @PutMapping(UPDATE)
    public ResponseEntity<Boolean> updateAuth(@RequestBody AuthUpdateRequestDto dto){
        return ResponseEntity.ok(authService.updateAuth(dto));
    }

    @Hidden
    @PutMapping(UPDATE_EMPLOYEE)
    public ResponseEntity<Boolean> updateAuthEmployee(@RequestBody AuthEmployeeUpdateRequestDto dto){
        return ResponseEntity.ok(authService.updateAuthEmployee(dto));
    }

    @Hidden
    @PutMapping(ACTIVATE_STATUS_MANAGER)
    public ResponseEntity<Boolean> activateStatusManager(@PathVariable Long id){
        return ResponseEntity.ok(authService.activateStatusManager(id));
    }

    @PostMapping(CREATE_ADMIN)
    public ResponseEntity<Boolean> createAdmin(@RequestBody CreateAdminRequestDto dto){
        return ResponseEntity.ok(authService.createAdmin(dto));
    }
    @PutMapping(UPDATE_MANAGER)
    public ResponseEntity<Boolean> updateAuthManager(@RequestBody AuthManagerUpdateRequestDto dto){
        return ResponseEntity.ok(authService.updateAuthManager(dto));
    }
    @DeleteMapping(DELETE_MANAGER)
    public ResponseEntity<Boolean> deleteAuthManager(@PathVariable Long id){
        return ResponseEntity.ok(authService.deleteManager(id));
    }

}
