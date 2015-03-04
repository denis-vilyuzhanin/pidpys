package org.pidpys.standalone.ui.wizard.newkeys;

import java.util.Arrays;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.event.CaretEvent;

import net.miginfocom.swing.MigLayout;

import org.pidpys.standalone.ui.wizard.StepPanel;

class KeyPasswordStepPanel extends StepPanel {
	
	private JPasswordField password;
	private JPasswordField passwordConfirm;
	
	public KeyPasswordStepPanel() {
		super(new MigLayout("wrap 2", "[][]", "(50% - pref)[][]"));
		add(new JLabel("Password"));
		password = new JPasswordField();
		password.addCaretListener(this::checkPasswordsTheSame);
		add(password, "width 100%");
		add(new JLabel("Confirm"));
		passwordConfirm = new JPasswordField();
		passwordConfirm.addCaretListener(this::checkPasswordsTheSame);
		add(passwordConfirm, "width 100%");
	}
	
	private void checkPasswordsTheSame(CaretEvent event) {
		char[] value = password.getPassword();
		char[] confirmation = passwordConfirm.getPassword();
		getWizard().setNextButtonEnabled(Arrays.equals(value, confirmation));
		Arrays.fill(value, (char) 0);
		Arrays.fill(confirmation, (char) 0);
	}
	
	
	
	@Override
	public boolean canGoNext() {
		char[] value = password.getPassword();
		try {
			if (value.length == 0) {
				int reply = JOptionPane.showConfirmDialog(getWizard(), 
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
		return "keyStorage";
	}
}