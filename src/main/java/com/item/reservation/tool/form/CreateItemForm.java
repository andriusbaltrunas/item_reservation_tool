package com.item.reservation.tool.form;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.File;

@Data
public class CreateItemForm {

    @NotBlank
    private String shortDescription;

    @NotBlank
    private String contactPerson;

    @NotBlank
    private String longDescription;

    @NotNull
    private MultipartFile[] images;

}