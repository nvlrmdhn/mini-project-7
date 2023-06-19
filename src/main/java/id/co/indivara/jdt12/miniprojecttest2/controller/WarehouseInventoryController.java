package id.co.indivara.jdt12.miniprojecttest2.controller;

import id.co.indivara.jdt12.miniprojecttest2.entity.Merchandise;
import id.co.indivara.jdt12.miniprojecttest2.entity.Warehouse;
import id.co.indivara.jdt12.miniprojecttest2.entity.WarehouseInventory;
import id.co.indivara.jdt12.miniprojecttest2.repo.WarehouseInventoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class WarehouseInventoryController {
    @Autowired
    WarehouseInventoryRepository warehouseInventoryRepository;

    @GetMapping("/all")
    public List<WarehouseInventory> viewWarehouseInventory() {
        return warehouseInventoryRepository.findAll();
    }
}
