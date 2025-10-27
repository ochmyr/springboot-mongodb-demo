package com.ochmyr.mongo_demo.service;

import com.ochmyr.mongo_demo.exception.UserNotFoundException;
import com.ochmyr.mongo_demo.model.User;
import com.ochmyr.mongo_demo.repository.CustomUserRepository;
import com.ochmyr.mongo_demo.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final CustomUserRepository customUserRepository;

    public User addUser(User user) {
        return userRepository.save(user);
    }

    public List<User> getAll() {
        return userRepository.findAll();
    }

    public User getById(String id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException(id));
    }

    public List<User> getAdults() {
        return userRepository.findByAgeGreaterThan(21);
    }

    public List<User> getByAgeRange(int min, int max) {
        return customUserRepository.findByAgeRange(min, max);
    }

    public List<User> getBySkill(String skill) {
        return userRepository.findBySkills(skill);
    }

    public void delete(String id) {
        if (!userRepository.existsById(id)) {
            throw new UserNotFoundException(id);
        }
        userRepository.deleteById(id);
    }
}
