package com.hayden.record.repository;

import com.hayden.record.domain.User;

import java.util.List;
import java.util.Optional;

public interface UserRepository {
    User save(User user);
    Optional<User> findById(Long id);
    Optional<User> findByName(String name);
    List<User> findAll();

    boolean existsByEmail(String email);
}
