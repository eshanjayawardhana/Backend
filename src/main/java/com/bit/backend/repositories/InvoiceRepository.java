package com.bit.backend.repositories;

import com.bit.backend.entities.InvoiceEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InvoiceRepository extends JpaRepository<InvoiceEntity,Integer> {
}
