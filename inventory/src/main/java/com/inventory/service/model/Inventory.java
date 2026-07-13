package com.inventory.service.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data

public class Inventory {
    @Id
    private int id;
    private int itemID;
    private int productID;
    private int quantity;
}
