package com.apper;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("account")
public class AccountController {

    private final AccountService accountService;

    public AccountController(AccountService accountService) {
        this.accountService = accountService;
    }

//    PostMapping for creating account
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public CreateAccountResponse createAccount(@RequestBody CreateAccountRequest request) {

        Account account = accountService.create(request.getFirstName(), request.getLastName(), request.getEmail(), request.getPassword());

        CreateAccountResponse response = new CreateAccountResponse();
        response.setVerificationCode(account.getVerificationCode());

        return response;
    }

//    PutMapping for updating account
    @PutMapping("{accountId}")
    @ResponseStatus(HttpStatus.OK)
    public UpdateAccountResponse updateAccount(@PathVariable String accountId, @RequestBody UpdateAccountRequest request) {
        accountService.update(
                accountId,
                request.getFirstName(),
                request.getLastName(),
                request.getEmail(),
                request.getPassword());

        UpdateAccountResponse response = new UpdateAccountResponse();
        response.setLastUpdate(LocalDateTime.now());

        return response;
    }

//    GetMapping for retrieving a specific account using accountId
    @GetMapping("{accountId}")
    public GetAccountResponse getAccount(@PathVariable String accountId) {
        Account account = accountService.get(accountId);

        GetAccountResponse response = new GetAccountResponse();
        response.setBalance(account.getBalance());
        response.setFirstName(account.getFirstName());
        response.setLastName(account.getLastName());
        response.setUsername(account.getUsername());
        response.setRegistrationDate(account.getCreationDate());
        response.setAccountId(account.getId());

        return response;
    }

//    GetMapping for retrieving all accounts using for loop
    @GetMapping
    public List<GetAccountResponse> getAllAccounts() {
        List<GetAccountResponse> responseList = new ArrayList<>();

        for (Account account : accountService.getAll()) {
            GetAccountResponse response = new GetAccountResponse();
            response.setBalance(account.getBalance());
            response.setFirstName(account.getFirstName());
            response.setLastName(account.getLastName());
            response.setUsername(account.getUsername());
            response.setRegistrationDate(account.getCreationDate());
            response.setAccountId(account.getId());
            responseList.add(response);
        }

        return responseList;
    }

//    DeleteMapping for deleting specific account using accountId
    @DeleteMapping("{accountId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteAccount(@PathVariable String accountId) {
        accountService.delete(accountId);
    }


}