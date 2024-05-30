package usj.master.iotdigitalpayment.service;

import java.time.Instant;      
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;


import usj.master.iotdigitalpayment.dao.RefreshTokenRepository;

import usj.master.iotdigitalpayment.entity.RefreshToken;
import usj.master.iotdigitalpayment.security.jwt.TokenRefreshException;

@Service
public class RefreshTokenService {
	
  @Value("${bezkoder.app.jwtRefreshExpirationMs}")
  private Long refreshTokenDurationMs;

  @Autowired
  private RefreshTokenRepository refreshTokenRepository;



	public Optional<RefreshToken> findByToken(String token) {
	    return refreshTokenRepository.findByToken(token);
	  }

  public RefreshToken createRefreshToken(String customerId) {

    RefreshToken refreshToken = new RefreshToken(
    		(customerId + System.currentTimeMillis()).toString(),
    		UUID.randomUUID().toString(), 
    		Instant.now().plusMillis(refreshTokenDurationMs));


    
    refreshToken = refreshTokenRepository.save(refreshToken);

    return refreshToken;
  }

  public RefreshToken verifyExpiration(RefreshToken token) {
    if (token.getExpiryDate().compareTo(Instant.now()) < 0) {
      refreshTokenRepository.delete(token);
      throw new TokenRefreshException(token.getToken(), "Refresh token was expired. Please make a new signin request");
    }

    return token;
  }


}
