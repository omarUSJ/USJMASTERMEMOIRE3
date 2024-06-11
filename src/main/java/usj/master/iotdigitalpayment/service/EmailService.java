package usj.master.iotdigitalpayment.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailService {

	@Autowired
    private JavaMailSender mailSender;

    public void sendEmail(String to, String subject, String body) {
    	System.out.println("start email...");
        SimpleMailMessage message = new SimpleMailMessage();
        System.out.println("to : "+ to);
        message.setTo(to);
        System.out.println("sub: "+subject);
        message.setSubject(subject);
        System.out.println("body: "+body);
        message.setText(body);

        mailSender.send(message);
    }
	
}
