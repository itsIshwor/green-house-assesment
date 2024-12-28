package io.greenhouse.controller;

import java.util.Collections;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.greenhouse.models.Receipt;
import io.greenhouse.service.ReceiptService;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/receipts")
@Slf4j
public class ReceiptController {

	@Autowired
	private ReceiptService receiptService;

	@PostMapping("/process")
	public ResponseEntity<Map<Object, Object>> processReceipt(@Valid @RequestBody Receipt receipt) {
		return ResponseEntity.status(HttpStatus.CREATED)
				.body(Collections.singletonMap("id", receiptService.processReceipt(receipt)));
	}

	@GetMapping("/{id}/points")
	public ResponseEntity<Map<Object, Object>> getPointsById(@PathVariable String id) {
		Integer points = receiptService.getPoints(id);
		return ResponseEntity.ok(Collections.singletonMap("points", points));
	}
}
