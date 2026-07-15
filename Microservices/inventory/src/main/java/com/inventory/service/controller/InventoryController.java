package com.inventory.service.controller;

import com.inventory.service.dto.InventoryDTO;
import com.inventory.service.service.InventoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping(value = "api/v1/")
public class InventoryController {

    @Autowired
    private InventoryService inventoryService;

    @GetMapping("/getItems")
    public List<InventoryDTO> getItems() {
        return inventoryService.getAllItems();
    }

    @GetMapping("/getItem/{id}")
    public InventoryDTO getItemById(@PathVariable("id") Integer id) {
        return inventoryService.getItemById(id);
    }

    @PostMapping("/addItem")
    public InventoryDTO addItem(@RequestBody InventoryDTO inventoryDTO) {
        return inventoryService.addItem(inventoryDTO);
    }

    @PutMapping("/updateItem")
    public InventoryDTO updateItem(@RequestBody InventoryDTO inventoryDTO) {
        return inventoryService.updateItem(inventoryDTO);
    }

    @DeleteMapping("/deleteItem")
    public String deleteItem(@RequestBody InventoryDTO inventoryDTO) {
        return inventoryService.deleteItem(inventoryDTO);
    }
}