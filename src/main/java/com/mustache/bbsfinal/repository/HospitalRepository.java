package com.mustache.bbsfinal.repository;

import com.mustache.bbsfinal.domain.Hospital;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface HospitalRepository extends JpaRepository<Hospital, Integer> {
    List<Hospital> findByBusinessTypeNameIn(List<String> businessTypes);

    List<Hospital> findByRoadNameAddressContaining(String keyword); // 포함
    List<Hospital> findByHospitalNameStartsWith(String keyword); // 시작
    List<Hospital> findByHospitalNameEndsWith(String keyword); // 끝남

    List<Hospital> findByPatientRoomCountGreaterThanAndPatientRoomCountLessThan(int var1, int var2);
    List<Hospital> findByPatientRoomCountBetween(int a, int b); // JPQL Between. 위와 비교할때 가독성, 사용성이 더 좋다.

    List<Hospital> findByPatientRoomCountBetweenOrderByPatientRoomCountDesc(int a, int b); // orderBy. 내림차순 정렬

}
