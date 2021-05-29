package br.com.labestudo.api.model.dto;

import lombok.Data;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Data
public class UserDto implements Serializable {

    @NotNull
    private String name;

}
