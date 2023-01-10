package com.time_tracker.backendtime_tracker.Entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.hibernate.validator.constraints.Range;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Entity (name = "Project")
@Table (name = "project")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Project {

    @jakarta.persistence.Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id", nullable = false)
    private Integer id;

    @DateTimeFormat(pattern="yyyy-MM-dd")
    @Column(name = "start_date", nullable = false)
    private Date startDate;

    @DateTimeFormat(pattern="yyyy-MM-dd")
    @Column(name = "end_date", nullable = false)
    private Date endDate;

    @Min(1)
    @Column(name = "daily_salary", nullable = false)
    private Float dailySalary;


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
