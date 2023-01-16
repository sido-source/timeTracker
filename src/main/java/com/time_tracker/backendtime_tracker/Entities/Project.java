package com.time_tracker.backendtime_tracker.Entities;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.util.HashSet;
import java.util.Set;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class Project {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id", nullable = false)
    private Long id;

    @Getter
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @Column(name = "start_date", nullable = false)
    private Date startDate;

    @DateTimeFormat(pattern="yyyy-MM-dd")
    @Column(name = "end_date", nullable = false)
    private Date endDate;

    @Min(1)
    @Column(name = "daily_salary", nullable = false)
    private Float contractorDailySalary;


    @NotNull
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "company_id")
    @Setter
    private Company company;


    @NotNull
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "contractor_id")
    @Setter
    private Contractor contractor;

    @Column(name = "description", length = 150, nullable = true)
    private String description;
}
