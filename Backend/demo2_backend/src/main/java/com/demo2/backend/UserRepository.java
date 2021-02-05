package com.demo2.backend;

import org.springframework.data.jpa.repository.JpaRepository;

import com.demo2.backend.User;

// This will be AUTO IMPLEMENTED by Spring into a Bean called userRepository
// CRUD refers Create, Read, Update, Delete

public interface UserRepository extends JpaRepository<User, Integer> {

}