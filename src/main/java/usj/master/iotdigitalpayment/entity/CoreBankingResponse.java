package usj.master.iotdigitalpayment.entity;

public class CoreBankingResponse {

	 	private String token;
	    private String account;
	    private String amount;
	    private String amountCurrency;
	    private String pourcentage;
	    
		public String getToken() {
			return token;
		}
		public void setToken(String token) {
			this.token = token;
		}
		public String getAccount() {
			return account;
		}
		public void setAccount(String account) {
			this.account = account;
		}
		public String getAmount() {
			return amount;
		}
		public void setAmount(String amount) {
			this.amount = amount;
		}
		public String getAmountCurrency() {
			return amountCurrency;
		}
		public void setAmountCurrency(String amountCurrency) {
			this.amountCurrency = amountCurrency;
		}
		
		
		
		public String getPourcentage() {
			return pourcentage;
		}
		public void setPourcentage(String pourcentage) {
			this.pourcentage = pourcentage;
		}
		@Override
		public String toString() {
			return "CoreBankingResponse [token=" + token + ", account=" + account + ", amount=" + amount
					+ ", amountCurrency=" + amountCurrency + ", pourcentage=" + pourcentage + "]";
		}
	
		

	    
	
}
