package com.auth.Signing.BillingPackage.portaldb.entity;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "invoice")
@Data
public class Invoice {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String companyname;

	private int acc_no;
	private double fixed_bb_cost;
	private double one_time_cost;
	private double total_sms_usage;
	private double total_call_usage;
	private double total_data_usage;
	private double total_bb_usage;
	private double total_cost;
	private String month;
	private int year;

	private String statusofinvoice;

	}

