package com.hrmanagement.service;

import com.hrmanagement.rabbitmq.model.MailActivateModel;
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
        SimpleMailMessage mailMessage = new SimpleMailMessage();
        mailMessage.setFrom("${spring.mail.username}"); //sunucu olarak kullanılacak mail
        mailMessage.setTo(mailRegisterModel.getEmail()); //kullanıcının girmiş olduğu mail
        mailMessage.setSubject("AKTIVASYON KODU");
        mailMessage.setText(
                mailRegisterModel.getName() + " " + mailRegisterModel.getSurname() + " başarıyla kayıt oldunuz.\n" +
                        "Aktivasyon Link: " + "http://34.155.188.71/auth/activate-status?token="+mailRegisterModel.getToken()

        );
        javaMailSender.send(mailMessage);
    }

    public void sendActivateUserInfo(MailActivateModel mailModel) {
        SimpleMailMessage mailMessage = new SimpleMailMessage();
        mailMessage.setFrom("${spring.mail.username}");
        mailMessage.setTo(mailModel.getEmail());
        mailMessage.setSubject("Hesap Aktivasyonu");
        mailMessage.setText(
                mailModel.getName() + " " + mailModel.getSurname() + " hesabınız aktif edilmiştir. Giriş bilgileriniz aşağıdaki gibidir:\n"
                + "E-posta: " + mailModel.getEmail()
                + "\nŞifre: " + mailModel.getPassword()
        );
        javaMailSender.send(mailMessage);
    }



}
