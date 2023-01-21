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
public class Contractor {

    @Id
    @SequenceGenerator(name = "contractorGenerator", sequenceName = "CONTRACTOR_SEQUENCE", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "contractorGenerator")
    @Column(name = "id", nullable = false)
    private Long id;

    @NotNull(message = "Name cannot be null")
    @Length(max = 15, message = "Maximum 15 characters")
    @Column(name = "name")
    private String name;

    @NotNull(message = "Surname cannot be null")
    @Length(max = 20, message = "Maximum 20 characters")
    @Column(name = "surname")
    private String surname;


    @Length(max = 20, message = "Departament 20 characters")
    @Column(name = "department", length = 20, nullable = true)
    private String department;

    @NotNull(message = "Position can not be null")
    @Size(max = 20, message = "Position max size is 20 ")
    @Column(name = "position", nullable = false)
    private String position;
    @NotNull(message = "Pesel can not be null")
    @Digits(integer=11, fraction=0, message = "Pesel must have 11 digits")
    private  Long pesel;

    @JsonIgnore
    @OneToMany(mappedBy = "contractor")
    private Set<Contract> contracts = new HashSet<>();
}
