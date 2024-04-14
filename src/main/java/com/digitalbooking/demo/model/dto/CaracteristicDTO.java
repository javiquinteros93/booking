package com.digitalbooking.demo.model.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class CaracteristicDTO {
    private String name;
    private String icon_url;

    public CaracteristicDTO() {}

    public String getName() {
        return name;
    }
    public String getIcon_url() {
        return icon_url;
    }

    public void setName(String name) {
        this.name = name;
    }
    public void setIcon_url(String icon_url) {
        this.icon_url = icon_url;
    }
}
