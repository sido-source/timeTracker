package com.time_tracker.backendtime_tracker.Entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.lang.Nullable;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class UserToAccount {

    @Id
    @SequenceGenerator(name = "userToAccGenerator", sequenceName = "USER_TO_ACC_SEQUENCE", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "userToAccGenerator")
    private Long id;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id")
    private User user;

    @Nullable
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "company_id")
    @Setter
    private Company company;


    @Nullable
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "contractor_id")
    @Setter
    private Contractor contractor;
}
