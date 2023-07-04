package com.apper.estore;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class CreateUserRequest {

    @NotBlank(message = "Email is required")
    @Email(message = "Invalid email format")
    private String email;

    @NotBlank(message = "Password is required")
    @Size(min=8, message = "Password must be atleast 8 characters")
    private String password;

    @JsonProperty("first_name")
    @NotBlank(message = "First name is required")
    private String firstName;

    @JsonProperty("middle_name")
    @NotBlank(message = "Middle name is required")
    private String middleName;

    @JsonProperty("last_name")
    @NotBlank(message = "Last name is required")
    private String lastName;

    @JsonProperty("birth_date")
    @NotBlank(message = "Birth date is required")
    @Pattern(regexp= "[0-9]{4}-[0-9]{2}-[0-9]{2}")
    private String birthDate;
}