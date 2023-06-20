package id.co.indivara.jdt12.miniprojecttest2.controller;

import id.co.indivara.jdt12.miniprojecttest2.entity.Store;
import id.co.indivara.jdt12.miniprojecttest2.repo.StoreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.sql.Timestamp;
import java.time.LocalDateTime;
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
        str.setJoinDate(Timestamp.valueOf(LocalDateTime.now()));
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

    @PatchMapping("/update/store/{storeId}")
    public Store updateStore(@PathVariable String storeId,@RequestBody Store store){
        Store store1 = new Store();
        storeRepository.findById(storeId);
        store1.setStoreName(store.getStoreName());
        store1.setStoreLocation(store.getStoreLocation());

        if (store1.getStoreName().isEmpty()){
            store1.setStoreName(store.getStoreName());
        }

        if (store1.getStoreLocation().isEmpty()){
            store1.setStoreLocation(store.getStoreLocation());
        }
        return store1;
    }

    @DeleteMapping("/delete/store/{storeId}")
    public void deleteStore(@PathVariable String storeId){
        storeRepository.deleteById(storeId);
    }
}
