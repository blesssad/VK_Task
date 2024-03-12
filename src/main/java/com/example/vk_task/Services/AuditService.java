package com.example.vk_task.Services;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDateTime;

@Service
public class AuditService {
    @Value("${audit.log.file.path}")
    private String auditLogFilePath;

    public void logMessage(String accessGranted, String requestUrl, String method) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        LocalDateTime currentTime = LocalDateTime.now();
        String username = authentication.getName();

        String logEntry = String.format("[%s] User: %s, Access Granted: %s, RequestUrl: %s, Method: %s",
                currentTime, username, accessGranted, requestUrl, method);

        try (PrintWriter writer = new PrintWriter(new FileWriter(auditLogFilePath, true))) {
            writer.println(logEntry);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
