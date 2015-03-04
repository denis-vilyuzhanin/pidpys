package org.pidpys.standalone.ui.wizard.newkeys;

import java.awt.Button;
import java.awt.Image;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import org.pidpys.standalone.Resources;
import org.pidpys.standalone.ui.wizard.AbstractWizardWindow;


public class NewKeysWizardWindow extends AbstractWizardWindow {

	private KeyInfoStepPanel keyInfoPanel;
	private SelectAlgorithmStepPanel selectAlgorithmPanel;
	private KeyPasswordStepPanel keyPasswordPanel;
	private KeyStorageStepPanel keyStoragePanel;
	private File defaultKeysFolder = new File(System.getProperty("user.home"));
	
	public NewKeysWizardWindow() {
		super.setTitle("New Keys");
		
		keyInfoPanel = new KeyInfoStepPanel();
		addStep("keyInfo", keyInfoPanel);
		
		selectAlgorithmPanel = new SelectAlgorithmStepPanel();
		addStep("selectAlgorithm", selectAlgorithmPanel);
		
		keyPasswordPanel = new KeyPasswordStepPanel();
		addStep("keyPassword", keyPasswordPanel);
		
		keyStoragePanel = new KeyStorageStepPanel();
		addStep("keyStorage", keyStoragePanel);
		
		beginStep("selectAlgorithm");
	}

	

	@Override
	protected Image createWizardImage() {
		try {
			return ImageIO.read(this.getClass().getClassLoader().getResource(Resources.NEW_KEYS_WIZARD_IMAGE));
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
		
	}
	
}
