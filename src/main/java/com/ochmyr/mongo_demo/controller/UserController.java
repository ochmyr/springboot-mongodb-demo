package com.ochmyr.mongo_demo.controller;

import com.ochmyr.mongo_demo.dto.UserRequestDTO;
import com.ochmyr.mongo_demo.dto.UserResponseDTO;
import com.ochmyr.mongo_demo.mapper.UserMapper;
import com.ochmyr.mongo_demo.model.User;
import com.ochmyr.mongo_demo.service.UserService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@Validated
@RequestMapping("/api/users")
public class UserController {

    private final UserService userService;
    private final UserMapper userMapper;

    @PostMapping("/add")
    public ResponseEntity<UserResponseDTO> create(@Valid @RequestBody UserRequestDTO dto) {
        User created = userService.addUser(userMapper.toEntity(dto));
        return ResponseEntity.status(HttpStatus.CREATED).body(userMapper.toResponse(created));
    }

    @GetMapping
    public ResponseEntity<List<UserResponseDTO>> fetchAllUsers() {
        List<UserResponseDTO> users = userService
                .getAll()
                .stream()
                .map(userMapper::toResponse)
                .toList();
        return ResponseEntity.ok(users);
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserResponseDTO> getUserById(@PathVariable String id) {
        return ResponseEntity.ok(userMapper.toResponse(userService.getById(id)));
    }

    @GetMapping("/adults")
    public ResponseEntity<List<UserResponseDTO>> fetchAdults() {
        List<UserResponseDTO> users = userService
                .getAdults()
                .stream()
                .map(userMapper::toResponse)
                .toList();
        return ResponseEntity.ok(users);
    }

    @GetMapping("/skill/{skill}")
    public ResponseEntity<List<UserResponseDTO>> fetchUsersBySkill(@PathVariable String skill) {
        List<UserResponseDTO> users = userService
                .getBySkill(skill)
                .stream()
                .map(userMapper::toResponse)
                .toList();
        return ResponseEntity.ok(users);
    }

    @GetMapping("/age")
    public ResponseEntity<List<UserResponseDTO>> fetchUsersByAgeRange(
            @RequestParam int min,
            @RequestParam int max) {
        List<UserResponseDTO> users = userService.getByAgeRange(min, max)
                .stream()
                .map(userMapper::toResponse)
                .toList();
        return ResponseEntity.ok(users);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> delete(@PathVariable String id) {
        userService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
