package com.hrmanagement.dto.request;

import com.hrmanagement.repository.enums.EPermissionType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class CreatePermissionRequestDto {
    String token;
    EPermissionType ePermissionType;
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    LocalDate startDate;
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    LocalDate endDate;
    LocalDate dateOfRequest;
}
