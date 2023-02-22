package com.rochelle.login_reg.repositories;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import com.rochelle.login_reg.models.User;

public interface UserRepository extends CrudRepository <User, Long>{
    // Need to modify findByEmail
    Optional<User> findByEmail(String email);
}
