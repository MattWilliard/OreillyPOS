package com.oreillys.invoice.pos.database.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name="invoice")
@Data
public class Invoice {

    @Column(name="customer_id")
    private Long customerId;

    @Id
    @Column(name="invoice_id")
    private int invoiceId;

    @Column(name="invoice_data")
    private String invoiceJson;

}
