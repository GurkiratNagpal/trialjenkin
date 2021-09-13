package com.auth.Signing.BillingPackage.portaldb.repository;

import com.auth.Signing.BillingPackage.portaldb.entity.Invoice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface InvoiceRepository extends JpaRepository<Invoice, Long> {


	@Query(value = "Select  companyname, total_cost from invoice where statusofinvoice = 'unpaid' and year='2021' order by total_cost desc", nativeQuery = true)
	List<Object[]> findUnpaid();

	@Query(value = "Select  companyname, sum(total_cost) from invoice group by acc_no order by sum(total_cost) desc limit 5", nativeQuery = true)
	List<Object[]> findTopBilled();

	@Query(value = "select * from invoice where companyname =:n", nativeQuery = true)
	List<Invoice> findByCompanyname(@Param("n") String s);


	@Query(value = "Select  id, companyname, total_cost from invoice where statusofinvoice = 'unpaid' and year<2021 order by total_cost desc", nativeQuery = true)
	List<Object[]> findDelayedInvoice();

}