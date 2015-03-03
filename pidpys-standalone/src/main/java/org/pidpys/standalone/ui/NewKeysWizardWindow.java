package org.pidpys.standalone.ui;

import java.awt.Image;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import net.miginfocom.swing.MigLayout;

import org.pidpys.standalone.Resources;

public class NewKeysWizardWindow extends AbstractWizardWindow {

	private KeyInfoPanel keyInfoPanel;
	private SelectAlgorithmPanel selectAlgorithmPanel;
	
	public NewKeysWizardWindow() {
		super.setTitle("New Keys");
		
		keyInfoPanel = new KeyInfoPanel();
		addStep("keyInfo", keyInfoPanel);
		
		selectAlgorithmPanel = new SelectAlgorithmPanel();
		addStep("selectAlgorithm", selectAlgorithmPanel);
		
		gotoStep("keyInfo");
	}

	

	@Override
	protected Image createWizardImage() {
		try {
			return ImageIO.read(this.getClass().getClassLoader().getResource(Resources.NEW_KEYS_WIZARD_IMAGE));
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
		
	}
	
	private class KeyInfoPanel extends StepPanel {
		private JTextField keyNameField;
		private JTextArea commentsArea;
		
		public KeyInfoPanel() {
			super(new MigLayout("wrap 1", "[300!]", "150[][]10[][]"));
			add(new JLabel("Name"));
			keyNameField = new JTextField();
			add(keyNameField, "width 300!");
			add(new JLabel("Comments"));
			commentsArea = new JTextArea();
			JScrollPane scrollArea = new JScrollPane(commentsArea);
			add(scrollArea, "width 300!, height 150!");
		}

		@Override
		public String next() {
			return "selectAlgorithm";
		}
		
	}
	
	private class SelectAlgorithmPanel extends StepPanel {
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
