package org.pidpys.standalone.ui;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;

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
	
	public AbstractWizardWindow() {
		build();
	}

	private void build() {
		JPanel windowLayout = new JPanel();
		windowLayout.setLayout(new BorderLayout());
		super.add(windowLayout);

		JPanel buttonsPanel = new JPanel();
		buttonsPanel.setLayout(new FlowLayout(FlowLayout.RIGHT));
		Border border = BorderFactory.createEtchedBorder();
		buttonsPanel.setBorder(BorderFactory.createEtchedBorder());
		
		nextButton = new JButton("Next");
		buttonsPanel.add(nextButton);
		
		previousButton = new JButton("Previous");
		buttonsPanel.add(previousButton);
		
		cancelButton = new JButton("Cancel");
		buttonsPanel.add(cancelButton);
		
		windowLayout.add(buttonsPanel, BorderLayout.SOUTH);
		
		JLabel wizardImage = new JLabel(new ImageIcon(createWizardImage()));
		windowLayout.add(wizardImage, BorderLayout.WEST);
		
		bodyPanel = new JPanel(new CardLayout());
		windowLayout.add(bodyPanel,BorderLayout.CENTER);
	}
	
	protected void addStep(String stepKey, JPanel stepPanel) {
		bodyPanel.add(stepPanel, stepKey);
	}
	
	protected void gotoStep(String stepKey) {
		((CardLayout)bodyPanel.getLayout()).show(bodyPanel, stepKey);
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
}
