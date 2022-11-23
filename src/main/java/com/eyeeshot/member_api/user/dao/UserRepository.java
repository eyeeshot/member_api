package com.eyeeshot.member_api.user.dao;

import com.eyeeshot.member_api.user.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
  Optional<User> findByEmail(String email);
  Optional<User> findByPhoneNumber(String phoneNumber);
}
