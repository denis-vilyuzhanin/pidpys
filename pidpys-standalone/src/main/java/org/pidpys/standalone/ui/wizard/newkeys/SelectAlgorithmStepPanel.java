package org.pidpys.standalone.ui.wizard.newkeys;

import javax.swing.JComboBox;
import javax.swing.JLabel;

import net.miginfocom.swing.MigLayout;

import org.pidpys.standalone.ui.wizard.StepPanel;

class SelectAlgorithmStepPanel extends StepPanel {
	private JComboBox algorithmSelection;
	private JComboBox keyLengthSelection;
	
	public SelectAlgorithmStepPanel() {
		super(new MigLayout("wrap 2", "[][]", "(50% - pref)[][]"));
		
		add(new JLabel("Select algorithm"));
		algorithmSelection = new JComboBox<String>(new String[]{"RSA", "DSA"});
		add(algorithmSelection, "width 100%");
		
		add(new JLabel("Key length"));
		keyLengthSelection = new JComboBox<String>(new String[]{"2048", "1024", "512", "256", "127"});
		add(keyLengthSelection, "width 100%");
	}

	@Override
	public String next() {
		return "keyPassword";
	}
	
}