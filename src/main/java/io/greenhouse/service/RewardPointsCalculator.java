package io.greenhouse.service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

import org.springframework.stereotype.Component;

import io.greenhouse.models.Item;
import io.greenhouse.models.Receipt;

@Component
public class RewardPointsCalculator {

	public int calculatePoints(Receipt receipt) {
		int points = 0;

		points += calculateRetailerPoints(receipt.getRetailer());

		if (isRoundDollarAmount(receipt.getTotal())) {
			points += 50;
		}

		if (isMultipleOfQuarter(receipt.getTotal())) {
			points += 25;
		}

		points += (receipt.getItems().size() / 2) * 5;
		points += calculateItemDescriptionPoints(receipt.getItems());

		if (isDayOdd(receipt.getPurchaseDate())) {
			points += 6;
		}

		if (isPurchaseTimeInRange(receipt.getPurchaseTime(), LocalTime.of(14, 0), LocalTime.of(16, 0))) {
			points += 10;
		}

		return points;
	}

	private int calculateRetailerPoints(String retailer) {
		return retailer.replaceAll("[^a-zA-Z0-9]", "").length();
	}

	private boolean isRoundDollarAmount(BigDecimal total) {
		return total.stripTrailingZeros().scale() <= 0;
	}

	private boolean isMultipleOfQuarter(BigDecimal total) {
		return total.remainder(BigDecimal.valueOf(0.25)).compareTo(BigDecimal.ZERO) == 0;
	}

	private int calculateItemDescriptionPoints(List<Item> items) {
		int points = 0;
		for (Item item : items) {
			String description = item.getShortDescription().trim();
			if (description.length() % 3 == 0) {
				points += item.getPrice().multiply(BigDecimal.valueOf(0.2)).setScale(0, RoundingMode.UP).intValue();
			}
		}
		return points;
	}

	private boolean isDayOdd(LocalDate purchaseDate) {
		return purchaseDate.getDayOfMonth() % 2 != 0;
	}

	private boolean isPurchaseTimeInRange(LocalTime purchaseTime, LocalTime start, LocalTime end) {
		return purchaseTime.isAfter(start) && purchaseTime.isBefore(end);
	}
}