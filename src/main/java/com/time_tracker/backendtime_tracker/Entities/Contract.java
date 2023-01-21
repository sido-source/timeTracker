package com.time_tracker.backendtime_tracker.Entities;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.*;

import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class Contract {

    @Id
    @SequenceGenerator(name = "contractGenerator", sequenceName = "CONTRACT_SEQUENCE", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "contractGenerator")
    @Column(name = "id", nullable = false)
    private Long id;

    @Getter
    @NotNull(message = "Start date can not be null")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @Column(name = "start_date")
    private Date startDate;

    @DateTimeFormat(pattern="yyyy-MM-dd")
    @NotNull(message = "End date can not be null")
    @Column(name = "end_date")
    private Date endDate;

    @Min(value = 130, message = "Minimal daily salary is set to 130")
    @Column(name = "daily_salary", nullable = false)
    private Float dailySalary;

    @Column(name = "description", length = 150, nullable = true)
    private String description;

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

}
