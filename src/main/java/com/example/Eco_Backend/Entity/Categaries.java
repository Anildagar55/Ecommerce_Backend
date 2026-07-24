package com.example.Eco_Backend.Entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Table(name = "categories")
@Builder
@AllArgsConstructor
public class Categaries {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    @Column(nullable = false)
    private String name;

    @ManyToOne
    @JoinColumn(name = "parent_id",nullable = false)
    private Categaries parent;
}
