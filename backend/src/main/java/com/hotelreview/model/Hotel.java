package com.hotelreview.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "hotels")
public class Hotel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Hotel name is required")
    private String name;

    @NotBlank(message = "Location is required")
    private String location;

    @Column(length = 1000)
    private String description;

    private String imageUrl;

    @OneToMany(mappedBy = "hotel", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonIgnore
    private List<Review> reviews = new ArrayList<>();

    public Hotel() {}

    public Hotel(String name, String location, String description, String imageUrl) {
        this.name = name;
        this.location = location;
        this.description = description;
        this.imageUrl = imageUrl;
    }

    // ----- Getters & Setters -----
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getLocation() { return location; }
    public void setLocation(String location) { this.location = location; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    public String getImageUrl() { return imageUrl; }
    public void setImageUrl(String imageUrl) { this.imageUrl = imageUrl; }

    public List<Review> getReviews() { return reviews; }
    public void setReviews(List<Review> reviews) { this.reviews = reviews; }

    // Computed fields used by the frontend
    public double getAverageRating() {
        if (reviews == null || reviews.isEmpty()) return 0.0;
        double sum = 0;
        for (Review r : reviews) sum += r.getRating();
        return Math.round((sum / reviews.size()) * 10.0) / 10.0;
    }

    public int getReviewCount() {
        return reviews == null ? 0 : reviews.size();
    }
}
