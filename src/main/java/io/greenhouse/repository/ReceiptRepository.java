package io.greenhouse.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import io.greenhouse.models.Receipt;

@Repository
public interface ReceiptRepository extends JpaRepository<Receipt, String> {
}