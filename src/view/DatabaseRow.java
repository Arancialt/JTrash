package view;

import java.awt.FlowLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JLabel;
import javax.swing.JPanel;

import controller.JTrash;
import model.DatabaseEntry;

public class DatabaseRow extends JPanel {
	
	JTrash controller = JTrash.getInstance();
	
	public DatabaseRow(DatabaseEntry entry) {
		setLayout(new FlowLayout(FlowLayout.LEFT));
		JLabel name = new JLabel(entry.name);
		add(name);
		name.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				// quando cliccato imposta il nome in JTrash
			}
		});
		add(new JLabel(Integer.toString(entry.played)));
		add(new JLabel(Integer.toString(entry.won)));
		add(new JLabel(Integer.toString(entry.lost)));
	}
}
