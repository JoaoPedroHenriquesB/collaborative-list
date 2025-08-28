package com.jpdev.collaborative_list.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class ItemRequest {

    @NotBlank(message = "Item name cannot be blank")
    private String itemName;
}