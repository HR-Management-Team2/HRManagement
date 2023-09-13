package com.hrmanagement.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

import static org.springframework.http.HttpStatus.BAD_REQUEST;
import static org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public enum ErrorType {
    BAD_REQUEST_ERROR(1201, "Geçersiz Parametre Girişi Yaptınız", BAD_REQUEST),
    AUTH_PASSWORD_ERROR(1301, "Şifreler uyuşmuyor.", BAD_REQUEST),
    AUTH_EMAIL_ERROR(1302, "Bu e-posta zaten kayıtlı.", BAD_REQUEST),
    INTERNAL_ERROR(3000, "Sunucuda beklenmeyen hata", INTERNAL_SERVER_ERROR),
    KULLANICI_BULUNAMADI(2301, "Aradığınız id ye ait kullanıcı bulunamadı.", INTERNAL_SERVER_ERROR),
    INVALID_TOKEN(4001, "GEÇERSİZ TOKEN BİLGİSİ", BAD_REQUEST),
    AUTH_LOGIN_ERROR(4002, "KULLANICI ADI VEYA ŞİFRE HATALIDIR.", INTERNAL_SERVER_ERROR),
    COMPANY_HAS_BEEN(4003, "COMPANY_HAS_BEEN", INTERNAL_SERVER_ERROR),
    COMPANY_NOT_FOUND(4004,"Şirket bulunamadı.", HttpStatus.NOT_FOUND);

    private int code;
    private String message;
    private HttpStatus httpStatus;
}
