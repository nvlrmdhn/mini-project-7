package id.co.indivara.jdt12.miniprojecttest2.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "transaction")
public class Transaction {

    @Id
    @Column(name = "transaction_id",nullable = false)
    private String transactionId;

    @Column(name = "type",nullable = false)
    private String type;

    @Column(name = "timestamp",nullable = false)
    private Timestamp timestamp;
}
