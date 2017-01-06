package com.lopushen.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.data.annotation.Id;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.OneToMany;
import java.util.List;

@Entity
@Getter
@AllArgsConstructor
public class User {
    @GeneratedValue
    @Id
    private Long id;
    private String name;
    private int age;
    @OneToMany
    private List<Account> accounts;
}
