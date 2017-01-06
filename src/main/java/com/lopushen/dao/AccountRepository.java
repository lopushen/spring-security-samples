package com.lopushen.dao;

import com.lopushen.domain.Account;
import org.springframework.data.repository.CrudRepository;

public interface AccountRepository extends CrudRepository <Account, Long> {
}
