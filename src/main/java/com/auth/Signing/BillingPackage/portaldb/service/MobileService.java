package com.auth.Signing.BillingPackage.portaldb.service;

import com.auth.Signing.BillingPackage.portaldb.entity.Mobile;
import com.auth.Signing.BillingPackage.portaldb.repository.MobileRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class MobileService {

	@Autowired
	private MobileRepository repo;

	public List<Mobile> listAll() {
		return repo.findAll();
	}

	public void save(Mobile product) {
		repo.save(product);
	}

	public Mobile get(long id) {
		return repo.findById(id).get();
	}

	public void delete(long id) {
		repo.deleteById(id);
	}


}
