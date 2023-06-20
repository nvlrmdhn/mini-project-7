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
public class TransferSupplyController {
    @Autowired
    TransactionRepository transactionRepository;
    @Autowired
    TransferSupplyRepository transferSupplyRepository;
    @Autowired
    WarehouseInventoryRepository warehouseInventoryRepository;

    @PostMapping("/insert/{warehouseId}/{merchandiseId}")
    public ResponseEntity<TransferSupply> insertSupply(@PathVariable Warehouse warehouseId,@PathVariable Merchandise merchandiseId,  @RequestBody TransferSupply transferSupply){
            WarehouseInventory warehouseInventory = warehouseInventoryRepository.findByMerchandiseIdAndWarehouseId(merchandiseId,warehouseId);
            transferSupply.setTrxSupplyId("T" + (transferSupplyRepository.count()+1));
            transferSupply.setMerchandise(merchandiseId);
            transferSupply.setWarehouse(warehouseId);
            transferSupply.setTimestamp(Timestamp.valueOf(LocalDateTime.now()));

            Transaction transaction = new Transaction();
            transaction.setTransactionId("trx"+(transactionRepository.count()+1));
            transaction.setType("trx_supply");
            transaction.setTimestamp(Timestamp.valueOf(LocalDateTime.now()));
            transactionRepository.save(transaction);
            WarehouseInventory warehouseInventory1 = new WarehouseInventory();

            try {
                warehouseInventory.setStock(warehouseInventory.getStock()+transferSupply.getStock());
                warehouseInventoryRepository.save(warehouseInventory);
            }catch (Exception ex){
                warehouseInventory1.setMerchandiseId(merchandiseId);
                warehouseInventory1.setWarehouseId(warehouseId);
                warehouseInventory1.setStock(transferSupply.getStock());
                warehouseInventoryRepository.save(warehouseInventory1);
            }
            transferSupplyRepository.save(transferSupply);
            return new ResponseEntity<>(transferSupply,HttpStatus.OK);
    }
}
