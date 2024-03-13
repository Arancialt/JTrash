package view;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.ListModel;

import controller.JTrash;
import model.PlayerUtente;

public class StatsPanel extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private JLabel playerNameLabel;
	private JTextField playerNameField;
	private JButton addButton;
	private JList<DefaultListModel<String>> lista;
	private DefaultListModel<String> listModel;

	private JTrash controller = JTrash.getInstance();

	public StatsPanel() {

		setLayout(new BorderLayout(50, 50));

		JPanel eastPanel = new JPanel();
		JPanel westPanel = new JPanel();
		JPanel southPanel = new JPanel();
		JPanel northPanel = new JPanel();
		JPanel centerPanel = new JPanel();

		playerNameLabel = new JLabel("Player's name:");
		playerNameField = new JTextField(20);
		addButton = new JButton("Add");

		// TODO aggiungere action listener per salvare e importare
		addButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				String name = playerNameField.getText();
				name = name.replace(",", "");
				if (name.isBlank()) {
					return;
				}
				var newEntry = controller.getDatabase().addPlayer(name);
				if (newEntry != null) {
					listModel.addElement(newEntry.toString());
				}
			};
		});

		JPanel inputPanel = new JPanel();
		inputPanel.add(playerNameLabel);
		inputPanel.add(playerNameField);
		inputPanel.add(addButton);

		listModel = new DefaultListModel<>();
		// per ogni riga del database
		for (var entry : JTrash.getInstance().getDatabase().getEntries()) {
			listModel.addElement(entry.toString());
		}
		lista = new JList(listModel);
		JScrollPane scrollPane = new JScrollPane(lista);

		add(inputPanel, BorderLayout.NORTH);
		add(scrollPane, BorderLayout.CENTER);

		add(eastPanel, BorderLayout.EAST);
		add(westPanel, BorderLayout.WEST);
		add(southPanel, BorderLayout.SOUTH);
	}
}
