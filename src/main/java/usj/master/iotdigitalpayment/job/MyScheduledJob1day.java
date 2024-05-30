package usj.master.iotdigitalpayment.job;

import java.time.LocalDate; import java.util.List; 
import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import usj.master.iotdigitalpayment.dao.InitiateTransactionResponseRepository;
import usj.master.iotdigitalpayment.entity.InitiateTransactionResponse;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;

@Component
public class MyScheduledJob1day {

	@Autowired
	private InitiateTransactionResponseRepository initiateTransactionResponseRepository;

	//once a day
	//@Scheduled(cron = "0 0 0 * * *")
	@Scheduled(cron = "*/1 * * * * *") //once a second
	public void runJob() {
		 System.out.println("Running job at: " + LocalDate.now());      
	        
	        LocalDate threeDaysFromNow = LocalDate.now().plusDays(3);
	        List<InitiateTransactionResponse> expiredTransactionsIn3Days
	        			= initiateTransactionResponseRepository.findByTransactionDateTime(threeDaysFromNow);
	        
	        System.out.println("threeDaysFromNow: "+threeDaysFromNow);
	        
	        System.out.println("expiredTransactions: "+expiredTransactionsIn3Days);
	        
	        if (!expiredTransactionsIn3Days.isEmpty()) {
	        for (int i =0;i<expiredTransactionsIn3Days.size();i++) {
	        	if(expiredTransactionsIn3Days.get(i).getAutomatic().equals("0")) {
	        		//not automatic
	                //  		sendEmail("omar_zarrade@hotmail.com" ,"test" , "test");
	               }else {
	            	   System.out.println("testt");
	            	   ResponseEntity<?> responseEntity = initiateTransactionsAPI(expiredTransactionsIn3Days.get(i));
	            	   System.out.println("test2");
	            	   if(responseEntity.getStatusCode() == HttpStatus.OK) {
	            		   System.out.println("transaction is ready and done");
	            	   }else {
	            		   System.out.println("else");
	            		 //  sendEmail("omar_zarrade@hotmail.com" ,"test" , "test");
	            	   }
	               }
	        	}
	                
	        }
	        
	     }
	 	
	    public static void sendEmail(String recipient, String subject, String content) {
	        // Set up the SMTP server properties
	        Properties props = new Properties();
	        props.put("mail.smtp.auth", "true");
	        props.put("mail.smtp.starttls.enable", "true");
	        props.put("mail.smtp.host", "smtp.gmail.com");
	        props.put("mail.smtp.port", "587");

	        // Email configuration
	        final String senderEmail = "test";  
	        final String senderPassword = "test";  

	        // Get session
	        Session session = Session.getInstance(props, new javax.mail.Authenticator() {
	            protected PasswordAuthentication getPasswordAuthentication() {
	                return new PasswordAuthentication(senderEmail, senderPassword);
	            }
	        });

	        try {
	            // Compose the message
	            Message message = new MimeMessage(session);
	            message.setFrom(new InternetAddress(senderEmail));
	            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(recipient));
	            message.setSubject(subject);
	            message.setText(content);

	            // Send the message
	            Transport.send(message);

	            System.out.println("Email sent successfully");

	        } catch (MessagingException e) {
	            e.printStackTrace();
	        }
	    }
	 	 private ResponseEntity<?> initiateTransactionsAPI(InitiateTransactionResponse transactions) {
	 	        // Set up headers
	 	        HttpHeaders headers = new HttpHeaders();
	 	        headers.setContentType(MediaType.APPLICATION_JSON);

	 	        // Set up request entity
	 	        HttpEntity<InitiateTransactionResponse> requestEntity = new HttpEntity<>(transactions, headers);

	 	        // Create RestTemplate
	 	        RestTemplate restTemplate = new RestTemplate();

	 	        // Make POST request to initiateTransactionRequestAPI
	 	        ResponseEntity<?> responseEntity = restTemplate.postForEntity("/api/transaction/initiateTransactionRequest", requestEntity, String.class);

	 	        System.out.println("responseEntity: "+responseEntity);
	 	        return responseEntity;
	 	    }
}
