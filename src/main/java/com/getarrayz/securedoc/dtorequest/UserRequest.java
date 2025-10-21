package com.getarrayz.securedoc.dtorequest;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
public class UserRequest {

    @NotEmpty(message = "Field firstName - cannot be empty")
    private String firstName;

    @NotEmpty(message = "Field lastName - cannot be empty")
    private String lastName;

    @NotEmpty(message = "Field email - cannot be empty")
    @Email(message = "Invalid email data")
    private String email;

    @NotEmpty(message = "Field password - cannot be empty")
    private String password;

    private String bio;

    private String phone;

}
