package com.mustache.bbsfinal.domain;

import lombok.Getter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "nation_wide_hospitals")
@Getter
public class Hospital {
    @Id // generatedValue 쓰지 않는다. 입력을 해놓았기 때문
    private Integer id;

    @Column(name = "road_name_address")
    private String roadNameAddress;

    @Column(name ="hospitalName")
    private String hospitalName;
    private Integer patientRoomCount;
    private Integer totalNumberOfBeds;
    private String businessTypeName;
    private Float totalAreaSize;
}
