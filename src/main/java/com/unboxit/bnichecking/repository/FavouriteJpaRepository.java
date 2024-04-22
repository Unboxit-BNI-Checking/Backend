package com.unboxit.bnichecking.repository;

import com.unboxit.bnichecking.model.Favourite;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface FavouriteJpaRepository extends JpaRepository<Favourite, Long> {
//    Favourite findFavouriteByAccountNumber(String accountNumber);

    @Query(value="SELECT * FROM favourites WHERE account_id=?1", nativeQuery = true)
    List<Favourite> findByAccountId(Long accountId);
}
