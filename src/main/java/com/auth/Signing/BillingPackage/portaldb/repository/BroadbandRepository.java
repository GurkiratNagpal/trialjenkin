package com.auth.Signing.BillingPackage.portaldb.repository;

import com.auth.Signing.BillingPackage.portaldb.entity.Broadband;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BroadbandRepository extends JpaRepository<Broadband, Long> {

    List<Broadband> findByCompanyid(int s);

}
