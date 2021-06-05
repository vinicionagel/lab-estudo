package br.com.labestudo.api.repository;

import br.com.labestudo.api.model.entity.SelfRegisterUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SelfRegisterRepository extends JpaRepository<SelfRegisterUser, String> {
}
