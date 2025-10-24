package com.ochmyr.mongo_demo.dto;


import jakarta.validation.constraints.*;

import java.util.List;

public record UserRequestDTO(
        @NotEmpty(message = "Name can not be a null or empty")
        @Size(min = 5, max = 30, message = "The length of the customer name should be between 5 and 30")
        String name,

        @NotNull(message = "Age is required")
        @Positive(message = "Age must be greater than 0")
        Integer age,

        List<String> skills
) {}
