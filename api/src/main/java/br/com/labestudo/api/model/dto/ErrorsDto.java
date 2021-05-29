package br.com.labestudo.api.model.dto;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class ErrorsDto {

    private List<ErrorDto> errors = new ArrayList<>();

}
