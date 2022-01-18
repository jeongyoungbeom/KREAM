package com.project.kream.Service;

import lombok.RequiredArgsConstructor;
import net.nurigo.java_sdk.api.Message;
import net.nurigo.java_sdk.exceptions.CoolsmsException;
import org.json.simple.JSONObject;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Random;

@Service
@RequiredArgsConstructor
public class PhoneService {

    public void sendSms(String phoneNum){

        Random rand  = new Random();
        String numStr = "";
        for(int i=0; i<4; i++) {
            String ran = Integer.toString(rand.nextInt(10));
            numStr+=ran;
        }
        certifiedPhoneNumber(phoneNum, numStr);
    }

    public void certifiedPhoneNumber(String phoneNumber, String cerNum) {
        String api_key = "NCSOHBW8VTOQVKUT";
        String api_secret = "HYH4CRNMFBK3FLC7AJWVHLBQTCQ0QYGL";
        Message coolsms = new Message(api_key, api_secret);

        HashMap<String, String> params = new HashMap<String, String>();
        params.put("to", phoneNumber);
        params.put("from", "01055275957");
        params.put("type", "SMS");
        params.put("text", "KREAM 사이트를 이용하세요. https://kream.co.kr/");
        params.put("app_version", "test app 1.2");
        try {
            JSONObject obj = coolsms.send(params);
            System.out.println(obj.toString());
        } catch (CoolsmsException e) {
            System.out.println(e.getMessage());
            System.out.println(e.getCode());
        }
    }
}
