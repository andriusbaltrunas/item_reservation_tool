package com.item.reservation.tool.form;

import com.item.reservation.tool.utils.annotation.ComparePasswords;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Data
@ComparePasswords(password = "password", repeatPassword = "passwordRepeat")
public class ChangePassword {

    @NotBlank
    @Size(min = 6)
    private String password;

    @NotBlank
    @Size(min = 6)
    private String passwordRepeat;
}
