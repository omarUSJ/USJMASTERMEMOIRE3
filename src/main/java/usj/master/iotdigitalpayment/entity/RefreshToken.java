package usj.master.iotdigitalpayment.entity;

import java.time.Instant;  

import javax.persistence.Column;
import javax.persistence.Entity;

import javax.persistence.Id;


@Entity(name = "refreshtoken")
public class RefreshToken {
	
	@Id
	@Column(name = "id")
	private String  id;


	@Column(name = "token", nullable = false, unique = true)
	private String token;

	@Column(name = "expirydate", nullable = false)
	private Instant expiryDate;


	public RefreshToken() {
	}
	
	

	public RefreshToken(String id, String token, Instant expiryDate) {
		this.id = id;
		this.token = token;
		this.expiryDate = expiryDate;
		
	}



	public Instant getExpiryDate() {
		return expiryDate;
	}

	public void setExpiryDate(Instant expiryDate) {
		this.expiryDate = expiryDate;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	@Override
	public String toString() {
		return "RefreshToken [id=" + id + ", token=" + token + ", expiryDate=" + expiryDate + "]";
	}	
}
