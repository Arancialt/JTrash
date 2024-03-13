package controller.actionListener;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import controller.JTrash;
import view.Frame;

public class StartActionListener implements ActionListener {
	
	@Override
	public void actionPerformed(ActionEvent e) {
		
		Frame frame = Frame.getInstance();
		
		frame.getCardLayout().show(frame.getCardPanel(), "NumeroAvversariScreen");
	}
}
