package br.com.andrergomes.service.example2;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.SchedulingConfigurer;
import org.springframework.scheduling.config.ScheduledTaskRegistrar;

import br.com.andrergomes.service.example2.service.TaskService;

public class ScheduleTasksManager implements SchedulingConfigurer {
		
	private TaskService taskService;

	@Autowired
	public ScheduleTasksManager(TaskService taskService) {
		this.taskService = taskService;
	}

	@Override
	public void configureTasks(ScheduledTaskRegistrar taskRegistrar) {
		taskRegistrar.setScheduler(taskExecutor());
	}

	@Bean(destroyMethod = "shutdown")
	public Executor taskExecutor() {
		return Executors.newScheduledThreadPool(10);
	}
}
