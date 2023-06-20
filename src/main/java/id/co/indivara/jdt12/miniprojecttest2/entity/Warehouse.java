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
@Table(name = "warehouse")
public class Warehouse {

    @Id
    @Column(name = "warehouse_id",nullable = false)
    private String warehouseId;

    @Column(name = "warehouse_name",nullable = false)
    private String warehouseName;

    @Column(name = "warehouse_location",nullable = false)
    private String warehouseLocation;

    @Column(name = "join_date",nullable = false)
    private Timestamp joindate;
}
