package br.com.labestudo.api.controller;

import br.com.labestudo.api.exception.PassValidationException;
import br.com.labestudo.api.exception.UserRegisteredException;
import br.com.labestudo.api.model.dto.UserDto;
import br.com.labestudo.api.service.SelfRegisterService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController()
@RequestMapping("/selfregister")
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class SelfRegisterController {

    private final SelfRegisterService selfRegisterService;

    @PutMapping()
    public ResponseEntity<String> add(@RequestBody @Valid UserDto userDto) throws UserRegisteredException, PassValidationException {
        selfRegisterService.selfRegister(userDto);
        return ResponseEntity.ok(userDto.getName());
    }

}
