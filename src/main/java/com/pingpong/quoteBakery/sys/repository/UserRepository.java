package com.pingpong.quoteBakery.sys.repository;

import com.pingpong.quoteBakery.sys.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByEmail(String email);
}
