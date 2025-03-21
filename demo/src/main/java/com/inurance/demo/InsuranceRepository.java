package com.inurance.demo;

import com.inurance.demo.InsurancePolicy;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface InsuranceRepository extends JpaRepository<InsurancePolicy, Long> {
    List<InsurancePolicy> findByNameContainingIgnoreCase(String name);
}


