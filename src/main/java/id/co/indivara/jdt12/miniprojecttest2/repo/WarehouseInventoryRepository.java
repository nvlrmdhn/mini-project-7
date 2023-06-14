package id.co.indivara.jdt12.miniprojecttest2.repo;

import id.co.indivara.jdt12.miniprojecttest2.entity.Merchandise;
import id.co.indivara.jdt12.miniprojecttest2.entity.Warehouse;
import id.co.indivara.jdt12.miniprojecttest2.entity.WarehouseInventory;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WarehouseInventoryRepository extends JpaRepository<WarehouseInventory,String> {

    WarehouseInventory findByMerchandiseIdAndWarehouseId(Merchandise merchandise, Warehouse warehouse);

}
