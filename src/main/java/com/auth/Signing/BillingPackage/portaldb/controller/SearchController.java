package com.auth.Signing.BillingPackage.portaldb.controller;


import com.auth.Signing.BillingPackage.portaldb.entity.Company;
import com.auth.Signing.BillingPackage.portaldb.entity.Invoice;
import com.auth.Signing.BillingPackage.portaldb.repository.CompanyRepository;
import com.auth.Signing.BillingPackage.portaldb.repository.InvoiceRepository;
import com.auth.Signing.BillingPackage.portaldb.service.InvoiceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import javax.mail.MessagingException;
import java.io.UnsupportedEncodingException;
import java.security.Principal;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@RestController
@CrossOrigin("*")
public class SearchController {

    @Autowired
    InvoiceRepository invoiceRepository;
    @Autowired
    CompanyRepository companyRepository;


    @GetMapping("/portal/search/inv/{invno}")
    public Invoice searchInvoice(@PathVariable("invno") String invoicenumber) {
        System.err.println(invoicenumber);
        Long invoice_no = Long.parseLong((invoicenumber));
        Optional<Invoice> foundInv = invoiceRepository.findById(invoice_no);
        foundInv.orElseThrow(() -> new NoSuchElementException());
        System.err.println("Returning"+ foundInv.get());
        return foundInv.get();
    }

    @GetMapping("/portal/search/company/{cname}")
    public List<Company> viewCompanies(@PathVariable("cname") String c_name) {
        List<Company> companyList = companyRepository.findByCompanynameContaining(c_name);
        if (companyList.isEmpty()) {
            throw new NoSuchElementException("No value present");
        }
        System.err.println("Returning"+ companyList);
        return  companyList;
    }

    @GetMapping("/portal/search/compinv/{comname}")
    public List<Invoice> viewOneCompany(@PathVariable("comname") String compname) {
        List<Invoice> invoiceList = invoiceRepository.findByCompanyname(compname);
        System.err.println("Yeh dekho company" + compname);
        invoiceList.forEach(System.err::println);
        return invoiceList;
    }

    @Autowired
    InvoiceService invoiceService;
    @GetMapping("/portal/inv/mail/{id}")
    public void sendmailinv(@PathVariable("id") long invoice_id, Principal principal) throws MessagingException, UnsupportedEncodingException {
        invoiceService.sendinvtocompany(invoice_id, principal);
    }




}
