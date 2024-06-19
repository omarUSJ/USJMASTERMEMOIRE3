package usj.master.iotdigitalpayment.job;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.RestTemplate;

import usj.master.iotdigitalpayment.dao.InitiateTransactionResponseRepository;
import usj.master.iotdigitalpayment.entity.InitiateTransactionResponse;
import usj.master.iotdigitalpayment.service.EmailService;
import usj.master.iotdigitalpayment.service.EncryptionService;
import usj.master.iotdigitalpayment.service.InitiateTransactionResponseService;

@Component
public class MyScheduledJob1day {

	@Autowired
	private EmailService emailService;
	
	@Autowired
	private InitiateTransactionResponseRepository initiateTransactionResponseRepository;

	@Autowired
    private InitiateTransactionResponseService initiateTransactionResponseService;
	
	 @Autowired
	 private EncryptionService encryptionService;
	
	//once a day
	//@Scheduled(cron = "0 0 0 * * *")
	@Scheduled(cron = "*/5 * * * * *") //every 5 secs
	public void runJob() {
		 System.out.println("Running job at: " + LocalDate.now());      
	        
		 //take alll trans that will be expired in 3 days
	        LocalDate threeDaysFromNow = LocalDate.now().plusDays(3);
	        List<InitiateTransactionResponse> expiredTransactionsIn3Days
	        			= initiateTransactionResponseRepository.findByTransactionDateTime(threeDaysFromNow);
	       
	       //print this trans
	       System.out.println("threeDaysFromNow: "+threeDaysFromNow);
	        
	       //print date in 3 days
	        System.out.println("expiredTransactions: "+expiredTransactionsIn3Days);
	         
	        //if we had a trans
	        if (!expiredTransactionsIn3Days.isEmpty()) {
	        	
	        for (int i =0;i<expiredTransactionsIn3Days.size();i++) {
	        	//if mail already sent or trans is inactive
	        	if(expiredTransactionsIn3Days.get(i).isMailSent() == true || expiredTransactionsIn3Days.get(i).getActive() == false) {
	        		System.out.println("mail already sent: " );
	   	         
	        	}else {
	        		//not automatic
	        	if(expiredTransactionsIn3Days.get(i).getAutomatic().equals("0") ) {
	        		System.out.println("sending email...");
	        		
	        		emailService.sendEmail(expiredTransactionsIn3Days.get(i).getEmail(), "expired", "Your Payment will be expired in 3 days");
	        		initiateTransactionResponseService.markTransactionAsMailSent(expiredTransactionsIn3Days.get(i).getTransactionId());
	        		initiateTransactionResponseService.activeSent(expiredTransactionsIn3Days.get(i).getTransactionId());
	        	}else {
	            	  //automatic trans
	        	
	               ResponseEntity<?> responseEntity = initiateTransactionsAPI(expiredTransactionsIn3Days.get(i));
	        		
	            	   if(responseEntity.getStatusCode() == HttpStatus.OK) {
	            		   System.out.println("transaction is ready and well be done in 3 days");
	            		   emailService.sendEmail(expiredTransactionsIn3Days.get(i).getEmail(), "Payment", "transaction is ready and well be done in 3 days");
	            		   initiateTransactionResponseService.markTransactionAsMailSent(expiredTransactionsIn3Days.get(i).getTransactionId());
	            		   initiateTransactionResponseService.activeSent(expiredTransactionsIn3Days.get(i).getTransactionId());
	            	   }else {
	            		   
	            		   emailService.sendEmail(expiredTransactionsIn3Days.get(i).getEmail(), "Failed", "Payment Failed");
	            		   initiateTransactionResponseService.markTransactionAsMailSent(expiredTransactionsIn3Days.get(i).getTransactionId());
	            		   initiateTransactionResponseService.activeSent(expiredTransactionsIn3Days.get(i).getTransactionId());
	            	   }
	               }
	        	}
	                
	        }
	        }
	        }
	        
	     
	 	 private ResponseEntity<?> initiateTransactionsAPI(InitiateTransactionResponse transactions) {
	 		// Set up headers
	 	    HttpHeaders headers = new HttpHeaders();
	 	    headers.setContentType(MediaType.APPLICATION_JSON);
	 	    
	 	    // Set up request entity
	 	    HttpEntity<InitiateTransactionResponse> requestEntity = new HttpEntity<>(transactions, headers);

	 	    // Log request details
	 	    System.out.println("Request Entity: " + requestEntity);

	 	    // Create RestTemplate
	 	    RestTemplate restTemplate = new RestTemplate();

	 	    try {
	 	        // Make POST request to initiateTransactionRequestAPI
	 	       ResponseEntity<?> responseEntity = restTemplate.postForEntity("http://localhost:8080/usjMaster/api/transaction/initiateTransactionRequest", requestEntity, String.class);
	 	        
	 	        return responseEntity;
	 	    } catch (HttpServerErrorException e) {
	 	        System.err.println("HTTP Server Error: " + e.getStatusCode() + " : " + e.getResponseBodyAsString());
	 	        throw e; 
	 	    } catch (Exception e) {
	 	        System.err.println("Exception: " + e.getMessage());
	 	        throw e;
	 	    }
	 	    }
}
