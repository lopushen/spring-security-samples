package com.lopushen.service;

import com.lopushen.dao.PersonRepository;
import com.lopushen.domain.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;

@Service
public class PersonService {
    @Autowired
    private PersonRepository repository;

    public void createPerson(Person Person) {
        repository.save(Person);
    }

    public Person getPerson(Long id) {
        return repository.findOne(id);
    }

    public Iterable<Person> getAllPeople() {
        return repository.findAll();
    }

    public void updatePerson(Person person, Long id) {
        if (repository.exists(id)) {
            person.setId(id);
            repository.save(person);
        } else {
            throw new EntityNotFoundException(String.format("The person with id {} has not been found", id));
        }

    }

    public void deletePerson(Long id) {
        repository.delete(id);
    }
}
