package com.jpdev.collaborative_list.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class CreateRoomRequest {

    @NotBlank(message = "Creator name cannot be blank")
    @Size(min = 2, max = 100, message = "Creator name must be between 2 and 100 characters")
    private String creatorName;
}
