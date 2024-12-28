package io.greenhouse;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@SpringBootApplication
@RestController
public class GreenHouseAssessmentApplication {
	//base url mapping
	//TODO: move to its own controller later.
	@GetMapping("/")
	public ResponseEntity<Map<String, Object>> baseUrl(){
		return ResponseEntity.ok(Map.of("message", "Hello World!", "status", "OK"));
	}
	public static void main(String[] args) {

		SpringApplication.run(GreenHouseAssessmentApplication.class, args);
	}

}
