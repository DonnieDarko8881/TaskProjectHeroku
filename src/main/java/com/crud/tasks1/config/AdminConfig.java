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

    @Value("company: ${info.company.name}" + " mail: ${info.company.email}" + " tel: ${info.company.phone}")
    private String companyDetails;
}
