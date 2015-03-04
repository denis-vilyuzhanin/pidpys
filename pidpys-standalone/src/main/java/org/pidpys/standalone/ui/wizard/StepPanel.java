package org.pidpys.standalone.ui.wizard;

import java.awt.LayoutManager;

import javax.swing.JPanel;

public class StepPanel extends JPanel {

	protected AbstractWizardWindow wizard;
	
	public StepPanel() {
		super();
	}

	public StepPanel(boolean isDoubleBuffered) {
		super(isDoubleBuffered);
	}

	public StepPanel(LayoutManager layout, boolean isDoubleBuffered) {
		super(layout, isDoubleBuffered);
	}

	public StepPanel(LayoutManager layout) {
		super(layout);
	}

	public String next() {
		return null;
	}
	
	public boolean canGoNext() {
		return true;
	}
	
	void attach(AbstractWizardWindow wizard) {
		this.wizard = wizard;
	}
	
	public AbstractWizardWindow getWizard() {
		return this.wizard;
	}
}