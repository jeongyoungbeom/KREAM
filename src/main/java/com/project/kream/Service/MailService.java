package com.project.kream.Service;

import com.project.kream.Model.Entity.Customer;
import com.project.kream.Model.Entity.Mail;
import com.project.kream.Repository.CustomerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Component
@Service
@RequiredArgsConstructor
public class MailService {

    @Autowired
    private JavaMailSender mailSender;
    private final CustomerRepository customerRepository;
    private static final String FROM_ADDRESS = "kream@kream.com";
    private final PasswordEncoder passwordEncoder;

    @Transactional
    public void sendMail(Mail mail) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(mail.getAddress());
        message.setFrom(MailService.FROM_ADDRESS);
        message.setSubject(mail.getTitle());
        message.setText(mail.getMessage());

        mailSender.send(message);
    }

    public Mail execMail(String email){
        Mail mail = new Mail();
        String password = getTempPassword();
        mail.setAddress(email);
        mail.setTitle("[KREAM] 임시비밀번호 발송 안내 이메일 입니다.");
        mail.setMessage("안녕하세요.\n임시비밀번호 안내 관련 이메일 입니다.\n\n" + "[ " + email + " ] 님의 임시 비밀번호는\n"
                + password + " 입니다." + "\n");
        sendMail(mail);
        Optional<Customer> optionalCustomer = customerRepository.findByEmail(email);
        Customer customer = optionalCustomer.get();
        customer.setUserpw(passwordEncoder.encode(password));
        customerRepository.save(customer);
        return mail;
    }

    public String getTempPassword(){
        char[] charSet = new char[] { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F',
                'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z' ,'!', '@', '#', '$', '%', '^', '&', '*'};

        String str = "";

        int idx = 0;
        for (int i = 0; i < 10; i++) {
            idx = (int) (charSet.length * Math.random());
            str += charSet[idx];
        }
        return str;
    }

}
