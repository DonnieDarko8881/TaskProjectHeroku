package com.crud.tasks1.service;

import com.crud.tasks1.config.AdminConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

@Service
public class MailCreatorService {

    private AdminConfig adminConfig;

    @Qualifier("templateEngine")
    private TemplateEngine templateEngine;

    @Autowired
    public MailCreatorService(AdminConfig adminConfig, TemplateEngine templateEngine) {
        this.adminConfig = adminConfig;
        this.templateEngine = templateEngine;
    }

    public String buildTrelloCardEmail(String message){
        Context context = new Context();
        context.setVariable("message", message);
        context.setVariable("tasks_url","http://localhost:8888/front");
        context.setVariable("button","Visit website");
        context.setVariable("admin_name", adminConfig.getAdminName());
        context.setVariable("company_details", adminConfig.getCompanyDetails());
        context.setVariable("goodbye_message", "Have a nice day! ");
        return templateEngine.process("mail/created-trello-card-mail",context);
    }
}
