package com.hrmanagement.controller;

import com.hrmanagement.dto.request.UserCreateRequestDto;
import com.hrmanagement.dto.request.UserUpdateRequestDto;
import com.hrmanagement.repository.entity.User;
import com.hrmanagement.service.UserService;
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
    public ResponseEntity<Boolean> createUser(@RequestBody UserCreateRequestDto dto) {
        return ResponseEntity.ok(userService.createUser(dto));
    }

    @PutMapping(UPDATE_USER)
    public ResponseEntity<Boolean> updateUser( @RequestBody UserUpdateRequestDto dto){
        return ResponseEntity.ok(userService.updateUser(dto));
    }

    @DeleteMapping(DELETE_USER)
    public ResponseEntity<Boolean> deleteUser( Long authId){
        return ResponseEntity.ok(userService.deleteById(authId));
    }

    @GetMapping(FINDALL)
    public ResponseEntity<List<User>> findAll(){
        return ResponseEntity.ok(userService.findAll());
    }
}
