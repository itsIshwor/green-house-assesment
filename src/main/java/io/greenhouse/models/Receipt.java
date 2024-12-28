package io.greenhouse.models;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@Getter
@Setter
@ToString
public class Receipt {

	@Id
	private String id;

	private String retailer;
	private LocalDate purchaseDate;
	private LocalTime purchaseTime;
	private BigDecimal total;

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinColumn(name = "receipt_id")
	@Size(min = 1, message = "A receipt must have at least one item")
	private List<Item> items;

	private Integer points;
}