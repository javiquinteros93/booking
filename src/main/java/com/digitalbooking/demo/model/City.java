package com.digitalbooking.demo.model;

import javax.persistence.*;

@Entity
@Table (name="cities")
public class City {

    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Id
    private Long id;
    private String name;
    private double latitude;
    private double longitude;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "countries_id", referencedColumnName = "id")
    private Country country;

    public City() {
    }

    public City(String name) {
        this.name = name;
    }

    public Long getId() {
        return id;
    }
    public String getName() {
        return name;
    }
    public Country getCountry() {
        return country;
    }
    public double getLatitude() {
        return latitude;
    }
    public double getLongitude() {
        return longitude;
    }

    public void setName(String name) {
        this.name = name;
    }
    public void setCountry(Country country) {
        this.country = country;
    }
    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }
    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }
}
