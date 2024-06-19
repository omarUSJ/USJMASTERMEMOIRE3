package usj.master.iotdigitalpayment.service;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import usj.master.iotdigitalpayment.dao.InitiateTransactionResponseRepository;
import usj.master.iotdigitalpayment.entity.InitiateTransactionResponse;

@Service
public class InitiateTransactionResponseService {

	  @Autowired
	  private InitiateTransactionResponseRepository initiateTransactionResponseRepository;

	    @Transactional
	    public void markTransactionAsMailSent(Long trans) {
	    	
	        InitiateTransactionResponse transaction = initiateTransactionResponseRepository.findByTransactionId(trans);
	           
	        	transaction.setMailSent(true);
	        	
	            initiateTransactionResponseRepository.save(transaction);	
	          
	    }
	    
	    @Transactional
	    public void activeSent(Long trans) {
	    	initiateTransactionResponseRepository.activeSent(trans);
	        
	    }
	
	 
}
