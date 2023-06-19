package id.co.indivara.jdt12.miniprojecttest2.repo;

import id.co.indivara.jdt12.miniprojecttest2.entity.Merchandise;
import id.co.indivara.jdt12.miniprojecttest2.entity.Store;
import id.co.indivara.jdt12.miniprojecttest2.entity.StoreInventory;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StoreInventoryRepository extends JpaRepository<StoreInventory,String> {

    StoreInventory findByMerchandiseIdAndStoreId(Merchandise merchandise, Store store);

}
