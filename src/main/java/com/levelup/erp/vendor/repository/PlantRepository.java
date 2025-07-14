package com.levelup.erp.vendor.repository;

import com.levelup.erp.vendor.model.Plant;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PlantRepository extends JpaRepository<Plant, Long> {

    Optional<Plant> findByName(String name);

}
