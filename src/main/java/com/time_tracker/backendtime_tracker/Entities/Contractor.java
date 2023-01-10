package com.time_tracker.backendtime_tracker.Entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashSet;
import java.util.Set;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Contractor {

    @jakarta.persistence.Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id", nullable = false)
    private Integer id;
    //LONGtch

    @Column(name = "name", length = 15, nullable = false)
    private String name;

    @Column(name = "surname", length = 20, nullable = false)
    private String surname;


    @Column(name = "department", length = 20, nullable = true)
    private String department;

    @Size(max = 20)
    @Column(name = "position", nullable = false)
    private String position;

    @OneToMany(mappedBy = "contractor")
    private Set<Project> projects = new HashSet<>();
}
