package com.levelup.erp.material.repository;

import com.levelup.erp.material.models.FinishedMaterial;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface FinishedMaterialRepository extends JpaRepository<FinishedMaterial, Long> {


    @Modifying
    @Transactional
    @Query("DELETE FROM FinishedMaterial fm WHERE fm.headerMaterial = :headerMaterial")
    void deleteByHeaderMaterial(@Param("headerMaterial") String headerMaterial);

}
