package com.poly.service;

import java.util.List;
import com.poly.entity.Account;

public interface AccountService {
    List<Account> findAll();
    Account findById(String username);
    Account save(Account account);
    void delete(String username);
}
