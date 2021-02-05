package com.example.demo;

import org.springframework.data.repository.CrudRepository;

import com.example.demo.User;

// This will be AUTO IMPLEMENTED by Spring into a Bean called EmailRepository
// CRUD refers Create, Read, Update, Delete

public interface EmailRepository extends CrudRepository<User, Integer> {

}