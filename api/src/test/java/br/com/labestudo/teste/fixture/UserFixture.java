package br.com.labestudo.teste.fixture;

import br.com.labestudo.api.auth.model.entity.User;

public class UserFixture {

    public static User getValidUser() {
        User user = new User();
        user.setName("validUser");
        user.setEmail("validUser@gmail.com");
        user.setActive(true);
        user.setPass("$2a$10$uGS2DXh1gIvZEsmjlwQRZOZnCeoizUeinEPuLQKtpsTkIhDepesw2");
        return user;
    }
}