package com.unboxit.bnichecking.service;

import com.unboxit.bnichecking.entity.db.FavouriteDB;
import com.unboxit.bnichecking.entity.http.response.GetFavourite;
import com.unboxit.bnichecking.model.Favourite;
import com.unboxit.bnichecking.repository.FavouriteJpaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

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

    public List<GetFavourite> getAllFavouriteByAccountId(Long account_id){
        List<GetFavourite> results = new ArrayList<>();
        List<FavouriteDB> favouriteDBs = repository.findByAccountId(account_id);

        for (FavouriteDB favouriteDB: favouriteDBs) {
            results.add(new GetFavourite(favouriteDB));
        }

        return results;
    }

}



