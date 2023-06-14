package id.co.indivara.jdt12.miniprojecttest2.entity;

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
@Table(name = "transfer_supply")
public class TransferSupply {

    @Id
    @Column(name = "trx_supply_id")
    private String trxSupplyId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "merchandise_id")
    private Merchandise merchandise;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "destination")
    private Warehouse warehouse;

    @Column(name = "stock")
    private Integer stock;


}
