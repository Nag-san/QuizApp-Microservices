package com.Spring.Auth_service.repository;

import com.Spring.Auth_service.model.UserCredential;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserCredentialRepo extends JpaRepository<UserCredential, Integer> {
    UserCredential findByName(String username);
}
