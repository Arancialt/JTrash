package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JPanel;

import controller.actionListener.unAvversarioActionListener;

import javax.swing.JLabel;
import java.awt.Font;
import java.awt.GridLayout;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class NumeroAvversariScreen extends JPanel {

	private static final long serialVersionUID = 1L;

	/**
	 * Create the panel.
	 */
	public NumeroAvversariScreen(Frame frame) {
		setBackground(new Color(0, 100, 0));
		setLayout(new BorderLayout());
		
		JLabel opponentsNumberLabel = new JLabel("Select the number of opponents:");
		opponentsNumberLabel.setForeground(new Color(255, 255, 255));
		opponentsNumberLabel.setFont(new Font("MS Gothic", Font.PLAIN, 50));
		opponentsNumberLabel.setHorizontalAlignment(JLabel.CENTER);
		add(opponentsNumberLabel, BorderLayout.CENTER);
		
		JPanel buttonPanel = new JPanel();
		buttonPanel.setLayout(new GridLayout(1, 3));
		
		JButton oneButton = new JButton("One");
		buttonPanel.add(oneButton);
		oneButton.addActionListener(e -> {
			frame.setPlayers(2);
			frame.getCardLayout().show(frame.getCardPanel(), "GameScreen");
		});
		
		JButton twoButton = new JButton("Two");
		twoButton.addActionListener(e -> {
			frame.setPlayers(3);
			frame.getCardLayout().show(frame.getCardPanel(), "GameScreen");
		});
		buttonPanel.add(twoButton);
		
		JButton threeButton = new JButton("Three");
		threeButton.addActionListener(e -> {
			frame.setPlayers(4);
			frame.getCardLayout().show(frame.getCardPanel(), "GameScreen");
		});
		buttonPanel.add(threeButton);
		
		// Imposta le dimensioni preferite per i pulsanti (larghezza e altezza)
        Dimension buttonSize = new Dimension(100, 50);
        oneButton.setPreferredSize(buttonSize);
        twoButton.setPreferredSize(buttonSize);
        threeButton.setPreferredSize(buttonSize);
        
        add(buttonPanel, BorderLayout.SOUTH);
	}

}
