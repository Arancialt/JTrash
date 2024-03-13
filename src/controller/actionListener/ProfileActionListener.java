package controller.actionListener;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import view.Frame;

public class ProfileActionListener implements ActionListener {

	@Override
	public void actionPerformed(ActionEvent e) {
		Frame frame = Frame.getInstance();
		frame.getCardLayout().show(frame.getCardPanel(), "ProfileScreen");
		
	}

}
