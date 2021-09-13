package com.auth.Signing.BillingPackage.portaldb.repository;

import com.auth.Signing.BillingPackage.portaldb.entity.Company;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CompanyRepository extends JpaRepository<Company, Long> {

    Company findByCompanyname(String s);




    List<Company> findByCompanynameContaining(String s);
}
