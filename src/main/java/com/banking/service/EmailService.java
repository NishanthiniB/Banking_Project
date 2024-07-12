package com.banking.service;

import com.banking.dto.EmailDetails;

public interface EmailService {

    void sendEmailAlert(EmailDetails details);
    void sendEmailWithAttachments(EmailDetails emailDetails);
}
