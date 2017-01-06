package com.lopushen.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.data.annotation.Id;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import java.math.BigDecimal;

@Entity
@Getter
@AllArgsConstructor
public class Account {
    @GeneratedValue
    @Id
    private Long id;
    private String name;
    private BigDecimal amount;

}
