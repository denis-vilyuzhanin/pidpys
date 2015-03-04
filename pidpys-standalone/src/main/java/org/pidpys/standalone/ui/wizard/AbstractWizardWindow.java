package org.pidpys.standalone.ui.wizard;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.image.BufferedImage;
import java.util.Deque;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.Border;

public abstract class AbstractWizardWindow extends JFrame {

	private JButton nextButton;
	private JButton previousButton;
	private JButton cancelButton;
	private JPanel bodyPanel;
	private String currentStep;
	private Map<String, StepPanel> steps = new HashMap<>();
	private Deque<String> passedSteps = new LinkedList<>();
	
	public AbstractWizardWindow() {
		build();
	}

	private void build() {
		setSize(640, 480);
		JPanel windowLayout = new JPanel();
		windowLayout.setLayout(new BorderLayout());
		super.add(windowLayout);

		JPanel buttonsPanel = new JPanel();
		buttonsPanel.setLayout(new FlowLayout(FlowLayout.RIGHT));
		Border border = BorderFactory.createEtchedBorder();
		buttonsPanel.setBorder(BorderFactory.createEtchedBorder());
		
		previousButton = new JButton("Previous");
		previousButton.addActionListener(this::previousStep);
		previousButton.setEnabled(false);
		buttonsPanel.add(previousButton);
		
		nextButton = new JButton("Next");
		nextButton.addActionListener(this::nextStep);
		buttonsPanel.add(nextButton);
		
		cancelButton = new JButton("Cancel");
		buttonsPanel.add(cancelButton);
		
		windowLayout.add(buttonsPanel, BorderLayout.SOUTH);
		
		JLabel wizardImage = new JLabel(new ImageIcon(createWizardImage()));
		windowLayout.add(wizardImage, BorderLayout.WEST);
		
		bodyPanel = new JPanel(new CardLayout());
		windowLayout.add(bodyPanel,BorderLayout.CENTER);
	}
	
	protected void addStep(String stepKey, StepPanel stepPanel) {
		stepPanel.attach(this);
		bodyPanel.add(stepPanel, stepKey);
		steps.put(stepKey, stepPanel);
	}
	
	public void beginStep(String stepKey) {
		passedSteps.clear();
		gotoStep(stepKey);
	}
	
	protected void gotoStep(String stepKey) {
		currentStep = stepKey;
		((CardLayout)bodyPanel.getLayout()).show(bodyPanel, stepKey);
	}
	
	private void nextStep(ActionEvent event) {
		StepPanel current = steps.get(currentStep);
		if (current.canGoNext()) {
			passedSteps.push(currentStep);
			previousButton.setEnabled(true);
			gotoStep(current.next());
		}
	}
	
	private void previousStep(ActionEvent event) {
		gotoStep(passedSteps.pop());
		previousButton.setEnabled(!passedSteps.isEmpty());
	}
	
	protected Image createWizardImage() {
		int width = 257;
		int height = 430;
		BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_3BYTE_BGR);
		Graphics2D g = (Graphics2D) image.createGraphics();
		try {
			g.setColor(Color.WHITE);
			g.fillRect(0, 0, width, height);
		} finally {
			g.dispose();
		}
		return image;
	}
	
	public void setNextButtonEnabled(boolean isEnabled) {
		nextButton.setEnabled(isEnabled);
	}
}
