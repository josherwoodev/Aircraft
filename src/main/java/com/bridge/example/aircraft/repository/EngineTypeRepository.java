package com.bridge.example.aircraft.repository;

import com.bridge.example.aircraft.entity.EngineType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EngineTypeRepository extends JpaRepository<EngineType, Long> {
}
