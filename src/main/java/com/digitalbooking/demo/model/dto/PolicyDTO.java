package com.digitalbooking.demo.model.dto;

import com.digitalbooking.demo.model.Type;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class PolicyDTO {
    private String name;
    private String type;

    public PolicyDTO() {}

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type.getType();
    }
}
