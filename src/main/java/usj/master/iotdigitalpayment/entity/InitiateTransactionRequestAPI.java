package usj.master.iotdigitalpayment.entity;

public class InitiateTransactionRequestAPI {
	
	
		private String customerId;

		private String accountId;

		private String accountCurrency;

		private String automatic;
		
		private String percentage;
		
		private String email;
		
		public InitiateTransactionRequestAPI() {
			super();
		}

		public InitiateTransactionRequestAPI(String customerId, String accountId, String accountCurrency,
				String automatic, String percentage, String email) {
			super();
			this.customerId = customerId;
			this.accountId = accountId;
			this.accountCurrency = accountCurrency;
			this.automatic = automatic;
			this.percentage = percentage;
			this.email = email;
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

		public String getAutomatic() {
			return automatic;
		}

		public void setAutomatic(String automatic) {
			this.automatic = automatic;
		}

		public String getPercentage() {
			return percentage;
		}

		public void setPercentage(String percentage) {
			this.percentage = percentage;
		}

		
		
		public String getEmail() {
			return email;
		}

		public void setEmail(String email) {
			this.email = email;
		}

		@Override
		public String toString() {
			return "InitiateTransactionRequestAPI [customerId=" + customerId + ", accountId=" + accountId
					+ ", accountCurrency=" + accountCurrency + ", automatic=" + automatic + ", percentage=" + percentage
					+ ", email=" + email + "]";
		}

		

	}

