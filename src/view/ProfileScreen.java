package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;

import controller.JTrash;

public class ProfileScreen extends JPanel {

	private static final long serialVersionUID = 1L;
	
	/**
	 * Create the panel.
	 */
	public ProfileScreen() {
		setBackground(new Color(0, 100, 0));
		setLayout(new BorderLayout(500, 100));
		
		JPanel eastPanel = new JPanel();
		JPanel westPanel = new JPanel();
		JPanel southPanel = new JPanel();
		JPanel northPanel = new JPanel();
		StatsPanel statsPanel = new StatsPanel();
		
		eastPanel.setBackground(new Color(0, 100, 0));
		westPanel.setBackground(new Color(0, 100, 0));
		southPanel.setBackground(new Color(0, 100, 0));
		northPanel.setBackground(new Color(0, 100, 0));
		
		JButton backButton = new JButton("<-");
		westPanel.add(backButton);
		backButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				Frame frame = Frame.getInstance();
				frame.getCardLayout().show(frame.getCardPanel(), "TitleScreen");
			}
			
		});
		
//		JPanel statsPanel = new JPanel();
//		statsPanel.setLayout(new BorderLayout());
//		JScrollPane statsArea = new JScrollPane();
//		statsPanel.add(statsArea, BorderLayout.CENTER);
        
		/*
		 * | nome giocatore | partite giocate| partite vinte | partite perse |
		 */
		
		add(eastPanel, BorderLayout.EAST);
		add(westPanel, BorderLayout.WEST);
		add(southPanel, BorderLayout.SOUTH);
		add(northPanel, BorderLayout.NORTH);
		add(statsPanel, BorderLayout.CENTER);
	}

}
