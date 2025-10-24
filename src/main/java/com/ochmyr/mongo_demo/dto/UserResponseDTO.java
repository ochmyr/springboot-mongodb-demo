package com.ochmyr.mongo_demo.dto;

import java.util.List;

public record UserResponseDTO(
        String id,
        String name,
        int age,
        List<String> skills
) {}
