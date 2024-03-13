package controller.actionListener;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import controller.JTrash;
import view.Frame;

public class unAvversarioActionListener implements ActionListener {

	@Override
	public void actionPerformed(ActionEvent e) {
		
		// mostro la schermata di gioco
		Frame frame = Frame.getInstance();
		frame.getCardLayout().show(frame.getCardPanel(), "GameScreen");
		
		JTrash game = JTrash.getInstance();
		game.setup(2);
	}
}
