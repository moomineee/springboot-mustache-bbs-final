package com.mustache.bbsfinal.repository;

import com.mustache.bbsfinal.domain.Hospital;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HospitalRepository extends JpaRepository<Hospital, Integer> {
}
