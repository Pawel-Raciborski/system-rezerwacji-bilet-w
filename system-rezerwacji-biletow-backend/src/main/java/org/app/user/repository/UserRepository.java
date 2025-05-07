package org.app.user.repository;

import org.app.user.model.User;

import java.util.Optional;
import java.util.UUID;

public interface UserRepository {
    Optional<User> findByEmailAndPassword(String email, String password);
    User save(User user);

    Optional<User> findById(UUID userId);
}
