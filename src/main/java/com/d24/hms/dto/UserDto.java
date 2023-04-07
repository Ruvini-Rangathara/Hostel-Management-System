package com.d24.hms.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDto implements SuperDto{
    private String username;
    private String password;
    private String jobRole;
    private String passwordHint;
}
