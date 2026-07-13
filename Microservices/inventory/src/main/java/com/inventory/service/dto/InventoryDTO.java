package com.inventory.service.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor

public class InventoryDTO {
    private int id;
    private int itemID;
    private int productID;
    private int quantity;
}
