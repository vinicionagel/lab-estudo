package br.com.labestudo.api.model.dto;

import lombok.Builder;
import lombok.Data;

import java.util.Map;
import java.util.Set;

@Data
@Builder
public class EmailDto {

    private String subject;
    private Set<String> recipients;
    private String template;
    private Map<String, Object> values;

}
