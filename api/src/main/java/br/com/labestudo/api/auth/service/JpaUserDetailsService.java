package br.com.labestudo.api.auth.service;

import java.util.Arrays;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import br.com.labestudo.api.auth.model.AuthUser;
import br.com.labestudo.api.auth.repository.UserRepository;

@Service
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class JpaUserDetailsService implements UserDetailsService {

	private final UserRepository userRepository;
	
	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		var user = userRepository.findByEmailAndActive(email, Boolean.TRUE)
				.orElseThrow(() -> new RuntimeException("Usuário ou senha inválida"));
		
		return new AuthUser(user, Arrays.asList(new SimpleGrantedAuthority("ADMIN".toUpperCase())));
	}
}
