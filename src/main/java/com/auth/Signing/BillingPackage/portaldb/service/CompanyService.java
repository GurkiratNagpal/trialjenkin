package com.auth.Signing.BillingPackage.portaldb.service;

import com.auth.Signing.BillingPackage.portaldb.entity.Company;
import com.auth.Signing.BillingPackage.portaldb.repository.CompanyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class CompanyService {
	@Autowired
	private CompanyRepository repo;

	public List<Company> listAll() {
		return repo.findAll();
	}

	public void save(Company company) {
		repo.save(company);
	}

	public Company get(long id) {
		return repo.findById(id).get();
	}

	public void delete(long id) {
		repo.deleteById(id);
	}
}
