package br.com.labestudo.teste.util;

import br.com.labestudo.api.ApiApplication;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

@SpringBootTest(classes = ApiApplication.class)
@ActiveProfiles("test")
public class ApiApplicationIT {

}
