package com.digitalbooking.demo.repository;

import com.digitalbooking.demo.model.Caracteristic;
import com.digitalbooking.demo.model.Caracteristic;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CaracteristicRepository extends JpaRepository<Caracteristic, Long> {
    @Query("SELECT c FROM Caracteristic c WHERE c.name = ?1")
    Optional<List<Caracteristic>> findByCaracteristicName(String name);
}
