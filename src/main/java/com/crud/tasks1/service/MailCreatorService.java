package com.crud.tasks1.service;

import com.crud.tasks1.config.AdminConfig;
import com.crud.tasks1.domain.Task;
import com.crud.tasks1.repository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import java.util.ArrayList;
import java.util.List;

@Service
public class MailCreatorService {

    private TaskRepository taskRepository;
    private AdminConfig adminConfig;

    @Qualifier("templateEngine")
    private TemplateEngine templateEngine;

    @Autowired
    public MailCreatorService(TaskRepository taskRepository, AdminConfig adminConfig, TemplateEngine templateEngine) {
        this.taskRepository = taskRepository;
        this.adminConfig = adminConfig;
        this.templateEngine = templateEngine;
    }

    public String buildTrelloCardEmail(String message) {

        List<String> functionality = new ArrayList<>();
        functionality.add("You can menage your Task");
        functionality.add("Provides connection with Trello Account");
        functionality.add("Application allows sending tasks to Trello");

        Context context = new Context();
        context.setVariable("message", message);
        context.setVariable("tasks_url", "https://donniedarko8881.github.io");
        context.setVariable("button", "Visit website");
        context.setVariable("goodbye_message", "Have a nice day! ");
        context.setVariable("show_button", true);
        context.setVariable("is_friend", false);
        context.setVariable("admin_config", adminConfig);
        context.setVariable("application_functionality", functionality);
        return templateEngine.process("mail/created-trello-card-mail", context);
    }

    public String buildScheduledEmail(String message) {
        List<Task> tasks = taskRepository.findAll();

        Context context = new Context();
        context.setVariable("message", message);
        context.setVariable("tasks_url", "https://donniedarko8881.github.io");
        context.setVariable("button", "Visit website");
        context.setVariable("admin_config", adminConfig);
        context.setVariable("goodbye_message", "Tomorrow, we will spam you again");
        context.setVariable("show_button", true);
        context.setVariable("is_friend", false);
        context.setVariable("task_list", tasks);
        return templateEngine.process("mail/created-scheduled-task-mail", context);
    }
}
