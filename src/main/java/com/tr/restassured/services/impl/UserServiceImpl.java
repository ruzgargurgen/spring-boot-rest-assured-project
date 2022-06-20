package com.tr.restassured.services.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tr.restassured.entities.User;
import com.tr.restassured.repos.UserRepository;
import com.tr.restassured.services.UserService;

@Service
public class UserServiceImpl implements UserService{
	@Autowired
	private UserRepository userRepository;

	@Override
	public List<User> getAllUsers() {
		return userRepository.findAll();
	}

	@Override
	public User save(User user) {
		return userRepository.save(user);
	}

	@Override
	public User getByUserId(Long userId) {
		return userRepository.findById(userId).orElse(null);
	}

	@Override
	public User update(Long userId, User user) {
		Optional<User> userEntity=userRepository.findById(userId);
		if (userEntity.isPresent()) {
		    User newUser=userEntity.get();
			newUser.setUserName(user.getUserName());
			newUser.setPassword(user.getPassword());
			userRepository.save(newUser);
			return newUser;
		}
		else
		return null;
	}

	@Override
	public Boolean deleteUser(Long userId) {
		userRepository.deleteById(userId);
		return true;
	}

}
