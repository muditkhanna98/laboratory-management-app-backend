package com.humber.laboratorymgntappbackend.repositories;

import com.humber.laboratorymgntappbackend.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Integer> {
}
