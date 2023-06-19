package id.co.indivara.jdt12.miniprojecttest2.controller;

import id.co.indivara.jdt12.miniprojecttest2.entity.Warehouse;
import id.co.indivara.jdt12.miniprojecttest2.repo.WarehouseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.NoSuchElementException;

@RestController
public class WarehouseController {

    @Autowired
    WarehouseRepository warehouseRepository;

    @PostMapping("/create/warehouse")
    public Warehouse createWarehouse(@RequestBody Warehouse warehouse){
        return warehouseRepository.save(warehouse);
    }

    @GetMapping("/find/warehouse/{warehouseId}")
    public Warehouse findById(@PathVariable String warehouseId){
     return warehouseRepository.findById(warehouseId).get();
    }

    @GetMapping("/find/all")
    public List<Warehouse> findAll(){
        return warehouseRepository.findAll();
    }

    @DeleteMapping("/delete/warehouse/{warehouseId}")
    public void deleteWarehouse(@PathVariable String warehouseId){
        warehouseRepository.deleteById(warehouseId);
    }
}
