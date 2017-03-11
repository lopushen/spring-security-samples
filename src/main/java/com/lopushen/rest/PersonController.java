package com.lopushen.rest;

import com.lopushen.domain.Person;
import com.lopushen.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/person")
public class PersonController {
    @Autowired
    private PersonService service;

    @RequestMapping(method = RequestMethod.POST)
    public void createPerson(@RequestBody Person person) {
        service.createPerson(person);
    }

    @RequestMapping(method = RequestMethod.GET, path = "/{id}")
    public Person getPerson(@PathVariable Long id) {
        return service.getPerson(id);
    }

    @RequestMapping(method = RequestMethod.PUT, path = "/{id}")
    public void updatePerson(@RequestBody Person person, @PathVariable Long id) {
        service.updatePerson(person, id);
    }

    @RequestMapping(method = RequestMethod.GET, path = "/all")
    public List<Person> getAllPersons() {
        List<Person> result = new ArrayList<>();
        service.getAllPeople().forEach(result::add);
        return result;
    }

    @RequestMapping(method = RequestMethod.DELETE, path = "/{id}")
    public void deletePerson(@PathVariable Long id) {
        service.deletePerson(id);
    }
}
