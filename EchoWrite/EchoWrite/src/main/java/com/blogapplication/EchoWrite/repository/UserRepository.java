package com.blogapplication.EchoWrite.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.blogapplication.EchoWrite.model.User;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
        public static User findByEmail(String emaill) {
                // TODO Auto-generated method stub
                throw new UnsupportedOperationException("Unimplemented method 'findByEmail'");
        }
}
        