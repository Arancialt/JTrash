package view;

import java.awt.Dimension;

import javax.swing.JPanel;

import controller.actionListener.ExitActionListener;
import controller.actionListener.ProfileActionListener;
import controller.actionListener.StartActionListener;

import java.awt.BorderLayout;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.GridLayout;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class TitleScreen extends JPanel {

	private static final long serialVersionUID = 1L;

	/**
	 * Create the panel.
	 */
	public TitleScreen() {
		setBackground(new Color(0, 100, 0));
		setLayout(new BorderLayout());
		
		JLabel titleLabel = new JLabel("JTRASH");
		titleLabel.setForeground(new Color(255, 255, 255));
		titleLabel.setFont(new Font("MS Gothic", Font.PLAIN, 70));
		titleLabel.setHorizontalAlignment(JLabel.CENTER);
		add(titleLabel, BorderLayout.CENTER);
		
		JButton btnNewButton = new JButton("Start");
		JButton btnProfile = new JButton("Profile");
		JButton btnExit = new JButton("Exit");
		
		btnNewButton.addActionListener(new StartActionListener());
		btnProfile.addActionListener(new ProfileActionListener());
		btnExit.addActionListener(new ExitActionListener());
		
		JPanel buttonPanel = new JPanel();
		buttonPanel.setLayout(new GridLayout(1, 3));
		
		// Imposta le dimensioni preferite per i pulsanti (larghezza e altezza)
        Dimension buttonSize = new Dimension(100, 50);
        btnNewButton.setPreferredSize(buttonSize);
        btnProfile.setPreferredSize(buttonSize);
        btnExit.setPreferredSize(buttonSize);
		
        // Aggiungi i pulsanti al pannello
        buttonPanel.add(btnNewButton);
        buttonPanel.add(btnProfile);
        buttonPanel.add(btnExit);
        
		add(buttonPanel, BorderLayout.SOUTH);		
	}
}
