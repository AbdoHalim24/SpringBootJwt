package com.AbdoHalim.SecurityJwt.auth;

import com.AbdoHalim.SecurityJwt.Config.JwtService;
import com.AbdoHalim.SecurityJwt.CustomUserDetails;
import com.AbdoHalim.SecurityJwt.Entity.Role;
import com.AbdoHalim.SecurityJwt.Entity.User;
import com.AbdoHalim.SecurityJwt.Repository.UserRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthenticationService {
    private final UserRepo userRepo;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    public AuthenticationResponse register(RegisterModel registerModel) {
        User user = User.builder()
                .firstname(registerModel.getFirstname())
                .lastname(registerModel.getLastname())
                .email(registerModel.getEmail())
                .password(passwordEncoder.encode(registerModel.getPassword()))
                .role(Role.USER)
                .build();

        userRepo.save(user);

        var jwt = jwtService.generateToken(new CustomUserDetails(user));
        return AuthenticationResponse.builder()
                .token(jwt)
                .build();
    }

    public AuthenticationResponse authenticate(RegisterModel request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getEmail(),
                        request.getPassword()
                )
        );
        
        var user = userRepo.findByEmail(request.getEmail());
        var jwt = jwtService.generateToken(new CustomUserDetails(user.get()));
        return AuthenticationResponse.builder()
                .token(jwt)
                .build();
    }
}
