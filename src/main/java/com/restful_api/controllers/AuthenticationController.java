package com.restful_api.controllers;

import com.restful_api.dtos.AuthenticationRequest;
import com.restful_api.dtos.AuthenticationResponse;
import com.restful_api.dtos.RegisterRequest;
import com.restful_api.services.implementations.AuthenticationService;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthenticationController {
    private final AuthenticationService authenticationService;

    @PostMapping("/register")
    public ResponseEntity<AuthenticationResponse> register(@RequestBody RegisterRequest registerRequest){
        try{
            return  ResponseEntity.ok(authenticationService.register(registerRequest));
        }catch (DataIntegrityViolationException ex){
            throw  new DataIntegrityViolationException("Duplicate registration");
        }

    }

    @PostMapping("/login")
    public ResponseEntity<AuthenticationResponse> login(@RequestBody AuthenticationRequest request){
        return  ResponseEntity.ok(authenticationService.authenticate(request));
    }




}
