package usj.master.iotdigitalpayment.entity;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;


@Entity
@Table(name = "initiated_transactions")
public class InitiateTransactionRequest {
	
		@Id
		//@GeneratedValue(strategy = GenerationType.AUTO)
		@Column(name="transaction_id")
		private int transactionId;

		@Column(name="customer_id")
		private String customerId;

		@Column(name="account_id")
		private String accountId;

		@Column(name="account_currency")
		private String accountCurrency;

		@JsonSerialize(using = LocalDateTimeSerializer.class)
	//	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
		@Column(name="transaction_date_time")
		private LocalDateTime transactionDateTime;
		
		@Column(name="automatic")
		private String automatic;
		
		@Column(name="Percentage")
		private String Percentage;

		public InitiateTransactionRequest(int transactionId, String customerId, String accountId,
				String accountCurrency, LocalDateTime transactionDateTime, String automatic, String percentage) {
			super();
			this.transactionId = transactionId;
			this.customerId = customerId;
			this.accountId = accountId;
			this.accountCurrency = accountCurrency;
			this.transactionDateTime = transactionDateTime;
			this.automatic = automatic;
			this.Percentage = percentage;
		}

		public int getTransactionId() {
			return transactionId;
		}

		public void setTransactionId(int transactionId) {
			this.transactionId = transactionId;
		}

		public void setTransactionDateTime(LocalDateTime transactionDateTime) {
			this.transactionDateTime = transactionDateTime;
		}

		public InitiateTransactionRequest() {
			setTransactionDateTime();
		}

		public String getCustomerId() {
			return customerId;
		}

		public void setCustomerId(String customerId) {
			this.customerId = customerId;
		}

		public String getAccountId() {
			return accountId;
		}

		public void setAccountId(String accountId) {
			this.accountId = accountId;
		}

		public String getAccountCurrency() {
			return accountCurrency;
		}

		public void setAccountCurrency(String accountCurrency) {
			this.accountCurrency = accountCurrency;
		}

		public LocalDateTime getTransactionDateTime() {
			return transactionDateTime;
		}

		public void setTransactionDateTime() {
			this.transactionDateTime = LocalDateTime.now();
		}

		public String getAutomatic() {
			return automatic;
		}

		public void setAutomatic(String automatic) {
			this.automatic = automatic;
		}

		public String getPercentage() {
			return Percentage;
		}

		public void setPercentage(String percentage) {
			Percentage = percentage;
		}

		@Override
		public String toString() {
			return "InitiateTransactionRequest [transactionId=" + transactionId + ", customerId=" + customerId
					+ ", accountId=" + accountId + ", accountCurrency=" + accountCurrency + ", transactionDateTime="
					+ transactionDateTime + ", automatic=" + automatic + ", Percentage=" + Percentage + "]";
		}

	}

