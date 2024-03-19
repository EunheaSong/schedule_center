package com.seed_planner.schedule_center.plan.domain;

import lombok.Getter;

/**
 * Open API를 통해 받아온 주소 데이터를 담는 모델
 */
@Getter
public class Location {
    private Double lat;
    private Double lng;
    private String address;

    @Override
    public String toString() {
        return "Lat : " + this.lat + ", Lng : " + this.lng + ", Address : " + this.address;
    }
}
