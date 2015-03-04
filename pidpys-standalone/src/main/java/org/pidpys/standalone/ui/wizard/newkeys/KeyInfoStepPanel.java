package org.pidpys.standalone.ui.wizard.newkeys;

import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import net.miginfocom.swing.MigLayout;

import org.pidpys.standalone.ui.wizard.StepPanel;

class KeyInfoStepPanel extends StepPanel {
	private JTextField keyNameField;
	private JTextArea commentsArea;
	
	public KeyInfoStepPanel() {
		super(new MigLayout("wrap 1", "[]", "(30%)[][]20[][]"));
		add(new JLabel("Name"));
		keyNameField = new JTextField();
		add(keyNameField, "width 100%");
		add(new JLabel("Comments"));
		commentsArea = new JTextArea();
		JScrollPane scrollArea = new JScrollPane(commentsArea);
		add(scrollArea, "width 100%, height 100%");
	}

	@Override
	public String next() {
		return "selectAlgorithm";
	}
	
}