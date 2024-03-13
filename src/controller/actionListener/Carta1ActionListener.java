package controller.actionListener;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import controller.JTrash;

public class Carta1ActionListener implements ActionListener {

	@Override
	public void actionPerformed(ActionEvent e) {
		
		JTrash model = JTrash.getInstance();
		model.setCartaScelta(0);
		
	}
}
