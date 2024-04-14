package com.digitalbooking.demo.model.dto;

import com.digitalbooking.demo.model.Score;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.Set;

@JsonIgnoreProperties(ignoreUnknown = true)
public class ReservationProductDTO {
    private Long id;
    private String title;
    private String city;
    private String address;
    private String category;
    private ImageDTO images;
    private int scores;
    private Set<PolicyDTO> policies;
    @JsonIgnoreProperties({"product","user"})
    private Set<ReservationDTO> reservations;

    public ReservationProductDTO() {}

    public Long getId() {
        return id;
    }
    public String getTitle() {
        return title;
    }
    public ImageDTO getImages() {
        return images;
    }
    public int getScores() {
        return scores;
    }
    public String getCategory() {
        return category;
    }
    public String getCity() {
        return city;
    }
    public Set<PolicyDTO> getPolicies() {
        return policies;
    }
    public String getAddress() {
        return address;
    }
    public Set<ReservationDTO> getReservations() {
        return reservations;
    }

    public void setId(Long id) {
        this.id = id;
    }
    public void setTitle(String title) {
        this.title = title;
    }
    public void setImages(Set<ImageDTO> images) {
        this.images = images.iterator().next();
    }
    public void setScores(Set<Score> scores) {
        int sum = 0;
        for (Score s:
                scores) {
            sum += s.getScore();
        }
        this.scores = sum/scores.size();
    }
    public void setCategory(CategoryDTO category) {
        this.category = category.getTitle();
    }
    public void setCity(CityDTO city) {
        this.city = city.getName() + ", " + city.getCountry();
    }
    public void setPolicies(Set<PolicyDTO> policies) {
        this.policies = policies;
    }
    public void setAddress(String address) {
        this.address = address;
    }
    public void setReservations(Set<ReservationDTO> reservations) {
        this.reservations = reservations;
    }
}
