package id.co.indivara.jdt12.miniprojecttest2.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "transfer_wtw")
public class TransferWTW {

    @Id
    @Column(name = "transfer_wtw_id")
    private String transferWTWId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "merchandise_id")
    @JsonIgnore
    private Merchandise merchandiseId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "source")
    @JsonIgnore
    private Warehouse warehouseIdSource;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "destination")
    @JsonIgnore
    private Warehouse warehouseIdDestination;

    @Column(name = "stock")
    private Integer stock;
}
