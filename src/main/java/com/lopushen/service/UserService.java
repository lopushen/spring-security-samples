package com.lopushen.service;

import com.lopushen.dao.UserRepository;
import com.lopushen.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;

@Service
public class UserService {
    @Autowired
    private UserRepository repository;

    public void createUser(User User) {
        repository.save(User);
    }

    public User getUser(Long id) {
        return repository.findOne(id);
    }

    public Iterable<User> getAllUsers() {
        return repository.findAll();
    }

    public void updateUser(User user, Long id) {
        if (repository.exists(id)) {
            user.setId(id);
            repository.save(user);
        } else {
            throw new EntityNotFoundException(String.format("The user with id {} has not been found", id));
        }

    }

    public void deleteUser(Long id) {
        repository.delete(id);
    }
}
