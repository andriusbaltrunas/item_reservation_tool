package com.item.reservation.tool.form;

import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Data
public class LoginForm {

    @Email
    @NotBlank
    private String email;

    @NotBlank
    @Size(min = 6)
    private String password;
}
