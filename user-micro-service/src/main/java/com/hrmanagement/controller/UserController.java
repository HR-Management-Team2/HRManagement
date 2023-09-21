package com.hrmanagement.controller;

import com.hrmanagement.dto.request.*;
import com.hrmanagement.dto.response.AdminProfileResponseDto;
import com.hrmanagement.dto.response.ManagerListResponseDto;
import com.hrmanagement.dto.response.UserResponseDto;
import com.hrmanagement.dto.response.EmployeeListResponseDto;
import com.hrmanagement.repository.entity.User;
import com.hrmanagement.service.UserService;
import io.swagger.v3.oas.annotations.Hidden;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

import static com.hrmanagement.constants.ApiUrls.*;

@RestController
@RequiredArgsConstructor
@RequestMapping(USER_APP)
@CrossOrigin("*")
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
    @PostMapping(ACTIVATE_STATUS)
    public ResponseEntity<Boolean> activateStatus(@RequestHeader(value = "Authorization") String token){
        return ResponseEntity.ok(userService.activateStatus(token));
    }

    @PutMapping(ACTIVATE_STATUS_MANAGER)
    public ResponseEntity<Boolean> activateStatusManager(Long authId){
        return ResponseEntity.ok(userService.activateStatusManager(authId));
    }
    @CrossOrigin("*")
    @PostMapping(ADD_EMPLOYEE)
    public ResponseEntity<Boolean> addEmployee(@RequestBody EmployeeCreateRequestDto dto) {
        return ResponseEntity.ok(userService.addEmployee(dto));
    }

    @GetMapping(FIND_USER)
    public ResponseEntity<AdminProfileResponseDto> findByUserDto(@PathVariable Long authId){
        return ResponseEntity.ok(userService.findUser(authId));
    }

    @CrossOrigin("*")
    @GetMapping(FIND_ALL_EMPLOYEE)
    public ResponseEntity<List<EmployeeListResponseDto>> findAllEmployee(String token){
        return ResponseEntity.ok(userService.findAllEmployee(token));
    }

    @CrossOrigin("*")
    @PutMapping(UPDATE_EMPLOYEE)
    public ResponseEntity<Boolean> updateEmployee(@RequestBody UpdateEmployeeRequestDto dto){
        return ResponseEntity.ok(userService.updateEmployee(dto));
    }
    @CrossOrigin("*")
    @GetMapping(FIND_ALL_MANAGER)
    public ResponseEntity<List<ManagerListResponseDto>> findAllManager(){
        return ResponseEntity.ok(userService.findAllManager());
    }

    @CrossOrigin("*")
    @PutMapping(UPDATE_MANAGER)
    public ResponseEntity<Boolean> updateManager(@RequestBody ManagerUpdateRequestDto dto){
        return ResponseEntity.ok(userService.updateManager(dto));
    }
    @CrossOrigin("*")
    @DeleteMapping(DELETE_MANAGER)
    public ResponseEntity<Boolean> deleteManager(@PathVariable Long authId){
        return ResponseEntity.ok(userService.deleteManager(authId));
    }


    @CrossOrigin("*")
    @PostMapping(IMAGE_UPLOAD)
    public ResponseEntity<String> updateImage(@RequestParam("file")MultipartFile file, @RequestParam("token") String token) throws IOException {
        return ResponseEntity.ok(userService.updateImage(file,token));
    }

    @PostMapping(UPDATE_USER_INFO)
    @CrossOrigin("*")
    public ResponseEntity<Boolean> updateUserInfo(@RequestBody UpdateUserInfoRequestDto dto){
        System.out.println("çalışıyor mu?");
        return ResponseEntity.ok(userService.updateUserInfo(dto));
    }





}
