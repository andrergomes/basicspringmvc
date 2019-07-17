package br.com.andrergomes.service.example2.service;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class TaskService {

	@Scheduled(fixedRate=15000)
	public void printMessage() {
		System.out.println("I am called by Spring scheduler");
	}
}
