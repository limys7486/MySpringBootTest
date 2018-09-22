package my.base.spring.repository;

import my.base.spring.model.RememberMeToken;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RememberMeTokenRepository extends JpaRepository<RememberMeToken, String>{
    RememberMeToken findBySeries(String series);

    RememberMeToken findByUserid(String userid);

    List<RememberMeToken> findByUsername(String username);
}