package br.com.labestudo.teste.it;

import br.com.labestudo.teste.fixture.SelfRegisterRepositoryFixture;
import br.com.labestudo.teste.util.ApiApplicationIT;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

class SelfRegisterIT extends ApiApplicationIT {

    @Autowired
    private SelfRegisterRepositoryFixture selfRegisterRepository;

    @BeforeEach
    public void setup() {

    }

    @BeforeEach
    public void initTest() {
        selfRegisterRepository.deleteAll();
    }

    @Test
    void test() {
        selfRegisterRepository.get();
    }
}
