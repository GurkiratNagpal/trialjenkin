package com.auth.Signing.BillingPackage.portaldb.entity;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "company")
public @Data
class Company {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "company_id", nullable = false)
	private int company_id;

	@Column(name = "company_name", nullable = false)
	private String companyname;

	@Column(name = "nokia", nullable = false)
	private int nokia;

	@Column(name = "oneplus", nullable = false)
	private int oneplus;

	@Column(name = "apple", nullable = false)
	private int apple;

	@Column(name = "samsung", nullable = false)
	private int samsung;

	@Column(name = "fixed", nullable = false)
	private int fixed;

	@Column(name = "broadband", nullable = false)
	private int broadband;

}
