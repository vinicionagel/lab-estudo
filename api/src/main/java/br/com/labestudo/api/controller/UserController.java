package br.com.labestudo.api.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController()
@RequestMapping("/user")
public class UserController {

	@GetMapping
	@PreAuthorize("isAuthenticated()")
	public ResponseEntity<String> find() {
		return ResponseEntity.ok("Hello Vini v4");
	}
}
