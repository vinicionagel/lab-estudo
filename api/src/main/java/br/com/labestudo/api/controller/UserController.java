package br.com.labestudo.api.controller;

import br.com.labestudo.api.auth.service.UserService;
import br.com.labestudo.api.model.dto.UserDto;
import br.com.labestudo.api.model.validation.ValidationGroup;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Tag(name = "Usuário", description = "Usuário Descrição")
@RestController()
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping
    @PreAuthorize("isAuthenticated()")
    @Operation(summary = "Buscar")
    public ResponseEntity<String> find() {
        return ResponseEntity.ok("Hello Vini v4");
    }

    @PutMapping
    public ResponseEntity<String> add(@RequestBody @Valid UserDto userDto) {
        return ResponseEntity.ok(userDto.getName());
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PreAuthorize("isAuthenticated() and @loggedUserValidator.isLoggedUserRequesting(#id)")
    public void update(@RequestBody @Validated(value = ValidationGroup.Update.class) UserDto userDto, @PathVariable Long id) {
        userService.update(userDto, id);
    }
}
