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

@RestController
public class TransferSupplyController {

    @Autowired
    MerchandiseRepository merchandiseRepository;
    @Autowired
    TransactionRepository transactionRepository;
    @Autowired
    TransferSupplyRepository transferSupplyRepository;
    @Autowired
    WarehouseRepository warehouseRepository;
    @Autowired
    WarehouseInventoryRepository warehouseInventoryRepository;

    @PostMapping("/insert/{warehouseId}/{merchandiseId}")
    public ResponseEntity<TransferSupply> insertSupply(@PathVariable Warehouse warehouseId,@PathVariable Merchandise merchandiseId,  @RequestBody TransferSupply transferSupply){
        TransferSupply transferSupply1 =warehouseRepository.findById(warehouseId.getWarehouseId()).map(warehouse -> {
            Merchandise merchandise = merchandiseRepository.findById(merchandiseId.getMerchandiseId()).get();
            transferSupply.setTrxSupplyId("T" + (transferSupplyRepository.count()+1));
            transferSupply.setMerchandise(merchandise);
            transferSupply.setWarehouse(warehouseId);
            Transaction transaction = new Transaction();
            transaction.setTransactionId("T" + (transferSupplyRepository.count()+1));
            transaction.setType("Supply");
            WarehouseInventory warehouseInventory = new WarehouseInventory();
            try {
                WarehouseInventory warehouseInventory1 =warehouseInventoryRepository.findByMerchandiseIdAndWarehouseId(merchandiseId,warehouseId);
                warehouseInventory1.setMerchandiseId(merchandiseId);
                warehouseInventory1.setWarehouseId(warehouseId);
                warehouseInventory1.setStock(warehouseInventory1.getStock()+transferSupply.getStock());
                warehouseInventoryRepository.save(warehouseInventory1);
            }catch (Exception ex){
                warehouseInventory.setMerchandiseId(merchandiseId);
                warehouseInventory.setWarehouseId(warehouseId);
                warehouseInventory.setStock(transferSupply.getStock());
                warehouseInventoryRepository.save(warehouseInventory);
            }
            return transferSupplyRepository.save(transferSupply);
        }).orElseThrow(()-> new RuntimeException("uwogh"));
        return new ResponseEntity<>(transferSupply1, HttpStatus.OK);
    }
}
