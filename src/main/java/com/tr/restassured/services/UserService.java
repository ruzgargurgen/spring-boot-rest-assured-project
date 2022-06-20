package com.tr.restassured.services;

import java.util.List;

import com.tr.restassured.entities.User;

public interface UserService {

	List<User> getAllUsers();

	User save(User user);

	User getByUserId(Long userId);

	User update(Long userId, User user);

	Boolean deleteUser(Long userId);

}
