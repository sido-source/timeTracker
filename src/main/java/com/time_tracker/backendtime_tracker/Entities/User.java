package com.time_tracker.backendtime_tracker.Entities;

import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collection;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {

    @Id
    @SequenceGenerator(name = "userGenerator", sequenceName = "USER_SEQUENCE", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "userGenerator")
    private Long Id;
    private String name;
    private String username;
    private String password;

    @ManyToMany( fetch = FetchType.EAGER)
    private Collection<Role> roles = new ArrayList<>();
}
