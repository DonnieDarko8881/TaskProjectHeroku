package com.crud.tasks1.config;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
@Getter
public class AdminConfig {
    @Value("${admin.mail}")
    private String adminMail;

    @Value("${admin.name}")
    private String adminName;

    @Value("${info.company.email}")
    private String companyEmail;

    @Value("${info.company.phone}")
    private String companyPhone;

    @Value("${info.company.name}")
    private String companyName;

    private String companyDetails;

    public String getCompanyDetails() {
        return "company: " +getCompanyName()+ " mail: " +getCompanyEmail() + " tel: "+ getCompanyPhone();
    }
}
