package com.nanotech.furniture.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.*;

@Entity
@Table(name = "furniture_images")
public class FurnitureImage {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String image; // local file path or URL

//    @ManyToOne
//    @JoinColumn(name = "furniture_id")
//    private Furniture furniture;
    
    @ManyToOne
    @JoinColumn(name = "furniture_id")
    @JsonBackReference
    private Furniture furniture;


    // Getters & Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getImage() { return image; }
    public void setImage(String image) { this.image = image; }

    public Furniture getFurniture() { return furniture; }
    public void setFurniture(Furniture furniture) { this.furniture = furniture; }
}
