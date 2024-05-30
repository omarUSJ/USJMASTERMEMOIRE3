package usj.master.iotdigitalpayment.dao;

import java.util.Optional;  

import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.stereotype.Repository;

import usj.master.iotdigitalpayment.entity.RefreshToken;




@Repository
public interface RefreshTokenRepository extends JpaRepository<RefreshToken, Long> {
  Optional<RefreshToken> findByToken(String token);

 
}
