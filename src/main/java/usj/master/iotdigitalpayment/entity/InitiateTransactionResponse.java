package usj.master.iotdigitalpayment.entity;

import java.time.LocalDate; 

import javax.persistence.*;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;



@Entity
@Table(name = "InitiateTransactionResponse")
public class InitiateTransactionResponse {
	
		@Id
		@Column(name="transaction_Id")
		private long transactionId;

		@Column(name="customer_id")
		private String customerId;

		@Column(name="account_id")
		private String accountId;

		@Column(name="account_currency")
		private String accountCurrency;

		@JsonSerialize(using = LocalDateSerializer.class)
		@Column(name="transaction_date_time")
		private LocalDate transactionDateTime;
	
		@Column(name="token")
		private String token;

		@Column(name="refresh_token")
		private String refresh_token;
		
		@Column(name="automatic")
		private String automatic;
		
		@Column(name="Percentage")
		private String Percentage;
		
		
		@Column(name="Email")
		private String email;

		@Column(name="mail_sent")
		private Boolean  mailSent = false;
		
		@Column(name="active")
		private Boolean  active = true;
		
		public InitiateTransactionResponse(long transactionId, String customerId, String accountId,
				String accountCurrency, LocalDate transactionDateTime, String token, String refresh_token,
				String automatic, String percentage, String email, Boolean  mailSent, Boolean active) {
			super();
			this.transactionId = transactionId;
			this.customerId = customerId;
			this.accountId = accountId;
			this.accountCurrency = accountCurrency;
			this.transactionDateTime = transactionDateTime;
			this.token = token;
			this.refresh_token = refresh_token;
			this.automatic = automatic;
			this.Percentage = percentage;
			this.email = email;
			this.mailSent= mailSent;
			this.active = active;
		}



		public long getTransactionId() {
			return transactionId;
		}



		public void setTransactionId(long transactionId) {
			this.transactionId = transactionId;
		}



		public void setTransactionDateTime(LocalDate transactionDateTime) {
			this.transactionDateTime = transactionDateTime;
		}



		public InitiateTransactionResponse() {
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

		public LocalDate getTransactionDateTime() {
			return transactionDateTime;
		}

		public void setTransactionDateTime() {
			this.transactionDateTime = LocalDate.now();
		}



		public String getToken() {
			return token;
		}



		public void setToken(String token) {
			this.token = token;
		}



		public String getRefresh_token() {
			return refresh_token;
		}



		public void setRefresh_token(String refresh_token) {
			this.refresh_token = refresh_token;
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
			this.Percentage = percentage;
		}



		public String getEmail() {
			return email;
		}



		public void setEmail(String email) {
			this.email = email;
		}



		public Boolean  isMailSent() {
			return mailSent;
		}



		public void setMailSent(Boolean  mailSent) {
			this.mailSent = mailSent;
		}



		public Boolean getActive() {
			return active;
		}



		public void setActive(Boolean active) {
			this.active = active;
		}



		public Boolean getMailSent() {
			return mailSent;
		}



		@Override
		public String toString() {
			return "InitiateTransactionResponse [transactionId=" + transactionId + ", customerId=" + customerId
					+ ", accountId=" + accountId + ", accountCurrency=" + accountCurrency + ", transactionDateTime="
					+ transactionDateTime + ", token=" + token + ", refresh_token=" + refresh_token + ", automatic="
					+ automatic + ", Percentage=" + Percentage + ", email=" + email + ", mailSent=" + mailSent
					+ ", active=" + active + "]";
		}





	




	}

