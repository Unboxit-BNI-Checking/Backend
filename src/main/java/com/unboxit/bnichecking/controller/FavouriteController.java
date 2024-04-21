package com.unboxit.bnichecking.controller;

import com.unboxit.bnichecking.model.Favourite;
import com.unboxit.bnichecking.repository.FavouriteJpaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class FavouriteController {
    @Autowired
    private FavouriteJpaRepository repository;

    @GetMapping("/favourites")
    public List<Favourite> getAllFavourite(){
        return repository.findAll();
    }

    @PostMapping("/favourites")
    public Favourite createFavourite(@RequestBody Favourite favourite){
        return repository.save(favourite);
    }

    @GetMapping("/favourites/{account_id}")
    public List<Favourite> getAllFavouriteByAccountId(@PathVariable Long account_id){
        return repository.findByAccountId(account_id);
    }

}
