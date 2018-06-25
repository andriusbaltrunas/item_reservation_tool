package com.item.reservation.tool.form;

import com.item.reservation.tool.utils.annotation.ComparePasswords;
import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Data
@ComparePasswords(password = "password", repeatPassword = "repeatPassword")
public class CreateUserForm {

    @Email
    @NotBlank
    private String email;

    @NotBlank
    private String firstName;
    @NotBlank
    private String lastName;

    @NotBlank
    @Size(min = 6)
    private String password;

    @NotBlank
    @Size(min = 6)
    private String repeatPassword;

}


