package com.auth.Signing.BillingPackage.portaldb.repository;

import com.auth.Signing.BillingPackage.portaldb.entity.Mobile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MobileRepository extends JpaRepository<Mobile, Long> {

	public List<Mobile> findByCompanyid(int c);

}
