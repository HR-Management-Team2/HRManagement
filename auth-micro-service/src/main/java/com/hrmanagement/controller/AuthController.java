package com.hrmanagement.controller;

import com.hrmanagement.dto.request.ActivateRequestDto;
import com.hrmanagement.dto.request.AuthUpdateRequestDto;
import com.hrmanagement.dto.request.LoginRequestDto;
import com.hrmanagement.dto.request.RegisterRequestDto;
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
public class AuthController {
    private final AuthService authService;


    @PostMapping(REGISTER)
    public ResponseEntity<RegisterResponseDto> register(@RequestBody @Valid RegisterRequestDto dto){
        return ResponseEntity.ok(authService.doRegister(dto));
    }

    @PostMapping(LOGIN)
    public ResponseEntity<String> login(@RequestBody LoginRequestDto dto){
        return ResponseEntity.ok(authService.doLogin(dto));
    }

    @PostMapping(ACTIVATE_STATUS)
    public ResponseEntity<Boolean> activateStatus(@RequestBody ActivateRequestDto dto){
        return ResponseEntity.ok(authService.activateStatus(dto));
    }

    @Hidden
    @PutMapping(UPDATE)
    public ResponseEntity<Boolean> updateAuth(@RequestBody AuthUpdateRequestDto dto){
        return ResponseEntity.ok(authService.updateAuth(dto));
    }

}
