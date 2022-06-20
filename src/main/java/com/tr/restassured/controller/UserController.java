package com.tr.restassured.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.tr.restassured.entities.User;
import com.tr.restassured.services.UserService;

@RestController
public class UserController {

	@Autowired
	private UserService userService;

	@GetMapping("/getAll")
	public ResponseEntity<List<User>> getAllUsers() {
		return ResponseEntity.ok(userService.getAllUsers());
	}

	@PostMapping("/users")
	public ResponseEntity<User> createUser(@RequestBody User user) {
		return ResponseEntity.ok(userService.save(user));
	}

	@GetMapping("getById/{userId}")
	public ResponseEntity<User> getByUserId(@PathVariable Long userId) {
		return ResponseEntity.ok(userService.getByUserId(userId));
	}

	@PutMapping("/edit/{userId}")
	public ResponseEntity<User> updateUser(@PathVariable Long userId, @RequestBody User user) {
		return ResponseEntity.ok(userService.update(userId, user));
	}

	@DeleteMapping("/delete/{userId}")
	public ResponseEntity<Boolean> deleteUser(@PathVariable Long userId) {
		return ResponseEntity.ok(userService.deleteUser(userId));
	}

}
