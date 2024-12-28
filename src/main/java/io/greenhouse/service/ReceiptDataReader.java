package io.greenhouse.service;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

import io.greenhouse.models.Receipt;
import io.greenhouse.repository.ReceiptRepository;

//this is is to just read the sample data in src/main/resources
@Service
public class ReceiptDataReader {
	private static final String MORNING_RECEIPT = "/morning-receipt.json";
	private static final String SAMPLE_RECEIPT = "/simple-receipt.json";

	@Autowired
	private ReceiptRepository receiptRepository;

	@Autowired
	private RewardPointsCalculator rewardPointsCalculator;

	// @PostConstruct=> this was added just to test initially.
	public void initData() {
		for (String file : List.of(MORNING_RECEIPT, SAMPLE_RECEIPT)) {
			persistSampleDataToDb(file);
		}
	}

	private void persistSampleDataToDb(String path) {
		try (InputStream inputStream = getClass().getResourceAsStream(path);) {
			ObjectMapper objectMapper = new ObjectMapper();
			objectMapper.registerModule(new JavaTimeModule());

			Receipt receipt = objectMapper.readValue(inputStream, Receipt.class);
			receipt.setId(UUID.randomUUID().toString());
			receipt.setPoints(rewardPointsCalculator.calculatePoints(receipt));
			receiptRepository.save(receipt);

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}