package com.project.kream.Controller.RestController;

import com.project.kream.Service.PhoneService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class PhoneRestController {
    private final PhoneService phoneService;

    @GetMapping("/api/send_sms/{phoneNum}")
    public void sendSMS(@PathVariable String phoneNum){
        phoneService.sendSms(phoneNum);
    }
}
