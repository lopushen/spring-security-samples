package com.lopushen.service;

import com.lopushen.dao.AccountRepository;
import com.lopushen.domain.Account;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;

@Service
public class AccountService {
    @Autowired
    private AccountRepository repository;

    public void createAccount(Account Account) {
        repository.save(Account);
    }

    public Account getAccount(Long id) {
        return repository.findOne(id);
    }

    public Iterable<Account> getAllAccounts() {
        return repository.findAll();
    }

    public void updateAccount(Account account, Long id) {
        if (repository.exists(id)) {
            account.setId(id);
            repository.save(account);
        } else {
            throw new EntityNotFoundException(String.format("The account with id {} has not been found", id));
        }

    }

    public void deleteAccount(Long id) {
        repository.delete(id);
    }
}
