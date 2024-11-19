package com.blogapplication.EchoWrite.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.blogapplication.EchoWrite.model.User;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
        public boolean existsByEmail(String email);
}
