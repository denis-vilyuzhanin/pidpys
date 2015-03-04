package org.pidpys.standalone.ui.wizard.newkeys;

import java.awt.event.ActionEvent;
import java.io.File;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JTextField;

import net.miginfocom.swing.MigLayout;

import org.pidpys.standalone.ui.wizard.StepPanel;

class KeyStorageStepPanel extends StepPanel {
	private JFileChooser fileChooser;
	private JTextField filePath;
	private JButton chooseFileButton;
	
	public KeyStorageStepPanel() {
		super(new MigLayout("", "", "40%[][]"));
		
		fileChooser = new JFileChooser();
		
		add(new JLabel("Store to file"), "wrap");
		filePath = new JTextField();
		add(filePath, "width 100%");
		
		chooseFileButton = new JButton("Browse...");
		chooseFileButton.addActionListener(this::chooseFile);
		add(chooseFileButton, "");
	}
	
	private void chooseFile(ActionEvent event) {
		fileChooser.setSelectedFile(new File(filePath.getText()));
		int result = fileChooser.showSaveDialog(getWizard());
		if (result == JFileChooser.APPROVE_OPTION) {
			filePath.setText(fileChooser.getSelectedFile().getAbsolutePath());
		}
	}
}