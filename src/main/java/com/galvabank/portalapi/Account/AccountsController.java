package com.galvabank.portalapi.Account;

import com.galvabank.portalapi.User.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/v1")
public class AccountsController {

    @Autowired
    private final AccountsRepository accountsRepository;
    private final UsersRepository usersRepository;


    public AccountsController(AccountsRepository accountsRepository, UsersRepository usersRepository) {
        this.accountsRepository = accountsRepository;
        this.usersRepository = usersRepository;
    }

    @PostMapping("/users/{user_id}/new-account")
    public Optional<Account> addOneAccount(@PathVariable long user_id, @RequestBody Account acct) {
        return usersRepository.findById(user_id).map(user -> {
            acct.setUser(user);
            return accountsRepository.save(acct);
        });
    }
}
