package org.pidpys.standalone.ui;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import org.pidpys.standalone.Resources;

public class NewKeysWizardWindow extends AbstractWizardWindow {

	public NewKeysWizardWindow() {
		super.setTitle("New Keys");
		super.setSize(640, 480);
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
