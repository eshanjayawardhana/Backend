package com.bit.backend.repositories;

import com.bit.backend.entities.ChemicalEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ChemicalRepository extends JpaRepository<ChemicalEntity,Integer> {
}
