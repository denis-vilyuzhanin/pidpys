package org.pidpys.standalone.view;

import java.awt.AWTException;
import java.awt.MenuItem;
import java.awt.PopupMenu;
import java.awt.SystemTray;
import java.awt.TrayIcon;
import java.io.IOException;

import javax.annotation.PostConstruct;
import javax.swing.ImageIcon;

import org.pidpys.standalone.Resources;
import org.pidpys.standalone.controller.ApplicationController;
import org.pidpys.standalone.controller.NewKeysWizardController;
import org.pidpys.standalone.controller.TrayController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;

@Component
public class TrayView {

	@Value(Resources.TRAY_ICON)
	Resource trayIconImageResouce;
	
	@Autowired
	ApplicationController applicationController;
	
	@Autowired
	TrayController trayController;
	
	@Autowired
	NewKeysWizardController newKeysWizardController;
	
	private ImageIcon trayIconImage;
	
	@PostConstruct
	public void init() throws IOException {
		trayIconImage = new ImageIcon(trayIconImageResouce.getURL());
	}
	
	public void show() throws AWTException {
		MenuItem newKeysItem = new MenuItem("New Keys");
		newKeysItem.addActionListener(newKeysWizardController::createNewKeys);
		
		MenuItem exitItem = new MenuItem("Exit");
		exitItem.addActionListener(applicationController::exitAction);
		
		PopupMenu popup = new PopupMenu();
		popup.add(newKeysItem);
		popup.add(exitItem);
		
		TrayIcon trayIcon = new TrayIcon(trayIconImage.getImage());
		trayIcon.setPopupMenu(popup);
		
		SystemTray tray = SystemTray.getSystemTray();
		tray.add(trayIcon);

	}
	
}
