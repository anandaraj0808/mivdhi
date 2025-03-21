package com.inurance.demo;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class InsurancePolicy {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String type;
    private double premium;
    private double coverage;

    public InsurancePolicy() {}

    public InsurancePolicy(String name, String type, double premium, double coverage) {
        this.name = name;
        this.type = type;
        this.premium = premium;
        this.coverage = coverage;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getType() { return type; }
    public void setType(String type) { this.type = type; }

    public double getPremium() { return premium; }
    public void setPremium(double premium) { this.premium = premium; }

    public double getCoverage() { return coverage; }
    public void setCoverage(double coverage) { this.coverage = coverage; }
}



