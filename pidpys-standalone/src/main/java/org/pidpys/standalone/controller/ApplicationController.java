package org.pidpys.standalone.controller;

import java.awt.AWTException;
import java.awt.event.ActionEvent;

import org.pidpys.standalone.view.TrayView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
public class ApplicationController {

	@Autowired
	TrayController trayController;
	
	@Autowired
	TrayView trayView;
	
	public void start() throws AWTException {
		trayView.show();
	}

	public void exitAction(ActionEvent e) {
		System.exit(0);
	}
}
