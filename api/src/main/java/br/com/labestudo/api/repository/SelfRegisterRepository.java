package br.com.labestudo.api.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.labestudo.api.model.entity.SelfRegisterUser;

@Repository
public interface SelfRegisterRepository extends JpaRepository<SelfRegisterUser, String> {

	Optional<SelfRegisterUser> findByEmail(String email);
}
