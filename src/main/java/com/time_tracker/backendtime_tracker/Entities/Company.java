package com.time_tracker.backendtime_tracker.Entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import org.hibernate.validator.constraints.Length;

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
public class Company {

    @Id
    @SequenceGenerator(name = "companyGenerator", sequenceName = "COMPANY_SEQUENCE", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "companyGenerator")
    @Column(name = "company_id", nullable = false)
    private Long id;


    @NotNull(message = "Name cannot be null")
    @Length(max = 25, message = "Maximum 15 characters")
    @Column(name = "name")
    private String name;

    @Length(max = 25, message = "Maxiumum 25 characters")
    @Column(name = "industry", nullable = true)
    private String industry;

    @Min(value = 1500, message = "Minimum year is 1500")
    @Max(value = 2022, message ="Maximum year is 2022" )
    @NotNull(message = "Founded year cannot be null")
    @Column(name = "founded_year", nullable = false )
    private Integer foundedYear;


    @Min(value= 0, message = "Minimum budget is 0")
    @Column(name = "budget", nullable = true)
    private Integer budget;

    @JsonIgnore
    @OneToMany(mappedBy = "company")
    private Set<Contract> contracts = new HashSet<>();

}
