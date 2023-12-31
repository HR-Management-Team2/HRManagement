package com.hrmanagement.dto.response;

import com.hrmanagement.repository.enums.ERole;
import com.hrmanagement.repository.enums.EStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class LoginResponseDto {
    String token;
    ERole role;
    Long authId;
    EStatus status;
}
