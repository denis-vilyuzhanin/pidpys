package org.pidpys.standalone;

import java.awt.AWTException;

import org.pidpys.standalone.controller.ApplicationController;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan("org.pidpys.standalone")
public class StandaloneApplication {

	public static void main(String[] args) throws AWTException {
		AnnotationConfigApplicationContext context = 
				new AnnotationConfigApplicationContext(StandaloneApplication.class);
		ApplicationController applicationController = 
				context.getBean(ApplicationController.class);
		applicationController.start();
	}
	
	
}
