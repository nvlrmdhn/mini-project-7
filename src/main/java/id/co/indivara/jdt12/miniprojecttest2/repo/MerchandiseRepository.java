package id.co.indivara.jdt12.miniprojecttest2.repo;

import id.co.indivara.jdt12.miniprojecttest2.entity.Merchandise;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MerchandiseRepository extends JpaRepository<Merchandise, String> {
}
