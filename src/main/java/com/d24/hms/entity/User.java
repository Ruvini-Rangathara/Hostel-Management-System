package com.d24.hms.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity(name="user")
public class User implements SuperEntity{
    @Id
    private String username;
    private String password;
    private String jobRole;
    private String passwordHint;
}
