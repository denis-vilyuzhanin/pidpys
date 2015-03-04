package org.pidpys.standalone.ui.wizard;

public interface WizardRouter<T extends StepPanel> {

	boolean route(AbstractWizardWindow wizard, T currentStep);
}
