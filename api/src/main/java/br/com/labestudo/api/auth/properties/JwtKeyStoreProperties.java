package br.com.labestudo.api.auth.properties;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Validated
@Component
@ConfigurationProperties("application.jwt.keystore")
public class JwtKeyStoreProperties {

	@NotNull
    private Resource path;
    
    @NotBlank
    private String keyStorePass;
    
    @NotBlank
    private String keyPairAlias;

}
