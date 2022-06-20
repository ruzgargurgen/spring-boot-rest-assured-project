package com.tr.restassured.repos;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.tr.restassured.entities.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

}
