package com.crud.tasks1.scheduler;

import com.crud.tasks1.config.AdminConfig;
import com.crud.tasks1.domain.Mail;
import com.crud.tasks1.repository.TaskRepository;
import com.crud.tasks1.service.SimpleEmailService;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class EmailScheduler {

    public final static String SUBJECT = "Tasks: Once a day email";

    private SimpleEmailService simpleEmailService;
    private TaskRepository taskRepository;
    private AdminConfig adminConfig;


    public EmailScheduler(SimpleEmailService simpleEmailService, TaskRepository taskRepository,
                          AdminConfig adminConfig) {
        this.simpleEmailService = simpleEmailService;
        this.taskRepository = taskRepository;
        this.adminConfig = adminConfig;
    }

    @Scheduled(cron = "0 0 10 * * *")
    public void sendInformationEmail() {
        long size = taskRepository.count();

        if (size > 1) {
            messageDependingOnSizeOfRecords(size, " tasks");
        } else if (size == 1) {
            messageDependingOnSizeOfRecords(size, " task");
        }
    }

    private void messageDependingOnSizeOfRecords(long size, String pluralityOfTask) {
        simpleEmailService.send(new Mail(adminConfig.getAdminMail(), null, SUBJECT,
                "Currently in database you got: " + size + pluralityOfTask));
    }
}
