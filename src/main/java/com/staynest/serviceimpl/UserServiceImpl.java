package com.staynest.serviceimpl;

import com.staynest.config.BadRequestException;
import com.staynest.config.ResourceNotFoundException;
import com.staynest.entities.User;
import com.staynest.enums.UserStatus;
import com.staynest.repository.UserRepository;
import com.staynest.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    private static final Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);

    private final UserRepository        userRepository;
    private final BCryptPasswordEncoder passwordEncoder;

    @Autowired
    public UserServiceImpl(UserRepository userRepository,
                           BCryptPasswordEncoder passwordEncoder) {
        this.userRepository  = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public User createUser(User user) {
        logger.info("Creating user with email: {}", user.getEmail());

        userRepository.findByEmail(user.getEmail()).ifPresent(existing -> {
            throw new BadRequestException("Email already exists: " + user.getEmail());
        });

        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setStatus(UserStatus.ACTIVE);

        User saved = userRepository.save(user);
        logger.info("User created successfully with ID: {}", saved.getUserId());
        return saved;
    }

    @Override
    public User getUserById(Integer userId) {
        logger.info("Fetching user with ID: {}", userId);
        return userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "User not found with ID: " + userId));
    }

    @Override
    public List<User> getAllUsers() {
        logger.info("Fetching all users");
        return userRepository.findAll();
    }

    @Override
    public User updateUserStatus(Integer userId, UserStatus status) {
        logger.info("Updating status of user ID: {} to {}", userId, status);
        User user = getUserById(userId);
        user.setStatus(status);
        return userRepository.save(user);
    }

    @Override
    public void deleteUser(Integer userId) {
        logger.info("Soft-deleting user ID: {}", userId);
        // Soft delete — sets status to INACTIVE, does not remove from DB
        updateUserStatus(userId, UserStatus.INACTIVE);
    }
}
