package id.co.indivara.jdt12.miniprojecttest2.controller;

import id.co.indivara.jdt12.miniprojecttest2.entity.Store;
import id.co.indivara.jdt12.miniprojecttest2.entity.Warehouse;
import id.co.indivara.jdt12.miniprojecttest2.repo.WarehouseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;

@RestController
public class WarehouseController {

    @Autowired
    WarehouseRepository warehouseRepository;

    @PostMapping("/create/warehouse")
    public Warehouse createWarehouse(@RequestBody Warehouse warehouse){
        warehouse.setWarehouseId("wrh"+(warehouseRepository.count()+1));
        warehouse.setJoinDate(Timestamp.valueOf(LocalDateTime.now()));
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

    @PatchMapping("/update/warehouse/{warehouseId}")
    public Store updateStore(@PathVariable String warehouseId, @RequestBody Warehouse warehouse){
        Store store1 = new Store();
        warehouseRepository.findById(warehouseId);
        store1.setStoreName(warehouse.getWarehouseName());
        store1.setStoreLocation(warehouse.getWarehouseLocation());

        if (store1.getStoreName().isEmpty()){
            store1.setStoreName(warehouse.getWarehouseName());
        }

        if (store1.getStoreLocation().isEmpty()){
            store1.setStoreLocation(warehouse.getWarehouseLocation());
        }
        return store1;
    }

    @DeleteMapping("/delete/warehouse/{warehouseId}")
    public void deleteWarehouse(@PathVariable String warehouseId){
        warehouseRepository.deleteById(warehouseId);
    }
}
