package com.hrmanagement.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class RegisterRequestDto {
    @NotEmpty(message = "Ad alanını boş bırakmayınız.")
    @Size(min = 3, max = 20, message = "Adınız en az 2 en fazla 20 karakter olabilir.")
    private String name;
    @NotEmpty(message = "Soyadı alanını boş bırakmayınız.")
    @Size(min = 3, max = 20, message = "Soyadınız en az 2 en fazla 20 karakter olabilir.")
    private String surname;
    @Email(message = "Lütfen Geçerli bir e-posta adresi giriniz ")
    String email;
    @NotEmpty(message = "Şifreyi boş geçemezsiniz")
    @Size(min = 8,max = 64, message = "Şifre 8 ile 64 karakter arasında olmalıdır.")
    @Pattern(
            message = "Şifre enaz 8 karakter olmalı, içinde en az bir büyük bir küçük harf sayı ve rakam olmalıdır.",
            regexp = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=*!])(?=\\S+$).{8,}$")
    String password;
    @NotEmpty(message = "Şifreyi boş geçemezsiniz")
    @Size(min = 8,max = 64, message = "Şifre 8 ile 64 karakter arasında olmalıdır.")
    @Pattern(
            message = "Şifre enaz 8 karakter olmalı, içinde en az bir büyük bir küçük harf sayı ve rakam olmalıdır.",
            regexp = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=*!])(?=\\S+$).{8,}$")
    String passwordConfirm;


}
