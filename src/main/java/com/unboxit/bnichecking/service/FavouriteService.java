package com.unboxit.bnichecking.service;

import com.unboxit.bnichecking.model.Favourite;
import com.unboxit.bnichecking.repository.FavouriteJpaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FavouriteService {
    @Autowired
    private FavouriteJpaRepository repository;


    public List<Favourite> getAllFavourite(){
        return repository.findAll();
    }

    public Favourite createFavourite(Favourite favourite){
        return repository.save(favourite);
    }

    public List<Favourite> getAllFavouriteByAccountId(Long account_id){
        return repository.findByAccountId(account_id);
    }

}



