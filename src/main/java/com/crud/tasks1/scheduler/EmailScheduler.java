package com.crud.tasks1.scheduler;

import com.crud.tasks1.config.AdminConfig;
import com.crud.tasks1.domain.Mail;
import com.crud.tasks1.repository.TaskRepository;
import com.crud.tasks1.service.SimpleMailService;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class EmailScheduler {

    public final static String SUBJECT = "Tasks: Once a day email";

    private SimpleMailService simpleEmailService;
    private TaskRepository taskRepository;
    private AdminConfig adminConfig;


    public EmailScheduler(SimpleMailService simpleEmailService, TaskRepository taskRepository,
                          AdminConfig adminConfig) {
        this.simpleEmailService = simpleEmailService;
        this.taskRepository = taskRepository;
        this.adminConfig = adminConfig;
    }
//*/10 * * * * * co 10 sekund
// 0 0 10 * * * codziennie o 10
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
        simpleEmailService.sendSynchronizedMail(new Mail(adminConfig.getAdminMail(), null, SUBJECT,
                "Currently in database you got: " + size + pluralityOfTask));
    }
}
