package br.com.labestudo.api.auth.service;

import br.com.labestudo.api.auth.model.entity.User;
import br.com.labestudo.api.auth.repository.UserRepository;
import br.com.labestudo.api.model.dto.UserDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public void update(UserDto userDto, Long id) {
        Optional<User> optUser = userRepository.findById(id);
        optUser.ifPresent(user -> {
            user.setName(userDto.getName());
            userRepository.save(user);
        });
    }
}
