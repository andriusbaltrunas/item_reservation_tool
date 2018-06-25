package com.item.reservation.tool.form;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Data
public class ReserveItemForm {

    @NotBlank
    private String uuid;

    @NotBlank
    private String reservationEnds;
}
