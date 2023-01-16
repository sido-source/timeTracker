package com.time_tracker.backendtime_tracker.Entities;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Contractor {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id", nullable = false)
    private Long id;


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
