package com.inurance.demo;

import com.inurance.demo.InsurancePolicy;
import com.inurance.demo.InsuranceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class InsuranceService {

    @Autowired
    private InsuranceRepository repository;

    public List<InsurancePolicy> getAllPolicies() {
        return repository.findAll();
    }

    public List<InsurancePolicy> searchPolicies(String name) {
        return repository.findByNameContainingIgnoreCase(name);
    }

    public List<InsurancePolicy> filterPolicies(Double minPremium, Double maxPremium, String type, Double minCoverage) {
        return repository.findAll().stream()
                .filter(policy -> (minPremium == null || policy.getPremium() >= minPremium) &&
                        (maxPremium == null || policy.getPremium() <= maxPremium) &&
                        (type == null || type.isEmpty() || policy.getType().equalsIgnoreCase(type)) &&
                        (minCoverage == null || policy.getCoverage() >= minCoverage))
                .collect(Collectors.toList());
    }
}


