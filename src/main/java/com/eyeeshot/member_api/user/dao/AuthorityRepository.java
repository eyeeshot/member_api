package com.eyeeshot.member_api.user.dao;

import com.eyeeshot.member_api.user.domain.Authority;
import com.eyeeshot.member_api.user.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AuthorityRepository extends JpaRepository<Authority, Long> {
  Optional<Authority> findById(Long id);

}
