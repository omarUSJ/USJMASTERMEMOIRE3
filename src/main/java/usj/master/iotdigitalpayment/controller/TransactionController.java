package usj.master.iotdigitalpayment.controller;

import java.time.LocalDate;  

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import usj.master.iotdigitalpayment.dao.InitiateTransactionRepository;
import usj.master.iotdigitalpayment.dao.InitiateTransactionResponseRepository;
import usj.master.iotdigitalpayment.entity.CoreBankingResponse;
import usj.master.iotdigitalpayment.entity.InitiateTransactionRequest;
import usj.master.iotdigitalpayment.entity.InitiateTransactionRequestAPI;
import usj.master.iotdigitalpayment.entity.InitiateTransactionResponse;
import usj.master.iotdigitalpayment.entity.RefreshToken;
import usj.master.iotdigitalpayment.security.jwt.JwtUtils;

import usj.master.iotdigitalpayment.service.RefreshTokenService;
 

@RestController
@RequestMapping("/api/transaction")
public class TransactionController {

	private static final Logger log = LoggerFactory.getLogger(TransactionController.class);
	
	@Autowired
	private InitiateTransactionRepository initiateTransactionRepository;

	@Autowired
	private InitiateTransactionResponseRepository initiateTransactionResponseRepository;

	
	@Autowired
	JwtUtils jwtUtils;
	 
	@Autowired
	RefreshTokenService refreshTokenService;
	 

	private final RestTemplate restTemplate;
	
	
	@Autowired
	public TransactionController(RestTemplate restTemplate) {
		
		this.restTemplate = restTemplate;
		
	}

	@PostMapping("/initiateTransactionRequest")
	public ResponseEntity<?> initiateTransactionRequestAPI (@RequestBody InitiateTransactionRequestAPI initiateTransactionRequestAPI) {

		try {
		log.info("TransactionController::initiateTransaction: " + initiateTransactionRequestAPI.toString());
		InitiateTransactionRequest initiateTransactionRequest = new InitiateTransactionRequest();

		initiateTransactionRequest.setCustomerId(initiateTransactionRequestAPI.getCustomerId());
		initiateTransactionRequest.setAccountId(initiateTransactionRequestAPI.getAccountId());
		initiateTransactionRequest.setAccountCurrency(initiateTransactionRequestAPI.getAccountCurrency());

		
		initiateTransactionRequest.setTransactionId(5);
		
		initiateTransactionRequest = initiateTransactionRepository.save(initiateTransactionRequest);

		String jwt = jwtUtils.generateJwtToken(initiateTransactionRequest.getCustomerId().toString());
		RefreshToken refreshToken = refreshTokenService.createRefreshToken(initiateTransactionRequest.getCustomerId().toString());

		// Get today's date
        LocalDate today = LocalDate.now();

        // Add 30 days to today's date
        LocalDate expiryDate = today.plusDays(30);
    
		InitiateTransactionResponse initiateTransactionResponse = 
				new InitiateTransactionResponse(
						initiateTransactionRequest.getTransactionId(),
						initiateTransactionRequest.getCustomerId(), initiateTransactionRequest.getAccountId(),
						initiateTransactionRequest.getAccountCurrency(), 
						expiryDate, 
						 jwt, 
						 refreshToken.toString(),
						 initiateTransactionRequest.getAutomatic(),
						 initiateTransactionRequest.getPercentage());
		
		initiateTransactionResponse = initiateTransactionResponseRepository.save(initiateTransactionResponse);
		
		log.info("TransactionController::initiateTransaction: Created ");

		callExternalAPIOk(initiateTransactionResponse);
		
		//callExternalAPINo(initiateTransactionResponse);
		//return ResponseEntity.status(HttpStatus.NOT_FOUND)
		//.body("NOT_FOUND");
		
		return ResponseEntity.status(HttpStatus.OK)
				.body("OK");
		
		}catch(Exception ex) {
			log.info("TransactionController::initiateTransaction: failed ");
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
					.body(ex.getStackTrace());
		
	}
	}
	
	@PostMapping("https://masterusj.free.beeceptor.com/corebanking/ok")
    public CoreBankingResponse callExternalAPIOk(@RequestBody InitiateTransactionResponse requestObject) {
		
		log.info("TransactionController::Call Core Banking: ");
		
        String url = "https://masterusj.free.beeceptor.com/corebanking";
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<InitiateTransactionResponse> requestEntity = new HttpEntity<>(requestObject, headers);

        // Make the POST request and get the response
        CoreBankingResponse responseObject = restTemplate.postForObject(url, requestEntity, CoreBankingResponse.class);

    	log.info("TransactionController::Call Core Banking: " + responseObject.toString());
        
       
        return responseObject;
    }
	@PostMapping("https://masterusj.free.beeceptor.com/corebanking/no")
    public CoreBankingResponse callExternalAPINo(@RequestBody InitiateTransactionResponse requestObject) {
		
		log.info("TransactionController::Call Core Banking: ");
		
        String url = "https://masterusj.free.beeceptor.com/corebanking";
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<InitiateTransactionResponse> requestEntity = new HttpEntity<>(requestObject, headers);

        // Make the POST request and get the response
        CoreBankingResponse responseObject = restTemplate.postForObject(url, requestEntity, CoreBankingResponse.class);

    	log.info("TransactionController::Call Core Banking: " + responseObject.toString());
        
        
        return responseObject;
    }
}
