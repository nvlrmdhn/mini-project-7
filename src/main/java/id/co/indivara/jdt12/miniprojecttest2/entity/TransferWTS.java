package id.co.indivara.jdt12.miniprojecttest2.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.print.attribute.standard.MediaSize;
import java.awt.*;
import java.sql.Timestamp;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "transfer_wts")
public class TransferWTS {

    @Id
    @Column(name = "transfer_wts_id",nullable = false)
    private String transferWTSId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "merchandise_id",nullable = false)
    @JsonIgnore
    private Merchandise merchandiseId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "source",nullable = false)
    @JsonIgnore
    private Warehouse source;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "destination",nullable = false)
    @JsonIgnore
    private Store destination;

    @Column(name = "stock",nullable = false)
    private Integer stock;

    @Column(name = "timestamp",nullable = false)
    private Timestamp timestamp;
}
