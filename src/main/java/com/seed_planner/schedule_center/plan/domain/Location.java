package com.seed_planner.schedule_center.plan.domain;


import lombok.AllArgsConstructor;
import lombok.Getter; /**
 * Open API를 통해 받아온 주소 데이터를 담는 모델
 */
@Getter
public class Location {
    private Double lat;
    private Double lng;
    private String address;

    public Location(Double lat, Double lng, String address) {
        valid(lat, lng);
        this.lat = lat;
        this.lng = lng;
        this.address = address;
    }

    private void valid(Double lat, Double lng) {
        if ((lat != null) ^ (lng != null)) throw new IllegalArgumentException("Both \"lat\" and \"lng\" must exist or not.");
    }

    @Override
    public String toString() {
        return "Lat : " + this.lat + ", Lng : " + this.lng + ", Address : " + this.address;
    }

}

