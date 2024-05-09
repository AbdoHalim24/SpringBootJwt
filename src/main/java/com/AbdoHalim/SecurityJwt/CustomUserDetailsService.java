package com.AbdoHalim.SecurityJwt;

import com.AbdoHalim.SecurityJwt.CustomUserDetails;
import com.AbdoHalim.SecurityJwt.Entity.User;
import com.AbdoHalim.SecurityJwt.Repository.UserRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;
@Service
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {
    private final UserRepo userRepo;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> user =userRepo.findByEmail(username);
        if (!user.isPresent()){
            throw new UsernameNotFoundException("USER NOT FOUND");
        }
        return new CustomUserDetails(user.get());

    }
}
