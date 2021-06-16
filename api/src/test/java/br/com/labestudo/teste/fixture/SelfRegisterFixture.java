package br.com.labestudo.teste.fixture;

import br.com.labestudo.api.model.dto.UserDto;

public class SelfRegisterFixture {

    public static UserDto validUserDto() {
        return UserDto.builder().email("vinicionagel@gmail.com")
                .name("vinicio nagel")
                .pass("Abc123")
                .build();
    }
    public static UserDto invalidEmailUserDto() {
        return UserDto.builder().email("vvsdfdsfds")
                .name("vinicio nagel")
                .build();
    }

    public static UserDto emptyUserDto() {
        return UserDto.builder().build();
    }

}
