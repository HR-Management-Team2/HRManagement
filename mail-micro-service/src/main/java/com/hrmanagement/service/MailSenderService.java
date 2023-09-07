package com.hrmanagement.service;

import com.hrmanagement.rabbitmq.model.MailRegisterModel;
import lombok.RequiredArgsConstructor;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class MailSenderService {
    private final JavaMailSender javaMailSender;

    public void sendRegisterUsersInfo(MailRegisterModel mailRegisterModel){
        //SimpleMailMessage' a alternatif olarak MimeMessage araştırılabilir.
        SimpleMailMessage mailMessage = new SimpleMailMessage();
        mailMessage.setFrom("${spring.mail.username}"); //sunucu olarak kullanılacak mail
        mailMessage.setTo(mailRegisterModel.getEmail()); //kullanıcının girmiş olduğu mail
        mailMessage.setSubject("DOGRULAMA KODU");
        mailMessage.setText(
                "Tebrikler, başarıyla kayıt oldunuz. Aktivasyon kodunuz aşağıda gönderilmiştir..\n"
                        + "\nDoğrulama Kodu: " + mailRegisterModel.getActivationCode()
        );
        javaMailSender.send(mailMessage);
    }


}
