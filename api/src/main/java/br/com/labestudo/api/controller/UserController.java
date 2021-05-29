package br.com.labestudo.api.controller;

import br.com.labestudo.api.model.dto.UserDto;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController()
@RequestMapping("/user")
public class UserController {

	@GetMapping
	@PreAuthorize("isAuthenticated()")
	public ResponseEntity<String> find() {
		return ResponseEntity.ok("Hello Vini v4");
	}

	@PutMapping()
	public ResponseEntity<String> add(@RequestBody @Valid UserDto userDto) {
		return ResponseEntity.ok(userDto.getName());
	}

}
