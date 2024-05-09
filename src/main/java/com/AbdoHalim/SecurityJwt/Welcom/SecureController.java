package com.AbdoHalim.SecurityJwt.Welcom;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth/v2")
public class SecureController {

    @GetMapping
    public ResponseEntity<String>saywlecom(){
        return ResponseEntity.ok("im secure");
    }
}
