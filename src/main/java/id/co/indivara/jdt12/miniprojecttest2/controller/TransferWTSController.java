package id.co.indivara.jdt12.miniprojecttest2.controller;

import id.co.indivara.jdt12.miniprojecttest2.entity.*;
import id.co.indivara.jdt12.miniprojecttest2.repo.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.sql.Timestamp;
import java.time.LocalDateTime;

@RestController
public class TransferWTSController{

    @Autowired
    StoreInventoryRepository storeInventoryRepository;
    @Autowired
    WarehouseInventoryRepository warehouseInventoryRepository;
    @Autowired
    TransferWTSRepository transferWTSRepository;
    @Autowired
    TransactionRepository transactionRepository;

    @PostMapping("/transferwts/{warehouseId}/{merchandiseId}/{storeId}")
    public ResponseEntity<TransferWTS> transferWTS(@PathVariable Warehouse warehouseId, @PathVariable Merchandise merchandiseId, @PathVariable Store storeId, @RequestBody TransferWTS transferWTS){
        WarehouseInventory warehouseSource = warehouseInventoryRepository.findByMerchandiseIdAndWarehouseId(merchandiseId,warehouseId);
        StoreInventory storeDestination = storeInventoryRepository.findByMerchandiseIdAndStoreId(merchandiseId,storeId);
        StoreInventory storeInventory = new StoreInventory();

        transferWTS.setTransferWTSId("T" + (transferWTSRepository.count()+1));
        transferWTS.setMerchandiseId(merchandiseId);
        transferWTS.setSource(warehouseId);
        transferWTS.setDestination(storeId);
        transferWTS.setTimestamp(Timestamp.valueOf(LocalDateTime.now()));
        transferWTSRepository.save(transferWTS);

        Transaction transaction = new Transaction();
        transaction.setTransactionId("trx"+(transactionRepository.count()+1));
        transaction.setType("trx_wts");
        transaction.setTimestamp(Timestamp.valueOf(LocalDateTime.now()));
        transactionRepository.save(transaction);

        if (warehouseSource.getStock()>=transferWTS.getStock()){
            warehouseSource.setStock(warehouseSource.getStock()- transferWTS.getStock());
        }
        try {
            storeDestination.setStock(storeDestination.getStock()+transferWTS.getStock());
            storeInventoryRepository.save(storeDestination);
        }catch (Exception ex){
            storeInventory.setStoreId(storeId);
            storeInventory.setMerchandiseId(merchandiseId);
            storeInventory.setStock(transferWTS.getStock());
            storeInventoryRepository.save(storeInventory);
        }
        return new ResponseEntity<>(transferWTS, HttpStatus.OK);
    }
}
