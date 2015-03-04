package org.pidpys.standalone.ui;

import java.awt.Image;
import java.awt.event.ActionEvent;
import java.io.IOException;
import java.util.Arrays;

import javax.imageio.ImageIO;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.event.CaretEvent;

import net.miginfocom.swing.MigLayout;

import org.pidpys.standalone.Resources;

public class NewKeysWizardWindow extends AbstractWizardWindow {

	private KeyInfoStepPanel keyInfoPanel;
	private SelectAlgorithmStepPanel selectAlgorithmPanel;
	private KeyPasswordStepPanel keyPasswordPanel;
	
	public NewKeysWizardWindow() {
		super.setTitle("New Keys");
		
		keyInfoPanel = new KeyInfoStepPanel();
		addStep("keyInfo", keyInfoPanel);
		
		selectAlgorithmPanel = new SelectAlgorithmStepPanel();
		addStep("selectAlgorithm", selectAlgorithmPanel);
		
		keyPasswordPanel = new KeyPasswordStepPanel();
		addStep("keyPassword", keyPasswordPanel);
		
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
	
	private class KeyInfoStepPanel extends StepPanel {
		private JTextField keyNameField;
		private JTextArea commentsArea;
		
		public KeyInfoStepPanel() {
			super(new MigLayout("wrap 1", "[300!]", "70[][]10[][]"));
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
	
	private class SelectAlgorithmStepPanel extends StepPanel {
		private JComboBox algorithmSelection;
		private JComboBox keyLengthSelection;
		
		public SelectAlgorithmStepPanel() {
			super(new MigLayout("wrap 2", "[120!][]", "150[]40[]"));
			
			add(new JLabel("Select algorithm"));
			algorithmSelection = new JComboBox<String>(new String[]{"RSA", "DSA"});
			add(algorithmSelection, "width 200!");
			
			add(new JLabel("Key length"));
			keyLengthSelection = new JComboBox<String>(new String[]{"2048", "1024", "512", "256", "127"});
			add(keyLengthSelection, "width 200!");
		}

		@Override
		public String next() {
			return "keyPassword";
		}
		
	}
	
	private class KeyPasswordStepPanel extends StepPanel {
		private JPasswordField password;
		private JPasswordField passwordConfirm;
		
		public KeyPasswordStepPanel() {
			super(new MigLayout("wrap 2", "[120!][]", "150[]40[]"));
			add(new JLabel("Password"));
			password = new JPasswordField();
			password.addCaretListener(this::checkPasswordsTheSame);
			add(password, "width 200!");
			add(new JLabel("Confirm"));
			passwordConfirm = new JPasswordField();
			passwordConfirm.addCaretListener(this::checkPasswordsTheSame);
			add(passwordConfirm, "width 200!");
		}
		
		private void checkPasswordsTheSame(CaretEvent event) {
			char[] value = password.getPassword();
			char[] confirmation = passwordConfirm.getPassword();
			setNextButtonEnabled(Arrays.equals(value, confirmation));
			Arrays.fill(value, (char) 0);
			Arrays.fill(confirmation, (char) 0);
		}
		
		
		
		@Override
		public boolean canGoNext() {
			char[] value = password.getPassword();
			try {
				if (value.length == 0) {
					int reply = JOptionPane.showConfirmDialog(NewKeysWizardWindow.this, 
															  "This is unsecure. Are you sure that you want to continue?",
															  "You don't specify password.",
															  JOptionPane.YES_NO_OPTION);
					return reply == JOptionPane.YES_OPTION;
				}
				return true;
			} finally {
				Arrays.fill(value, (char) 0);
			}
		}

		@Override
		public String next() {
			
			return super.next();
		}
	}
	
}
