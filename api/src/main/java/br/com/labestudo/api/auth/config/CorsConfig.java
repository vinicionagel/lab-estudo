package br.com.labestudo.api.auth.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Configuration
public class CorsConfig {

    @Value("application.cors.origins")
    private String allowedOrigins;

    @Value("application.cors.methods")
    private String allowedMethods;

    @Value("application.cors.headers")
    private String allowedHeaders;

    @Bean
    public FilterRegistrationBean<CorsFilter> corsFilterRegistrationBean() {
        CorsConfiguration config = new CorsConfiguration();
        config.setAllowCredentials(true);
        config.setAllowedOrigins(getAllowedCors(allowedOrigins));
        config.setAllowedMethods(getAllowedCors(allowedMethods));
        config.setAllowedHeaders(getAllowedCors(allowedHeaders));

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", config);

        FilterRegistrationBean<CorsFilter> bean = new FilterRegistrationBean<>();
        bean.setFilter(new CorsFilter(source));
        bean.setOrder(Ordered.HIGHEST_PRECEDENCE);

        return bean;
    }

    private List<String> getAllowedCors(String allowedCors) {
        return Arrays.stream(allowedCors.split(",")).collect(Collectors.toList());
    }
}
