package com.unboxit.bnichecking.service;

import com.unboxit.bnichecking.entity.db.FavouriteDB;
import com.unboxit.bnichecking.entity.http.response.GetFavourite;
import com.unboxit.bnichecking.model.Favourite;
import com.unboxit.bnichecking.repository.FavouriteJpaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
public class FavouriteService {
    @Autowired
    private FavouriteJpaRepository repository;


    public List<GetFavourite> getAllFavourite(){
        List<GetFavourite> results = new ArrayList<>();
        List<FavouriteDB> favouriteDBs = repository.findAllFavourites();

        for (FavouriteDB favouriteDB: favouriteDBs) {
            results.add(new GetFavourite(favouriteDB));
        }
        return results;
    }

    public Favourite createFavourite(Favourite favourite){
        return repository.save(favourite);
    }

    public List<GetFavourite> getAllFavouriteByUserId(Long user_id){
        List<GetFavourite> results = new ArrayList<>();
        List<FavouriteDB> favouriteDBs = repository.findByUserId(user_id);

        for (FavouriteDB favouriteDB: favouriteDBs) {
            results.add(new GetFavourite(favouriteDB));
        }

        return results;
    }

    public boolean checkAccountNumberFavouritedByUserId(Long user_id, String accountNumber){
        List<FavouriteDB> favouriteDBs = repository.findByUserId(user_id);

        for (FavouriteDB favouriteDB: favouriteDBs) {
            if (Objects.equals(favouriteDB.getFavouriteAccountNumber(), accountNumber)) {
                return true;
            }
        }
        return false;
    }
}



