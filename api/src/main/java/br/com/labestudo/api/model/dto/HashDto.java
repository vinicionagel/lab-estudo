package br.com.labestudo.api.model.dto;

import lombok.Data;

import javax.validation.constraints.NotEmpty;

@Data
public class HashDto {

    @NotEmpty
    private String hash;

}
