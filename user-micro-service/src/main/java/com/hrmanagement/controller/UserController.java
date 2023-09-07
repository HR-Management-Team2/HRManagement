package com.hrmanagement.controller;

import com.hrmanagement.dto.request.UserCreateRequestDto;
import com.hrmanagement.dto.request.UserUpdateRequestDto;
import com.hrmanagement.repository.entity.User;
import com.hrmanagement.service.UserService;
import io.swagger.v3.oas.annotations.Hidden;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.hrmanagement.constants.ApiUrls.*;

@RestController
@RequiredArgsConstructor
@RequestMapping(USER_APP)
public class UserController {
    private final UserService userService;

    @PostMapping(CREATE_USER)
    public ResponseEntity<Boolean> createUserBasic(@RequestBody UserCreateRequestDto dto) {
        return ResponseEntity.ok(userService.createUserBasic(dto));
    }

    @PutMapping(UPDATE_USER)
    public ResponseEntity<Boolean> updateUser(String token, @RequestBody UserUpdateRequestDto dto){
        return ResponseEntity.ok(userService.updateUser(token, dto));
    }

    @DeleteMapping(DELETE_USER)
    public ResponseEntity<Boolean> deleteUser( Long authId){
        return ResponseEntity.ok(userService.deleteById(authId));
    }

    @GetMapping(FINDALL)
    public ResponseEntity<List<User>> findAll(){
        return ResponseEntity.ok(userService.findAll());
    }
    @Hidden
    @PutMapping(ACTIVATE_STATUS)
    public ResponseEntity<Boolean> activateStatus(@PathVariable Long authId){
        return ResponseEntity.ok(userService.activateStatus(authId));
    }

    @PutMapping(ACTIVATE_STATUS_MANAGER)
    public ResponseEntity<Boolean> activateStatusManager(Long authId){
        return ResponseEntity.ok(userService.activateStatusManager(authId));
    }
}
