package com.mustache.bbsfinal.ArticleController;

import com.mustache.bbsfinal.domain.dto.HospitalResponse;
import com.mustache.bbsfinal.service.HospitalService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/hospitals")
public class HospitalRestController {

    private final HospitalService hospitalService;

    public HospitalRestController(HospitalService hospitalService) {
        this.hospitalService = hospitalService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<HospitalResponse> get(@PathVariable Integer id) { //ResponseEntity도 Dto타입
        //Hospital.of를 Static으로 선언하여 바로 사용 할 수 있다.
        //Static선언은 Hospital.of(hospital.get())으로 가독성이 좋다 Static이 아닐경우 -> hospital.get().of(hospital.get())
        HospitalResponse hospitalResponse = hospitalService.getHospital(id); //Dto
        return ResponseEntity.ok().body(hospitalResponse); //return은 Dto로 한다.
    }
}

