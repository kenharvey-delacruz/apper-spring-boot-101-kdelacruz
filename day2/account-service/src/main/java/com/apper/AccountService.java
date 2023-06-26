package com.apper;

import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class AccountService {

    private List<Account> accounts = new ArrayList<>();
    private final IdGeneratorService idGeneratorService;

    public AccountService(IdGeneratorService idGeneratorService) {
        this.idGeneratorService = idGeneratorService;
    }

    public Account create(String firstName, String lastName, String username, String clearPassword) {
        Account account = new Account();

        String id = IdGeneratorService.getNextId();
        System.out.println("Generated id: " + id);

        account.setId(id);
        account.setBalance(1_000.0);

        LocalDateTime now = LocalDateTime.now();
        account.setCreationDate(now);
        account.setLastUpdated(now);

        account.setFirstName(firstName);
        account.setLastName(lastName);
        account.setUsername(username);
        account.setClearPassword(clearPassword);
        account.setVerificationCode(IdGeneratorService.generateVerificationCode());

        accounts.add(account);

        return account;
    }

    public void update(String accountId, String firstName, String lastName, String username, String clearPassword){
        Account account = get(accountId);

        account.setLastUpdated(LocalDateTime.now());

        account.setFirstName(firstName);
        account.setLastName(lastName);
        account.setUsername(username);
        account.setClearPassword(clearPassword);

        accounts.set(accounts.indexOf(account), account);
    }

    public void delete(String accountId){
        accounts.remove(get(accountId));
    }

    public Account get(String accountId) {
        for (Account account : accounts) {
            if (account.getId().equals(accountId)) {
                return account;
            }
        }

        return null;
    }

    public List<Account> getAll(){
        return accounts;
    }
//
//    public void update() {
//
//    }
//
//    public void delete() {
//
//    }
}