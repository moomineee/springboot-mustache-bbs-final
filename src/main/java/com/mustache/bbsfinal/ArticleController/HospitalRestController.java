package com.mustache.bbsfinal.ArticleController;

import com.mustache.bbsfinal.domain.dto.HospitalResponse;
import com.mustache.bbsfinal.service.HospitalService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/hospitals")
public class HospitalRestController {
    HospitalService hospitalService;

    public HospitalRestController(HospitalService hospitalService){
        this.hospitalService=hospitalService;
    }
    @GetMapping("/{id}")
    public ResponseEntity<HospitalResponse> get(@PathVariable Integer id){
        HospitalResponse hospitalResponse = hospitalService.getHospital(id); // DTO
        return ResponseEntity.ok().body(hospitalResponse); // Return은 DTO로
    }
}

