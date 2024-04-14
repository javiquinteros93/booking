package com.digitalbooking.demo.model;

import javax.persistence.*;

@Entity
@Table(name = "scores")
public class Score {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private int score;

    public Score() {}

    public Long getId() {
        return id;
    }
    public int getScore() {
        return score;
    }

    public void setId(Long id) {
        this.id = id;
    }
    public void setScore(int score) {
        this.score = score;
    }
}
