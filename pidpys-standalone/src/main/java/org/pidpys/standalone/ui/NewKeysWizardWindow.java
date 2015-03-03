package org.pidpys.standalone.ui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.Image;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;

import net.miginfocom.swing.MigLayout;

import org.pidpys.standalone.Resources;

public class NewKeysWizardWindow extends AbstractWizardWindow {

	private SelectAlgorithmPanel selectAlgorithmPanel;
	
	public NewKeysWizardWindow() {
		super.setTitle("New Keys");
		super.setSize(640, 480);
		
		selectAlgorithmPanel = new SelectAlgorithmPanel();
		addStep("selectAlgorithm", selectAlgorithmPanel);
		gotoStep("selectAlgorithm");
	}

	

	@Override
	protected Image createWizardImage() {
		try {
			return ImageIO.read(this.getClass().getClassLoader().getResource(Resources.NEW_KEYS_WIZARD_IMAGE));
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
		
	}
	
	private class SelectAlgorithmPanel extends JPanel {
		private JComboBox algorithmSelection;
		private JComboBox keyLengthSelection;
		
		public SelectAlgorithmPanel() {
			super(new MigLayout("wrap 2", "[120!][]", "150[]40[]"));
			
			add(new JLabel("Select algorithm"));
			algorithmSelection = new JComboBox<String>(new String[]{"RSA", "DSA"});
			add(algorithmSelection, "width 200!");
			
			add(new JLabel("Key length"));
			keyLengthSelection = new JComboBox<String>(new String[]{"2048", "1024", "512", "256", "127"});
			add(keyLengthSelection, "width 200!");
		}
	}
	
}
