package com.inventory.service.repo;

import com.inventory.service.model.Inventory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.management.ValueExp;

@Repository
public interface InventoryRepo extends JpaRepository<Inventory, Integer> {
    @Query(value = "SELECT * FROM inventory WHERE itemID = ?1", nativeQuery = true)
    Inventory getInventoryByItemID(int itemID);

}