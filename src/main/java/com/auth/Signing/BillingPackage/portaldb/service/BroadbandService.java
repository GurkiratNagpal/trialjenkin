package com.auth.Signing.BillingPackage.portaldb.service;

import com.auth.Signing.BillingPackage.portaldb.entity.Broadband;
import com.auth.Signing.BillingPackage.portaldb.repository.BroadbandRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class BroadbandService {
	@Autowired
	private BroadbandRepository repo;

	public List<Broadband> listAll() {
		return repo.findAll();
	}

	public void save(Broadband broadband) {
		repo.save(broadband);
	}

	public Broadband get(long id) {
		Optional<Broadband> br= Optional.of(repo.findById(id).get());
		return br.get();
	}

	public void delete(long id) {
		repo.deleteById(id);
	}

}
