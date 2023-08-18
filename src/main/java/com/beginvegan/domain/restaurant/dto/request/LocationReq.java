package com.beginvegan.domain.restaurant.dto.request;

import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Data
public class LocationReq {

    private Long id;

    private String latitude;

    private String longitude;

    public LocationReq(Long id, String latitude, String longitude) {
        this.id = id;
        this.latitude = latitude;
        this.longitude = longitude;
    }
}
