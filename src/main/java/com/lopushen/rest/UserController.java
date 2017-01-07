package com.lopushen.rest;

import com.lopushen.domain.User;
import com.lopushen.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("user")
public class UserController {
    @Autowired
    private UserService service;

    @RequestMapping(method = RequestMethod.POST)
    public void createUser(@RequestBody User user) {
        service.createUser(user);
    }

    @RequestMapping(method = RequestMethod.GET, path = "/{id}")
    public User getUser(@PathVariable Long id) {
        return service.getUser(id);
    }

    @RequestMapping(method = RequestMethod.PUT, path = "/{id}")
    public void updateUser(@RequestBody User user, @PathVariable Long id) {
        service.updateUser(user, id);
    }

    @RequestMapping(method = RequestMethod.GET, path = "/all")
    public List<User> getAllUsers() {
        List<User> result = new ArrayList<>();
        service.getAllUsers().forEach(result::add);
        return result;
    }

    @RequestMapping(method = RequestMethod.DELETE, path = "/{id}")
    public void deleteUser(@PathVariable Long id) {
        service.deleteUser(id);
    }
}
