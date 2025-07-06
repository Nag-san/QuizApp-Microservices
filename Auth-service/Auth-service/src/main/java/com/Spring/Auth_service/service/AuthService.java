package com.Spring.Auth_service.service;

import com.Spring.Auth_service.model.UserCredential;
import com.Spring.Auth_service.model.UserCredentialDto;
import com.Spring.Auth_service.repository.UserCredentialRepo;
import org.apache.catalina.connector.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    @Autowired
    private PasswordEncoder encoder;
    @Autowired
    private UserCredentialRepo repo;

    @Autowired
    private JwtService jwtService;

    private UserCredential mappedToEntity(UserCredentialDto dto){
        UserCredential user = new UserCredential();
        user.setPassword(dto.getPassword());
        user.setEmail(dto.getEmail());
        user.setName(dto.getName());
        return user;
    }
    public ResponseEntity<UserCredentialDto> saveUser(UserCredentialDto credentialDto){
        credentialDto.setPassword(encoder.encode(credentialDto.getPassword()));
        repo.save(mappedToEntity(credentialDto));
        return new ResponseEntity<UserCredentialDto>(credentialDto, HttpStatus.OK);
    }

    public String generateToken(String username){
        return jwtService.generateToken(username);
    }

    public void validateToken(String token){
        jwtService.validateToken(token);
    }


}
