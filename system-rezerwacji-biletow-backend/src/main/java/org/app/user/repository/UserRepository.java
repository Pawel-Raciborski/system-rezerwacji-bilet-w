package org.app.user.repository;

import org.app.user.model.User;

import java.util.Optional;

public interface UserRepository {
    Optional<User> findByEmailAndPassword(String email, String password);
    void save(User user);
}
