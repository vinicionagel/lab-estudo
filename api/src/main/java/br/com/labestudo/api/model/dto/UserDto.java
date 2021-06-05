package br.com.labestudo.api.model.dto;

import java.io.Serializable;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;

import lombok.Data;

@Data
public class UserDto implements Serializable {

	private static final long serialVersionUID = 1L;

	@NotNull
	private String name;

    @Email
    private String email;

    private String pass;

}
