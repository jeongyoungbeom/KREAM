package com.project.kream.Controller.RestController;

import com.project.kream.Model.Entity.Mail;
import com.project.kream.Service.MailService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
public class MailController {
    @Autowired
    private final MailService mailService;

    // 인증메일
    @GetMapping("/api/sendMail/{email}")
    public Mail execMail(@PathVariable String email) {
        return mailService.execMail(email);
    }

}
