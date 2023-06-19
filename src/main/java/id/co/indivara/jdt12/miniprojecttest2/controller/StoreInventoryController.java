package id.co.indivara.jdt12.miniprojecttest2.controller;

import id.co.indivara.jdt12.miniprojecttest2.entity.StoreInventory;
import id.co.indivara.jdt12.miniprojecttest2.repo.StoreInventoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class StoreInventoryController {

    @Autowired
    StoreInventoryRepository storeInventoryRepository;

    @GetMapping("/all/store")
    public List<StoreInventory> viewStoreInventory(){
        return storeInventoryRepository.findAll();
    }
}
