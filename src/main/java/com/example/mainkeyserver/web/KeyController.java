package com.example.mainkeyserver.web;

import com.example.mainkeyserver.service.KeyService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class KeyController {
    private final KeyService keyService;

    @GetMapping("/api/generate/key")
    public ResponseEntity<String> generateKey(int length){
        String newKey = keyService.generateKey(length);
        return new ResponseEntity<>(newKey, HttpStatus.OK);
    }
}
