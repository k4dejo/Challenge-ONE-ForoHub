package com.foroHub.foroHub.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.foroHub.foroHub.model.User;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    static Optional<Object> findByName(Long authorId) {
        return Optional.empty();
    }

    static Optional<User> findByUsername(String userName) {
        return Optional.empty();
    }

}
