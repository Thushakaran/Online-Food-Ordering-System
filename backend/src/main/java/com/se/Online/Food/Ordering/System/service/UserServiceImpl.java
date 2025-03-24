package com.se.Online.Food.Ordering.System.service;

import com.se.Online.Food.Ordering.System.dto.UserDTO;
import com.se.Online.Food.Ordering.System.exception.ResourceNotFoundException;
import com.se.Online.Food.Ordering.System.model.CustomUserDetails;
import com.se.Online.Food.Ordering.System.model.User;
import com.se.Online.Food.Ordering.System.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;
    
    @Autowired
    private BCryptPasswordEncoder passwordEncoder;  // Inject BCryptPasswordEncoder

    public UserDTO registerUser(UserDTO userDTO) {
        User user = new User();
        user.setUsername(userDTO.getUsername());
        user.setPassword(passwordEncoder.encode(userDTO.getPassword()));  // Encode the password
        user.setRole(userDTO.getRole());

        user = userRepository.save(user);
        return new UserDTO(user.getId(), user.getUsername(), user.getPassword(), user.getRole());
    }


    public UserDTO getUserByUsername(String username) {
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new ResourceNotFoundException("User", "username", username));
        return new UserDTO(user.getId(), user.getUsername(), user.getPassword(), user.getRole());
    }

    @Override
    public UserDetails loadUserByUsername(String username) {
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new ResourceNotFoundException("User", "username", username));

        return new CustomUserDetails(user);  // Return the custom user details object
    }
}
