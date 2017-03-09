package com.lopushen.rest;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@RestController
public class UiController {

    /**
     * If the user is reachable, then return a user back, if no - 401
     * @param user
     * @return
     */
    @RequestMapping("/user")
    public Principal getUser(Principal user) {
        return user;
    }

}
