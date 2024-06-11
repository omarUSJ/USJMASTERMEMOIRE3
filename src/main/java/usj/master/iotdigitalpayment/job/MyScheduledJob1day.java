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
import org.springframework.web.client.RestTemplate;

import usj.master.iotdigitalpayment.dao.InitiateTransactionResponseRepository;
import usj.master.iotdigitalpayment.entity.InitiateTransactionResponse;
import usj.master.iotdigitalpayment.service.EmailService;

@Component
public class MyScheduledJob1day {

	@Autowired
	private EmailService emailService;
	
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
	        		System.out.println("sending email...");
	        		//not automatic
	        		emailService.sendEmail("omar_zarrade@hotmail.com", "expired", "Your Payment will be expired in 3 days");
	               }else {
	            	  
	            	   ResponseEntity<?> responseEntity = initiateTransactionsAPI(expiredTransactionsIn3Days.get(i));
	          
	            	   if(responseEntity.getStatusCode() == HttpStatus.OK) {
	            		   System.out.println("transaction is ready and done");
	            		   emailService.sendEmail("omar_zarrade@hotmail.com", "Payment", "Payment Done");
	            		   
	            	   }else {
	            		   System.out.println("else");
	            		   emailService.sendEmail("omar_zarrade@hotmail.com", "Failed", "Payment Failed");
	   
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

	 	        // Create RestTemplate
	 	        RestTemplate restTemplate = new RestTemplate();

	 	        // Make POST request to initiateTransactionRequestAPI
	 	        ResponseEntity<?> responseEntity = restTemplate.postForEntity("/api/transaction/initiateTransactionRequest", requestEntity, String.class);

	 	        System.out.println("responseEntity: "+responseEntity);
	 	        return responseEntity;
	 	    }
}
