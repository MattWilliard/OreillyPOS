package com.oreillys.oreillysinvoice.database.repository;

import com.oreillys.oreillysinvoice.database.model.Invoice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface InvoiceRepository extends JpaRepository<Invoice, Long> {

    @Query(value = "SELECT * FROM invoice WHERE customer_id = :custId", nativeQuery = true)
    public List<Invoice> getInvocesByCustomerId(@Param("custId") Long custId);

}
