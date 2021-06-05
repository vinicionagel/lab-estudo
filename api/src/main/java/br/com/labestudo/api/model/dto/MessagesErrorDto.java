package br.com.labestudo.api.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class MessagesErrorDto {

    private String key;
    private String[] args;


}
