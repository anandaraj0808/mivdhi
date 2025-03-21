package com.inurance.demo;

import com.inurance.demo.InsurancePolicy;
import com.inurance.demo.InsuranceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:3000")  // Fix CORS issue
@RestController
@RequestMapping("/policies")
public class InsuranceController {

    @Autowired
    private InsuranceService service;

    @GetMapping
    public List<InsurancePolicy> getAllPolicies() {
        return service.getAllPolicies();
    }

    @GetMapping("/search")
    public List<InsurancePolicy> searchPolicies(@RequestParam String name) {
        return service.searchPolicies(name);
    }

    @GetMapping("/filter")
    public List<InsurancePolicy> filterPolicies(
            @RequestParam(required = false) Double minPremium,
            @RequestParam(required = false) Double maxPremium,
            @RequestParam(required = false) String type,
            @RequestParam(required = false) Double coverage
    ) {
        return service.filterPolicies(minPremium, maxPremium, type, coverage);
    }
}

