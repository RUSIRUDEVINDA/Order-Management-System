package com.inventory.service.controller;

import com.inventory.service.dto.InventoryDTO;
import com.inventory.service.service.InventoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping(value = "api/v1/inventories")
public class InventoryController {

    @Autowired
    private InventoryService inventoryService;

    @GetMapping("/getItems")
    public List<InventoryDTO> getItems() {
        return inventoryService.getAllItems();
    }

    @GetMapping("/getItem/{itemID}")
    public InventoryDTO getItemById(@PathVariable Integer itemID) {
        return inventoryService.getItemById(itemID);
    }

    @PostMapping("/addItem")
    public InventoryDTO addItem(@RequestBody InventoryDTO inventoryDTO) {
        return inventoryService.addItem(inventoryDTO);
    }

    @PutMapping("/updateItem")
    public InventoryDTO updateItem(@RequestBody InventoryDTO inventoryDTO) {
        return inventoryService.updateItem(inventoryDTO);
    }

    @DeleteMapping("/deleteItem/{id}")
    public String deleteItemById(@PathVariable("id") Integer id) {
        return inventoryService.deleteItemById(id);
    }
}