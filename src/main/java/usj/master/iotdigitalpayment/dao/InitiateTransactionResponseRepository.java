package usj.master.iotdigitalpayment.dao;



import java.time.LocalDate;
import java.util.List;
import org.springframework.data.repository.CrudRepository; 

import usj.master.iotdigitalpayment.entity.InitiateTransactionResponse;


public interface InitiateTransactionResponseRepository extends CrudRepository<InitiateTransactionResponse, Long>{
	
	List<InitiateTransactionResponse> findByTransactionDateTime(LocalDate expirationDate);
  
	
	  InitiateTransactionResponse findByTransactionId(Long transactionId);


}
