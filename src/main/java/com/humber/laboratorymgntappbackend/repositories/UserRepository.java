package com.humber.laboratorymgntappbackend.repositories;

import com.humber.laboratorymgntappbackend.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Integer> {
    public Optional<User> findByUsername(String username);
}
