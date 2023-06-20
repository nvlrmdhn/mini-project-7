package id.co.indivara.jdt12.miniprojecttest2.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.sql.Timestamp;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "store")
public class Store {

    @Id
    @Column(name = "store_id",nullable = false)
    private String storeId;

    @Column(name = "store_name",nullable = false)
    private String storeName;

    @Column(name = "store_location",nullable = false)
    private String storeLocation;

    @Column(name = "join_date",nullable = false)
    private Timestamp joindate;
}
