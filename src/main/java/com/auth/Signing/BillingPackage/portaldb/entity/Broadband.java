package com.auth.Signing.BillingPackage.portaldb.entity;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "broadband")
public @Data
class Broadband {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "company_id", nullable = false)
	private int companyid;

	@Column(name = "data_consumption", nullable = false)
	private int data_consumption;

	@Column(name = "month", nullable = false)
	private String month;

	@Column(name = "year", nullable = false)
	private int year;

	@Column(name = "status", nullable = false)
	private String status;


}
