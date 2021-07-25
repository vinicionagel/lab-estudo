package br.com.labestudo.api.auth.model;

import br.com.labestudo.api.auth.model.entity.User;
import br.com.labestudo.api.auth.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.util.Objects;
import java.util.Optional;

@Component
public class LoggedUserValidator {

    @Autowired
    private UserRepository userRepository;

    public boolean isLoggedUserRequesting(Long id) {
        Optional<User> optUser = userRepository.findById(id);
        String loggedUserEmail = optUser.map(User::getEmail).orElse("");
        var userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return Objects.equals(userDetails.getUsername(), loggedUserEmail);
    }
}
