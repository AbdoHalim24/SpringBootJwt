package com.AbdoHalim.SecurityJwt.auth;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class AuthenticationResponse {
    private  String token;
}
