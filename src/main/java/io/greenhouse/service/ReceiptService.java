package io.greenhouse.service;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import io.greenhouse.exception.NotFoundException;
import io.greenhouse.models.Receipt;
import io.greenhouse.repository.ReceiptRepository;

@Service
public class ReceiptService {

	@Autowired
	private ReceiptRepository receiptRepository;
	@Autowired
	private RewardPointsCalculator rewardPointsCalculator;

	public String processReceipt(Receipt receipt) {
		receipt.setId(UUID.randomUUID().toString());
		int points = rewardPointsCalculator.calculatePoints(receipt);
		receipt.setPoints(points);
		receiptRepository.save(receipt);
		return receipt.getId();
	}

	public Integer getPoints(String id) {
		return receiptRepository.findById(id).map(Receipt::getPoints).orElseThrow(
				() -> new NotFoundException(HttpStatus.NOT_FOUND, String.format("Receipt with: id %s not found", id)));
	}
}
