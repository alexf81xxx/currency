package repo;

import controllers.TokenController;
import models.Token;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TokenRepo extends JpaRepository<Token, Long> {

    Token findByTokenNameAndToken (String tokenName, String token);

}
