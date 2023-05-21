package com.hbs.service.authentication;

import com.hbs.io.auth.AuthenticationRequest;
import com.hbs.io.auth.AuthenticationResponse;
import com.hbs.io.register.RegisterRequest;
import com.hbs.model.Customer;
import com.hbs.repository.CustomerRepo;
import com.hbs.security.JwtService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

/**
 * Created By Soumojit_Basak on 28-02-2023
 */
@Service
@RequiredArgsConstructor
@Slf4j
public class AuthenticationService {

    private final CustomerRepo repo;

    private final PasswordEncoder passwordEncoder;

    private final JwtService jwtService;

    private final AuthenticationManager authenticationManager;

    public AuthenticationResponse register(RegisterRequest request) {

        UserDetails userDetails = User.builder().username(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .roles(request.getRole())
                .build();

        log.info("Password is :" + request.getPassword());
        log.info("Encoded Password : " + passwordEncoder.encode(request.getPassword()));

        Customer customer = new Customer();
        customer.setFirstName(request.getFirstName());
        customer.setLastName(request.getLastName());
        customer.setUserName(request.getEmail());
        customer.setPassword(passwordEncoder.encode(request.getPassword()));
        customer.setRole(request.getRole());

        // Save Details of Customer Obj to Database
        repo.addCustomerDetails(customer);

        return new AuthenticationResponse("New User Added Successfully !!");
    }

    public AuthenticationResponse authenticate(AuthenticationRequest request) {

        UsernamePasswordAuthenticationToken token =
                new UsernamePasswordAuthenticationToken(request.getEmail(),
                        request.getPassword());

        authenticationManager.authenticate(token);

        // Fetch Details From Database for User
        Customer customer = repo.findByEmail(request.getEmail());

        log.info(" Customer Details is : " + customer);

        String jwtToken = jwtService.generateToken(customer);

        log.info(" The JWT Token Is :" + jwtToken);

        return AuthenticationResponse.builder()
                .jwtToken(jwtToken)
                .remark(" Authenticated Successfully !!")
                .build();

    }
}
