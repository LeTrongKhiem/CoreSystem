package com.example.backendapi.Service;

import com.example.backendapi.Abstractions.IEmailService;
import com.example.backendapi.Model.AppSettings;
import com.example.backendapi.Model.User;
import com.example.backendapi.Model.VerificationToken;
import com.example.backendapi.ModelMapping.ExchangedBookModel;
import com.example.backendapi.Repository.VerificationTokenRepository;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

import java.io.UnsupportedEncodingException;

@Component
public class EmailService implements IEmailService {
    @Autowired
    private Environment environment;
    @Autowired
    private AppSettings appSettings;
    @Autowired
    private JavaMailSender mailSender;
    @Autowired
    private VerificationTokenRepository verificationTokenRepository;
    @Override
    public void sendMailVerification(User user, String siteURL) throws MessagingException, UnsupportedEncodingException {
        String toAddress = user.getEmail();
        String fromAddress = appSettings.getEmail();
        String subject = "Please verify your registration";
        String content = "Dear [[name]],<br>"
                + "Please click the link below to verify your registration:<br>"
                + "<h3><a href=\"[[URL]]\" target=\"_self\">VERIFY</a></h3>"
                + "Thank you,<br>";
        MimeMessage message = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message);

        helper.setFrom(fromAddress);
        helper.setTo(toAddress);
        helper.setSubject(subject);

        content = content.replace("[[name]]", user.getFirstName() + " " + user.getLastName());
        VerificationToken token = verificationTokenRepository.findByUser(user);
        String verifyURL = siteURL + "verify?code=" + token.getToken();

        content = content.replace("[[URL]]", verifyURL);

        helper.setText(content, true);

        mailSender.send(message);
    }

    @Override
    public void sendMailVerification(ExchangedBookModel model) throws MessagingException, UnsupportedEncodingException {
        String toAddress = model.getEmailUserCreated();
        String fromAddress = appSettings.getEmail();
        String subject = "Vui long xac nhan trao doi sach";
        String content = "Xin chao [[name]],<br>"
                + "Ten sach duoc yeu cau trao doi : [[book]]<br>"
                + "Day la thong tin cua nguoi can trao doi sach: <br>"
                + "Ten nguoi yeu cau: [[nameRequest]] <br>"
                + "Email nguoi yeu cau: [[emailRequest]] <br>"
                + "Dien thoai nguoi yeu cau: [[phoneRequest]] <br>";
        MimeMessage message = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message);

        helper.setFrom(fromAddress);
        helper.setTo(toAddress);
        helper.setSubject(subject);

        content = content.replace("[[name]]", model.getNameUserCreated());
        content = content.replace("[[book]]", model.getNameBook());
        content = content.replace("[[nameRequest]]", model.getNameUserRequest());
        content = content.replace("[[emailRequest]]", model.getNameEmailRequest());
        content = content.replace("[[phoneRequest]]", model.getPhoneNumber());

        helper.setText(content, true);

        mailSender.send(message);
    }
}
