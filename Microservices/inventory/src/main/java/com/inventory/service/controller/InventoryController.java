package com.inventory.service.controller;

import com.inventory.service.dto.InventoryDTO;
import com.inventory.service.service.InventoryService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping(value = "api/vi/inventory")

public class InventoryController {
    @Autowired
    private InventoryService inventoryService;

    @Autowired
    private ModelMapper modelMapper;

    @GetMapping
    public List<InventoryDTO> getAllItems(){
        return inventoryService.getAllItems();
    }

    @GetMapping("/{itemID}")
    public InventoryDTO getItemById(@PathVariable Integer itemID) {
        return inventoryService.getItemById(itemID);
    }

    @PostMapping
    public InventoryDTO createItem(@RequestBody InventoryDTO inventoryDTO) {
        return inventoryService.createItem(inventoryDTO);
    }

    @PutMapping("/{itemID}")
    public InventoryDTO updateItem(@RequestBody InventoryDTO inventoryDTO) {
        return inventoryService.updateItem(inventoryDTO);
    }

    @DeleteMapping("/{itemID}")
    public String deleteItem(@PathVariable Integer itemID) {
        return inventoryService.deleteItem(itemID);
    }

}
