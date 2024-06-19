package usj.master.iotdigitalpayment.dao;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public class CustomInitiateTransactionResponseRepositoryImpl implements CustomInitiateTransactionResponseRepository {
	@PersistenceContext
    private EntityManager entityManager;

    @Override
    @Transactional
	public void activeSent(long transId) {
    	Query query = entityManager.createQuery(
    			
                "UPDATE InitiateTransactionResponse t SET t.active = 'false' WHERE t.transactionId = :transactionId");
     
            query.setParameter("transactionId", transId);
            query.executeUpdate();
	}

}
