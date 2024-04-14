package com.digitalbooking.demo.model.dto;

import com.digitalbooking.demo.model.Image;
import com.digitalbooking.demo.model.Reservation;
import com.digitalbooking.demo.model.Score;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.Set;

@JsonIgnoreProperties(ignoreUnknown = true)
public class HomeProductDTO {
    private Long id;
    private String title;
    private String description;
    private double latitude;
    private double longitude;
    private CategoryDTO category;
    private CityDTO city;
    private ImageDTO images;
    private Set<CaracteristicDTO> caracteristics;
    private int scores;

    public HomeProductDTO() {}

    public Long getId() {
        return id;
    }
    public String getTitle() {
        return title;
    }
    public String getDescription() {
        return description;
    }
    public double getLatitude() {
        return latitude;
    }
    public double getLongitude() {
        return longitude;
    }
    public ImageDTO getImages() {
        return images;
    }
    public Set<CaracteristicDTO> getCaracteristics() {
        return caracteristics;
    }
    public int getScores() {
        return scores;
    }
    public CategoryDTO getCategory() {
        return category;
    }
    public CityDTO getCity() {
        return city;
    }

    public void setId(Long id) {
        this.id = id;
    }
    public void setTitle(String title) {
        this.title = title;
    }
    public void setDescription(String description) {
        this.description = description;
    }
    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }
    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }
    public void setImages(Set<ImageDTO> images) {
        this.images = images.iterator().next();
    }
    public void setCaracteristics(Set<CaracteristicDTO> caracteristics) {
        this.caracteristics = caracteristics;
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
        this.category = category;
    }
    public void setCity(CityDTO city) {
        this.city = city;
    }
}
