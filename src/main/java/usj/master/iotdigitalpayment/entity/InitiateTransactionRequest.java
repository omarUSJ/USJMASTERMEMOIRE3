package usj.master.iotdigitalpayment.entity;

import java.time.LocalDate;


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
		private long transactionId;

		@Column(name="customer_id")
		private String customerId;

		@Column(name="account_id")
		private String accountId;

		@Column(name="account_currency")
		private String accountCurrency;

		@JsonSerialize(using = LocalDateTimeSerializer.class)
	//	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
		@Column(name="transaction_date_time")
		private LocalDate transactionDate;
		
		@Column(name="automatic")
		private String automatic;
		
		@Column(name="Percentage")
		private String Percentage;

		@Column(name="Email")
		private String email;

		public InitiateTransactionRequest(long transactionId, String customerId, String accountId,
				String accountCurrency, LocalDate transactionDate, String automatic, 
				String percentage, String email) {
			super();
			this.transactionId = transactionId;
			this.customerId = customerId;
			this.accountId = accountId;
			this.accountCurrency = accountCurrency;
			this.transactionDate = transactionDate;
			this.automatic = automatic;
			this.Percentage = percentage;
			this.email = email;
		}

		public long getTransactionId() {
			return transactionId;
		}

		public void setTransactionId(long transactionId) {
			this.transactionId = transactionId;
		}

		public void setTransactionDate(LocalDate transactionDate) {
			this.transactionDate = transactionDate;
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

		public LocalDate getTransactionDate() {
			return transactionDate;
		}

		public void setTransactionDateTime() {
			this.transactionDate = LocalDate.now();
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

		public String getEmail() {
			return email;
		}

		public void setEmail(String email) {
			this.email = email;
		}

		@Override
		public String toString() {
			return "InitiateTransactionRequest [transactionId=" + transactionId + ", customerId=" + customerId
					+ ", accountId=" + accountId + ", accountCurrency=" + accountCurrency + ", transactionDate="
					+ transactionDate + ", automatic=" + automatic + ", Percentage=" + Percentage + ", email=" + email
					+ "]";
		}
	}

