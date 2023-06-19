package id.co.indivara.jdt12.miniprojecttest2.controller;

import id.co.indivara.jdt12.miniprojecttest2.entity.Store;
import id.co.indivara.jdt12.miniprojecttest2.repo.StoreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class StoreController {

    @Autowired
    StoreRepository storeRepository;

    @PostMapping("/create/store")
    public Store createStore(@RequestBody Store store){
        Store str = new Store();
        str.setStoreId("str"+(storeRepository.count()+1));
        str.setStoreName(store.getStoreName());
        str.setStoreLocation(store.getStoreLocation());
        return storeRepository.save(str);
    }

    @GetMapping("/find/store/{storeId}")
    public Store findById(@PathVariable String storeId){
        return storeRepository.findById(storeId).get();
    }

    @GetMapping("/find/store/all")
    public List<Store> findAll(){
        return storeRepository.findAll();
    }

    @DeleteMapping("/delete/{storeId}")
    public void deleteStore(@PathVariable String storeId){
        storeRepository.deleteById(storeId);
    }


}
