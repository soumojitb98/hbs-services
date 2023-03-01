package com.hbs.controller;

import com.google.gson.Gson;
import com.hbs.io.AuthenticationRequest;
import com.hbs.io.AuthenticationResponse;
import com.hbs.io.RegisterRequest;
import com.hbs.service.authentication.AuthenticationService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created By Soumojit_Basak on 28-02-2023
 */
@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
@Slf4j
public class AuthenticationController {

    private final AuthenticationService service;

    private final Gson gson;

    @PostMapping(value = "/register",
            consumes = "application/json", produces = "application/json")
    public ResponseEntity<String> register(@RequestBody RegisterRequest request) {
        log.info(" Calling Authentication Service for User Registration -- START ");
        AuthenticationResponse resp = service.register(request);
        log.info(" Calling Authentication Service for User Registration -- STOP ");
        return new ResponseEntity<>(gson.toJson(resp), HttpStatus.OK);

    }

    @PostMapping(value = "/authenticate",
            consumes = "application/json", produces = "application/json")
    public ResponseEntity<AuthenticationResponse> register(@RequestBody AuthenticationRequest request) {
        return ResponseEntity.ok(service.authenticate(request));

    }

}
