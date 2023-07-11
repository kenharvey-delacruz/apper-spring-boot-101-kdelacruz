package com.apper.estore;

import com.apper.estore.payload.CreateUserRequest;
import com.apper.estore.payload.CreateUserResponse;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.Period;

@RestController
@RequestMapping("user")
public class UserApi {

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public CreateUserResponse createUser(@RequestBody @Valid CreateUserRequest request) throws InvalidUserAgeException {
        System.out.println(request);
        LocalDate birthDate = LocalDate.parse(request.getBirthDate());

        int age = Period.between(birthDate, LocalDate.now()).getYears();

        if (age < 15) {
            throw new InvalidUserAgeException("Invalid data. Age must be at least 15 years old.");
        } else {
            return new CreateUserResponse("RANDOM_CODE_FOR_NOW");
        }
    }

}