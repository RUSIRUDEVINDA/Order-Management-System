package com.inventory.service.service;

import com.inventory.service.dto.InventoryDTO;
import com.inventory.service.model.Inventory;
import com.inventory.service.repo.InventoryRepo;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class InventoryService {

    @Autowired
    private InventoryRepo inventoryRepo;

    @Autowired
    private ModelMapper modelMapper;

    public List<InventoryDTO> getAllItems() {
        List<Inventory> inventoryList = inventoryRepo.findAll();
        return modelMapper.map(inventoryList, new TypeToken<List<InventoryDTO>>(){}.getType());
    }

    public InventoryDTO getItemById(Integer id) {
        Inventory inventory = inventoryRepo.findById(id).orElse(null);
        return modelMapper.map(inventory, InventoryDTO.class);
    }

    public InventoryDTO addItem(InventoryDTO inventoryDTO) {
        inventoryRepo.save(modelMapper.map(inventoryDTO, Inventory.class));
        return inventoryDTO;
    }

    public InventoryDTO updateItem(InventoryDTO inventoryDTO) {
        inventoryRepo.save(modelMapper.map(inventoryDTO, Inventory.class));
        return inventoryDTO;
    }

    public String deleteItem(InventoryDTO inventoryDTO) {
        inventoryRepo.delete(modelMapper.map(inventoryDTO, Inventory.class));
        return "ITEM DELETED";
    }
}