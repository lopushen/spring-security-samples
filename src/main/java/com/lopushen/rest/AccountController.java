package com.lopushen.rest;

import com.lopushen.domain.Account;
import com.lopushen.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/accounts")
public class AccountController {
    @Autowired
    private AccountService service;

    @RequestMapping(method = RequestMethod.POST)
    public void createAccount(@RequestBody Account account) {
        service.createAccount(account);
    }

    @RequestMapping(method = RequestMethod.GET, path = "/{id}")
    public Account getAccount(@PathVariable Long id) {
        return service.getAccount(id);
    }

    @RequestMapping(method = RequestMethod.PUT, path = "/{id}")
    public void updateAccount(@RequestBody Account account, @PathVariable Long id) {
        service.updateAccount(account, id);
    }

    @RequestMapping(method = RequestMethod.GET, path = "/all")
    public List<Account> getAllAccounts() {
        List<Account> result = new ArrayList<>();
        service.getAllAccounts().forEach(result::add);
        return result;
    }

    @RequestMapping(method = RequestMethod.DELETE, path = "/{id}")
    public void deleteAccount(@PathVariable Long id) {
        service.deleteAccount(id);
    }
}
