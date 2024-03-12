package com.example.vk_task.Controllers;

import com.example.vk_task.Services.AuditService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

@ControllerAdvice
public class ExceptionController {
    private final AuditService auditService;

    public ExceptionController(AuditService auditService) {
        this.auditService = auditService;
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<Object> handleAccessDeniedException(Exception ex, WebRequest request) {
        auditService.logMessage("NO " + ex.getMessage(), request.getDescription(false), "ERROR");

        return new ResponseEntity<>(ex.getMessage(), HttpStatus.I_AM_A_TEAPOT);
    }

}
