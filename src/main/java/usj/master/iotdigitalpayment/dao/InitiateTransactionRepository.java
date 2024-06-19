package usj.master.iotdigitalpayment.dao;

import org.springframework.data.repository.CrudRepository;


import usj.master.iotdigitalpayment.entity.InitiateTransactionRequest;

public interface InitiateTransactionRepository extends CrudRepository<InitiateTransactionRequest, Long> {

	
		InitiateTransactionRequest findByTransactionId(Long transactionId);


}
