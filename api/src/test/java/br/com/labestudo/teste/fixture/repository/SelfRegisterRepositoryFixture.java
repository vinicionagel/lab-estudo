package br.com.labestudo.teste.fixture.repository;

import br.com.labestudo.api.model.entity.SelfRegisterUser;
import br.com.labestudo.api.repository.SelfRegisterRepository;
import br.com.labestudo.teste.fixture.SelfRegisterFixture;
import org.springframework.stereotype.Component;

@Component
public class SelfRegisterRepositoryFixture {

    private SelfRegisterRepository selfRegisterRepository;

    public SelfRegisterRepositoryFixture(SelfRegisterRepository selfRegisterRepository) {
        this.selfRegisterRepository = selfRegisterRepository;
    }

    public SelfRegisterUser get() {
        return selfRegisterRepository.save(SelfRegisterFixture.validSelfRegisterUser());
    }

    public void deleteAll() {
        selfRegisterRepository.deleteAll();
    }

}
