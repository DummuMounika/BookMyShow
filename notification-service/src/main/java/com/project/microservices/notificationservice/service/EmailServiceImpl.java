package com.project.microservices.notificationservice.service;

import java.util.concurrent.CompletableFuture;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import jakarta.mail.internet.MimeMessage;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class EmailServiceImpl implements EmailService {
	
	private JavaMailSender javaMailSender;

	@Autowired
	public EmailServiceImpl(JavaMailSender javaMailSender) {
		super();
		this.javaMailSender = javaMailSender;
	}
	
	@Value("${spring.mail.username}")
	private String sender;

	@Override
	public CompletableFuture<String> sendEmail(String to, String subject, String body){
		try {
			  MimeMessage mimeMessage = javaMailSender.createMimeMessage();
	          MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage, true);
	          mimeMessageHelper.setFrom(sender);
	          mimeMessageHelper.setTo(to); 
	          mimeMessageHelper.setSubject(subject);
			  mimeMessageHelper.setText(body, true); // true indicates HTML content
			  javaMailSender.send(mimeMessage);
			  log.info("Email sent successfully to {}", to);
			  return CompletableFuture.completedFuture("Email sent successfully ");
		}catch (Exception e) {
            log.error("Error sending email", e);
            return CompletableFuture.completedFuture("Error sending email");
        }
		
	}
}
