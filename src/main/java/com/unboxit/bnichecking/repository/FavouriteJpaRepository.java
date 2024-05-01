package com.unboxit.bnichecking.repository;

import com.unboxit.bnichecking.entity.db.FavouriteDB;
import com.unboxit.bnichecking.model.Favourite;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface FavouriteJpaRepository extends JpaRepository<Favourite, Long> {

    @Query(value="""
        SELECT
            f.favourite_id AS favouriteId,
            f.favourite_account_number AS favouriteAccountNumber,
            f.name AS name,
            f.user_id AS userId,
            f.created_at AS createdAt,
            f.updated_at AS updatedAt,
            f.deleted_at AS deletedAt
        FROM favourites f""", nativeQuery = true)
    List<FavouriteDB> findAllFavourites();

    @Query(value="""
        SELECT
            f.favourite_id AS favouriteId,
            f.favourite_account_number AS favouriteAccountNumber,
            f.name AS name,
            f.user_id AS userId,
            f.created_at AS createdAt,
            f.updated_at AS updatedAt,
            f.deleted_at AS deletedAt
        FROM favourites f
        WHERE user_id=:userId""", nativeQuery = true)
    List<FavouriteDB> findByUserId(Long userId);
}
