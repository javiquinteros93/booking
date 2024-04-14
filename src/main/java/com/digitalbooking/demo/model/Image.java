package com.digitalbooking.demo.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;

@Entity
@Table(name = "images")
public class Image {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String url;

    public Image(){

    }
    public Image(String url){
        this.url = url;
    }

    public Long getId() {
        return id;
    }
    public String getUrl() {
        return url;
    }

    public void setId(Long id) {
        this.id = id;
    }
    public void setUrl(String url) {
        this.url = url;
    }
}
