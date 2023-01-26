package com.time_tracker.backendtime_tracker.Entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Role {
    @javax.persistence.Id
    @SequenceGenerator(name = "rolesGenerator", sequenceName = "ROLES_SEQUENCE", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "rolesGenerator")
    private Long Id;
    private String name;
}
