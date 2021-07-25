package br.com.labestudo.teste.fixture.repository;

import br.com.labestudo.api.auth.model.entity.User;
import br.com.labestudo.api.auth.repository.UserRepository;
import br.com.labestudo.teste.fixture.UserFixture;
import org.springframework.stereotype.Component;

@Component
public class UserRepositoryFixture {

    private UserRepository userRepository;

    public UserRepositoryFixture(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User get() {
        return userRepository.save(UserFixture.get());
    }

    public void deleteAll() {
        userRepository.deleteAll();
    }
}
