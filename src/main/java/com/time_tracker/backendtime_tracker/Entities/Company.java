package com.time_tracker.backendtime_tracker.Entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Range;

import java.util.HashSet;
import java.util.Set;
//motBlank



@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Company {

    @jakarta.persistence.Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "company_id", nullable = false)
    private Integer id;


    @Column(name = "name", length = 15, nullable = false)
    private String name;

    @Column(name = "industry", length = 25, nullable = true)
    private String industry;

    @Range(min=1800, max= 2022)
    @Column(name = "founded_year", nullable = false )
    private Integer foundedYear;


    @Min(value= 0)
    @Column(name = "budget", nullable = true)
    private Integer budget;

    @OneToMany(mappedBy = "company")
    private Set<Project> projects = new HashSet<>();

}
