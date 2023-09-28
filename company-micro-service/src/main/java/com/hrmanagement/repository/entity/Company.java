package com.hrmanagement.repository.entity;

import com.hrmanagement.repository.enums.Status;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import nonapi.io.github.classgraph.json.Id;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
public class Company extends Base{
    @Id
    String id;
    String name;
    String taxNumber;
    String phone;
    String address;
    String email;
    Integer numberOfWorkers;
    LocalDate yearOfEstablishment;
    String status;
}
