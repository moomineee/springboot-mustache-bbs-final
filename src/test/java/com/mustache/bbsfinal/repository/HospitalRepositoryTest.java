package com.mustache.bbsfinal.repository;

import com.mustache.bbsfinal.domain.Hospital;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
class HospitalRepositoryTest {

    @Autowired
    HospitalRepository hospitalRepository;

    @Test
    void printHospitalNameAndAddress(List<Hospital> hospitals) {
        for (var hospital : hospitals) {
            System.out.printf("%s | %s %f\n", hospital.getHospitalName(), hospital.getRoadNameAddress(),
                    hospital.getTotalAreaSize());
        }
        System.out.println(hospitals.size());
    }

    @Test
    @DisplayName("송파구가 포함된 병원 리스트 출력")
    void containing() {
        List<Hospital> hospitals = hospitalRepository.findByRoadNameAddressContaining("송파구");
        printHospitalNameAndAddress(hospitals);
    }

    @Test
    @DisplayName("경희로 시작하는 병원 리스트 출력")
    void startswith() {
        List<Hospital> hospitals = hospitalRepository.findByHospitalNameStartsWith("경희");
        printHospitalNameAndAddress(hospitals);
    }

    @Test
    @DisplayName("'병원'으로 끝나는 리스트 출력")
    void endsWith() {
        List<Hospital> hospitals = hospitalRepository.findByHospitalNameEndsWith("병원"); // 의원, 병원 ...
        printHospitalNameAndAddress(hospitals);
    }

    @Test
    @DisplayName("병상수가 10개 이상 20개 미만인 병원 리스트 출력")
    void findByPatientRoomCountAndPatientRoomCount() {
        List<Hospital> hospitals = hospitalRepository.findByPatientRoomCountGreaterThanAndPatientRoomCountLessThan(10, 20);
        printHospitalNameAndAddress(hospitals);
    }

    @Test
    @DisplayName("병상수가 10개 이상 20개 미만인 병원 리스트 출력(Between")
    void findPatientRommCountBetween() {
        List<Hospital> hospitals = hospitalRepository.findByPatientRoomCountBetween(10, 20);
    }



    @Test
    @DisplayName("BusinessTypeName이 보건소 보건지소 보건진료소인 데이터가 잘 나오는지")
    void findByBusinessTypeNameIn() {
        List<String> inClues = new ArrayList<>();
        inClues.add("보건소");
        inClues.add("보건지소");
        inClues.add("보건진료소");
        List<Hospital> hospitals = hospitalRepository.findByBusinessTypeNameIn(inClues);
        for (var hospital : hospitals) {
            System.out.println(hospital.getHospitalName());
        }
    }
}