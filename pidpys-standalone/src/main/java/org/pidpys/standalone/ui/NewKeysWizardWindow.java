package org.pidpys.standalone.ui;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;

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
			super(new GridBagLayout());

			JLabel algorithmLabel = new JLabel("Select algorithm");
			GridBagConstraints algorithmLabelConstraint = new GridBagConstraints();
			algorithmLabelConstraint.gridx = 0;
			algorithmLabelConstraint.gridy = 0;
			algorithmLabelConstraint.ipadx = 70;
			super.add(algorithmLabel, algorithmLabelConstraint);
			
			algorithmSelection = new JComboBox<String>(new String[]{"RSA", "DSA"});
			GridBagConstraints algorithmSelectionConstraint = new GridBagConstraints();
			algorithmSelectionConstraint.gridx = 1;
			algorithmSelectionConstraint.gridy = 0;
			algorithmSelectionConstraint.fill = GridBagConstraints.HORIZONTAL;
			algorithmSelectionConstraint.ipadx = 100;
			super.add(algorithmSelection, algorithmSelectionConstraint);
			
			
			JLabel keyLengthLabel = new JLabel("Key length");
			GridBagConstraints algorithmLabelContraint = new GridBagConstraints();
			algorithmLabelContraint.gridx = 0;
			algorithmLabelContraint.gridy = 1;
			algorithmLabelContraint.ipadx = 70;
			algorithmLabelContraint.ipady = 30;
			super.add(keyLengthLabel, algorithmLabelContraint);
			
			keyLengthSelection = new JComboBox<String>(new String[]{"2048", "1024", "512", "256", "127"});
			GridBagConstraints keyLengthSelectionConstraint = new GridBagConstraints();
			keyLengthSelectionConstraint.gridx = 1;
			keyLengthSelectionConstraint.gridy = 1;
			keyLengthSelectionConstraint.fill = GridBagConstraints.HORIZONTAL;
			keyLengthSelectionConstraint.ipadx = 100;
			super.add(keyLengthSelection, keyLengthSelectionConstraint);
		}
	}
	
}
