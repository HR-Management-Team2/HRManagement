package com.hrmanagement.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class UpdateUserInfoRequestDto {
    String name;
    String surname;
    String email;
    MultipartFile image;
    String token;
}
