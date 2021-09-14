package uk.gov.ons.ssdc.common.model.entity;

import java.util.UUID;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import lombok.Data;

@Data
@Entity
public class PrintFileRow {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private long id;

  @Column(nullable = false)
  private String row;

  @Column(nullable = false)
  private UUID batchId;

  @Column(nullable = false)
  private int batchQuantity;

  @Column(nullable = false)
  private String printSupplier;

  @Column(nullable = false)
  private String packCode;
}
