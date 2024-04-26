package com.unboxit.bnichecking.controller;

import com.unboxit.bnichecking.entity.http.request.CreateFavourite;
import com.unboxit.bnichecking.entity.http.response.ApiResponse;
import com.unboxit.bnichecking.entity.http.response.GetFavourite;
import com.unboxit.bnichecking.model.Account;
import com.unboxit.bnichecking.model.Favourite;
import com.unboxit.bnichecking.service.AccountService;
import com.unboxit.bnichecking.service.FavouriteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping("/api")
public class FavouriteController {

    private final FavouriteService favouriteService;
    private final AccountService accountService;

    @Autowired
    public FavouriteController(FavouriteService favouriteService, AccountService accountService) {
        this.accountService = accountService;
        this.favouriteService = favouriteService;
    }

    @GetMapping("/favourites")
    public ResponseEntity<ApiResponse<List<GetFavourite>>> getAllFavourite() {
        return ResponseEntity.ok(new ApiResponse<>(true, favouriteService.getAllFavourite(), null));
    }

    @PostMapping("/favourites")
    public ResponseEntity<ApiResponse<Favourite>> createFavourite(@RequestBody CreateFavourite newFavourite) {

        Account account = accountService.getAccountByAccountId(newFavourite.getAccountId());
        if (account == null) {
            ApiResponse<Favourite> response = new ApiResponse<>(false, null, "Account id is invalid");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
        }

        Account favouriteAccount = accountService.getAccountByAccountNumber(newFavourite.getFavouriteAccountNumber());
        if (favouriteAccount == null) {
            ApiResponse<Favourite> response = new ApiResponse<>(false, null, "Favourite account number is invalid");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
        } else if (Objects.equals(favouriteAccount.getAccountNumber(), account.getAccountNumber())) {
            ApiResponse<Favourite> response = new ApiResponse<>(false, null, "Can't favourite your own account");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
        }

        List<GetFavourite> existingFavorites = favouriteService.getAllFavouriteByAccountId(newFavourite.getAccountId());
        for (GetFavourite existingFavorite : existingFavorites) {
            if (existingFavorite.getFavouriteAccountNumber().equals(newFavourite.getFavouriteAccountNumber())) {
                ApiResponse<Favourite> response = new ApiResponse<>(false, null, "This account is already registered as a favourite");
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
            }
        }

        Favourite savedFavorite = favouriteService.createFavourite(new Favourite(favouriteAccount, newFavourite.getName(), account));
        return ResponseEntity.ok(new ApiResponse<>(true, savedFavorite, null));
    }

    @GetMapping("/favourites/{account_id}")
    public List<GetFavourite> getAllFavouriteByAccountId(@PathVariable Long account_id) {
        return favouriteService.getAllFavouriteByAccountId(account_id);
    }

}
