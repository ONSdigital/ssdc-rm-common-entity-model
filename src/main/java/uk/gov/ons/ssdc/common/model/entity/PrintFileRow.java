package uk.gov.ons.ssdc.common.model.entity;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.UUID;


@Data
@Entity
public class PrintFileRow {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private long id;

  @Column
  private String row;

  @Column
  private UUID batchId;

  @Column(nullable = false)
  private int batchQuantity;

  @Column(nullable = false)
  private String printSupplier;

  @Column(nullable = false)
  private String packCode;
}
