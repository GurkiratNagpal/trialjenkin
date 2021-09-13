package com.auth.Signing.BillingPackage.portaldb.controller;

import com.auth.Signing.BillingPackage.portaldb.entity.Company;
import com.auth.Signing.BillingPackage.portaldb.entity.Invoice;
import com.auth.Signing.BillingPackage.portaldb.repository.CompanyRepository;
import com.auth.Signing.BillingPackage.portaldb.repository.InvoiceRepository;
import com.auth.Signing.BillingPackage.portaldb.service.InvoiceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@CrossOrigin("*")
public class MainController {

    @Autowired
    private InvoiceService service;

    @Autowired
    private CompanyRepository companyRepository;

    @GetMapping("/portal/companiesAll")
    public List<Company> allcompanies(){
        System.err.println("Showing all companies" + companyRepository.findAll());
        return companyRepository.findAll();
    }

    @Autowired
    private InvoiceRepository invoiceRepository;

    @GetMapping("/portal/delayed")
    public List<Object[]> getdelayed(){
        System.err.println("Getting delayed invoices");
        return invoiceRepository.findDelayedInvoice();
    }

    @GetMapping("/portal/topbilled")
    public List<Object[]> topbilled() {
        service.insertdata();
        System.err.println("Fetching top billled");
        List<Object[]> companyList = service.getTopBilled();
        return companyList;
    }

    @GetMapping("/portal/unpaid")
    public List<Object[]> unpaid() {

        List<Object[]> unpaidCompanies = service.getUnpaid();
        return unpaidCompanies;
    }


}
