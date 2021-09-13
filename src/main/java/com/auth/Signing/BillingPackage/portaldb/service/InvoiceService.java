package com.auth.Signing.BillingPackage.portaldb.service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.UnsupportedEncodingException;
import com.auth.Signing.BillingPackage.portaldb.entity.Broadband;
import com.auth.Signing.BillingPackage.portaldb.entity.Company;
import com.auth.Signing.BillingPackage.portaldb.entity.Invoice;
import com.auth.Signing.BillingPackage.portaldb.entity.Mobile;
import com.auth.Signing.BillingPackage.portaldb.repository.BroadbandRepository;
import com.auth.Signing.BillingPackage.portaldb.repository.CompanyRepository;
import com.auth.Signing.BillingPackage.portaldb.repository.InvoiceRepository;
import com.auth.Signing.BillingPackage.portaldb.repository.MobileRepository;
import com.auth.Signing.Project_Login.Repo.UserRepository;
import com.auth.Signing.Project_Login.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.text.DateFormatSymbols;
import javax.mail.internet.MimeMessage;
import java.security.Principal;
import java.util.List;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;

@Service
@Transactional
public class InvoiceService {
    @Autowired
    private InvoiceRepository invoiceRepository;

    @Autowired
    private CompanyRepository companyRepository;


    @Autowired
    private BroadbandRepository broadbandRepository;

    @Autowired
    private MobileRepository mobileRepository;

    public void insertdata() {

        int trialcount = invoiceRepository.findAll().size();
        if (trialcount==0) { //any number >1
            List<Company> companyList = companyRepository.findAll();


            for (Company c : companyList) {

                List<Mobile> mobileList = mobileRepository.findByCompanyid(c.getCompany_id());
                List<Broadband> broadbandList = broadbandRepository.findByCompanyid(c.getCompany_id());

                for (int z = 0; z < mobileList.size(); z++) {
                    int total = 0;
                    Mobile m = mobileList.get(z);
                    Broadband b = broadbandList.get(z);
                    Invoice inv = new Invoice();

                    if (z == 0) {
                        inv.setOne_time_cost(c.getNokia() * 50
                                + c.getApple() * 300
                                + c.getSamsung() * 100
                                + c.getOneplus() * 75
                                + c.getFixed() * 50
                                + c.getBroadband() * 50
                        );
                    } else {
                        inv.setOne_time_cost(0);
                    }
                    inv.setMonth(m.getMonth());
                    inv.setYear(m.getYear());
                    inv.setTotal_bb_usage((b.getData_consumption()));
                    inv.setFixed_bb_cost(105 * (c.getBroadband()) + 50 * (c.getFixed()));
                    //inv.setTotal_bb_cost(105 * (c.getBroadband()) + 50 * (c.getFixed()));
                    inv.setTotal_call_usage(m.getCall_duration());
                    //inv.setTotal_call_cost(m.getCall_duration() * 0.2);
                    inv.setTotal_sms_usage(m.getSms());
                    //inv.setTotal_sms_cost(m.getSms() * 0.05);
                    inv.setCompanyname(c.getCompanyname());
                    //inv.setCompany_id(c.getCompany_id());
                    inv.setAcc_no(c.getCompany_id());
                    inv.setStatusofinvoice(m.getStatus());
                    //inv.setTotal_data_cost(m.getData_consumption() * 0.1);
                    inv.setTotal_data_usage(m.getData_consumption());
                    inv.setTotal_cost(
                            inv.getOne_time_cost()
                            + inv.getTotal_data_usage() *0.1
                            + inv.getTotal_call_usage()* 0.2
                            +inv.getTotal_sms_usage() * 0.05
                            +inv.getTotal_bb_usage() *0.01
                            +inv.getFixed_bb_cost()
                    );
                    invoiceRepository.save(inv);
                }
            }
        }


    }

	public List<Object[]> getTopBilled() {

        return  invoiceRepository.findTopBilled();


	}

	public List<Object[]> getUnpaid() {
        return   invoiceRepository.findUnpaid();
	}

    @Autowired
    private JavaMailSender mailSender;
	@Autowired
    private UserRepository userRepository;
    public void sendinvtocompany(long invoice_id, Principal principal) throws MessagingException, UnsupportedEncodingException {
        Invoice tobesent = invoiceRepository.findById(invoice_id).get();
        User employee = userRepository.findByEmailid(principal.getName()).get();
        String toAddress = "companyhr000@gmail.com";
        String fromAddress = "tbillerportal@gmail.com";
        String senderName = "Telstra Billfinity";
        String subject = "Invoice Details";
        String content = "Dear [[name]] HR,<br>"
                + "<br> This is an auto-generated mail. Please do not reply! "
                + "This Mail has been generated by  "+ employee.getFirstname() +" from Telstra Billfinity  "
                + "[[statusdetails]]"
                +"<br><br> Invoice details attached below: "
                +"<br>Customer: " + tobesent.getCompanyname()
                +"<br>Generated on : " +  new DateFormatSymbols().getMonths()[Integer.valueOf(tobesent.getMonth())-1] + "-"+ tobesent.getYear()
                +" <br><br><br>Mobile Bill Details:  "
                +"<table>\n" +
                "    <tr>\n" +
                "        <th>OTC</th>\n" +
                "        <th>Call Usage</th>\n" +
                "        <th>Call Cost</th>\n" +
                "        <th>SMS Usage</th>\n" +
                "        <th>SMS Cost</th>\n" +
                "        <th>Data Usage</th>\n" +
                "        <th>Data Cost</th>\n" +
                "    </tr>\n" +
                "    <tr>\n" +
                "<td>" + tobesent.getOne_time_cost() +"</td>" +
                "<td>" + tobesent.getTotal_call_usage() +"</td>" +
                "<td>" + Math.round(tobesent.getTotal_call_usage() * 0.2) +"</td>" +
                "<td>" + tobesent.getTotal_sms_usage() +"</td>" +
                "<td>" + Math.round(tobesent.getTotal_sms_usage() *0.05)+"</td>" +
                "<td>" + tobesent.getTotal_data_usage() +"</td>" +
                "<td>" + Math.round(tobesent.getTotal_data_usage()*0.1)+"</td>" +
                "    </tr>\n" +
                "</table>"
                +"<br><br><br> Broadband Bill details"
                +"<table>\n" +
                "    <tr>\n" +
                "\n" +
                "        <th>Broadband Usage</th>\n" +
                "        <th>Broadband Fixed Charges</th>\n" +
                "\n" +
                "    </tr>\n" +
                "    <tr>\n" +
                "        <td>"+Math.round(tobesent.getTotal_bb_usage())+ "</td>\n" +
                "        <td>"+Math.round(tobesent.getFixed_bb_cost())+ "</td>\n" +
                "    </tr>\n" +
                "</table>"
                +"<br> <u>Total Payment Due: $"+ Math.round(tobesent.getTotal_cost()) + " </u>"
                +"<br><br><br><br> "
                + "Thank you, Regards,  <br>"
                + "Telstra Billfinity.";

        MimeMessage message = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message);

        helper.setFrom(fromAddress, senderName);
        helper.setTo(toAddress);
        helper.setSubject(subject);
        String status="";
        if(tobesent.getStatusofinvoice().equalsIgnoreCase("Paid")){

            status +="as confirmation of your payment for the invoice:"+ invoice_id;

        }
        else{
            status +="as a reminder of payment due for invoice:"+invoice_id;
        }
        content = content.replace("[[name]]", tobesent.getCompanyname().toUpperCase());
        content = content.replace("[[statusdetails]]", status);
        helper.setText(content, true);
        mailSender.send(message);

    }
}
