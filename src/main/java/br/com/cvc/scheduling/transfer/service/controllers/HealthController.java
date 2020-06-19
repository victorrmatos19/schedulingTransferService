package br.com.cvc.scheduling.transfer.service.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 * Health Check Controller
 *
 * @author Victor Rodrigues de Matos
 */

@RestController
@CrossOrigin("*")
public class HealthController {

    @GetMapping(value = "/health")
    public ResponseEntity<Map<String, String>> check() {
        HashMap<String, String> healthBody = new HashMap<>();
        healthBody.put("status", "UP");
        healthBody.put("spring-boot", "UP");

        return ResponseEntity.status(HttpStatus.OK).body(healthBody);
    }
}
