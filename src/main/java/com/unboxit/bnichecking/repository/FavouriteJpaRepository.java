package com.unboxit.bnichecking.repository;

import com.unboxit.bnichecking.entity.db.FavouriteDB;
import com.unboxit.bnichecking.model.Favourite;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface FavouriteJpaRepository extends JpaRepository<Favourite, Long> {

    @Query(value="""
        SELECT
            f.favourite_id,
            f.favourite_account_number,
            f.name,
            f.user_id,
            f.created_at,
            f.updated_at,
            f.deleted_at
        FROM favourites f""", nativeQuery = true)
    List<FavouriteDB> findAllFavourites();

    @Query(value="""
        SELECT
            f.favourite_id,
            f.favourite_account_number,
            f.name,
            f.user_id,
            f.created_at,
            f.updated_at,
            f.deleted_at
        FROM favourites f
        WHERE user_id=:userId""", nativeQuery = true)
    List<FavouriteDB> findByUserId(Long userId);
}
