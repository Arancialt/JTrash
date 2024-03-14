package view;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JButton;
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
		add(new JLabel(Integer.toString(entry.played)));
		add(new JLabel(Integer.toString(entry.won)));
		add(new JLabel(Integer.toString(entry.lost)));
	}
}
