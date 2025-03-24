package com.se.Online.Food.Ordering.System.dto;

import com.se.Online.Food.Ordering.System.model.Role;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDTO {
    private Long id;
    private String username;
    private String password;
    private Role role;
}
