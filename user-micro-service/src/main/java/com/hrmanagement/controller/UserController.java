package com.hrmanagement.controller;

import com.hrmanagement.dto.request.EmployeeCreateRequestDto;
import com.hrmanagement.dto.request.UpdateEmployeeRequestDto;
import com.hrmanagement.dto.request.UserCreateRequestDto;
import com.hrmanagement.dto.request.UserUpdateRequestDto;
import com.hrmanagement.dto.response.AdminProfileResponseDto;
import com.hrmanagement.dto.response.UserResponseDto;
import com.hrmanagement.dto.response.EmployeeListResponseDto;
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

}
