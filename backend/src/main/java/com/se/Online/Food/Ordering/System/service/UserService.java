package com.se.Online.Food.Ordering.System.service;

import com.se.Online.Food.Ordering.System.dto.UserDTO;
import org.springframework.security.core.userdetails.UserDetails;

public interface UserService {
    UserDTO registerUser(UserDTO userDTO);

    UserDTO getUserByUsername(String username);

//    boolean loginUser(String username, String password);

    UserDetails loadUserByUsername(String username);
}
