package id.co.indivara.jdt12.miniprojecttest2.repo;

import id.co.indivara.jdt12.miniprojecttest2.entity.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TransactionRepository extends JpaRepository<Transaction,String> {
}
