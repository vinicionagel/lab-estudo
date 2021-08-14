package br.com.labestudo.teste.util;


import br.com.labestudo.api.ApiApplication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.MessageSource;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.testcontainers.containers.PostgreSQLContainer;

@SpringBootTest(classes = ApiApplication.class)
public class ApiApplicationIT {

    @Autowired
    protected MessageSource messageSource;

    public static PostgreSQLContainer<?> postgreSQLContainer = new PostgreSQLContainer<>("postgres:13.1")
            .withUsername("test")
            .withPassword("test")
            .withDatabaseName("test");

    static {
        postgreSQLContainer.start();
        Runtime.getRuntime().addShutdownHook(new Thread(() -> postgreSQLContainer.stop()));
    }

    @DynamicPropertySource
    static void postgresqlProperties(DynamicPropertyRegistry registry) {
        registry.add("spring.datasource.url", postgreSQLContainer::getJdbcUrl);
        registry.add("spring.datasource.username", postgreSQLContainer::getUsername);
        registry.add("spring.datasource.password", postgreSQLContainer::getPassword);
    }
}
