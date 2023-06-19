package id.co.indivara.jdt12.miniprojecttest2.repo;

import id.co.indivara.jdt12.miniprojecttest2.entity.Store;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StoreRepository extends JpaRepository<Store,String> {
}
