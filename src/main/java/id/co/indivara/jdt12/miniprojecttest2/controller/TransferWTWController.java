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
public class TransferWTWController {
    @Autowired
    WarehouseInventoryRepository warehouseInventoryRepository;
    @Autowired
    TransferWTWRepository transferWTWRepository;
    @Autowired
    TransactionRepository transactionRepository;

    @PostMapping("/transferwtw/{warehouseIdSource}/{merchandiseId}/{warehouseIdDestiny}")
    public ResponseEntity<TransferWTW> transferWTW(@PathVariable Warehouse warehouseIdSource, @PathVariable Merchandise merchandiseId,@PathVariable Warehouse warehouseIdDestiny, @RequestBody TransferWTW transferwtw) {
        WarehouseInventory warehouseSource = warehouseInventoryRepository.findByMerchandiseIdAndWarehouseId(merchandiseId,warehouseIdSource);
        WarehouseInventory warehouseDestination = warehouseInventoryRepository.findByMerchandiseIdAndWarehouseId(merchandiseId,warehouseIdDestiny);
        WarehouseInventory warehouseInventory = new WarehouseInventory();

        transferwtw.setTransferWTWId("T" + (transferWTWRepository.count()+1));
        transferwtw.setWarehouseIdDestination(warehouseIdDestiny);
        transferwtw.setWarehouseIdSource(warehouseIdSource);
        transferwtw.setMerchandiseId(merchandiseId);
        transferwtw.setStock(transferwtw.getStock());
        transferwtw.setTimestamp(Timestamp.valueOf(LocalDateTime.now()));
        transferWTWRepository.save(transferwtw);

        Transaction transaction = new Transaction();
        transaction.setTransactionId("trx"+(transactionRepository.count()+1));
        transaction.setType("trx_wtw");
        transaction.setTimestamp(Timestamp.valueOf(LocalDateTime.now()));
        transactionRepository.save(transaction);

        if (warehouseSource.getStock()>= transferwtw.getStock()){
            warehouseSource.setStock(warehouseSource.getStock()- transferwtw.getStock());
        }
        try {
            warehouseDestination.setStock(warehouseDestination.getStock()+ transferwtw.getStock());
            warehouseInventoryRepository.save(warehouseDestination);
        }catch (Exception ex){
            warehouseInventory.setWarehouseId(warehouseIdDestiny);
            warehouseInventory.setMerchandiseId(merchandiseId);
            warehouseInventory.setStock(transferwtw.getStock());
            warehouseInventoryRepository.save(warehouseInventory);
        }
        return new ResponseEntity<>(transferwtw,HttpStatus.OK);
    }
}
