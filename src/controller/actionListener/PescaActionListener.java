package controller.actionListener;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import controller.JTrash;
import model.Carta;
import model.Mazzo;
import model.Player;
import view.GameScreen4;

public class PescaActionListener implements ActionListener {

	@Override
	public void actionPerformed(ActionEvent e) {
		JTrash model = JTrash.getInstance();
		GameScreen4 screen = GameScreen4.getInstance();
		
		if(model.getCurrentState().equals(model.getPlayerTurnState())) {
			screen.getGameLogPanel().log("Il giocatore pesca");
			// pesca una carta e la da al giocatore
			Mazzo mazzo = model.getMazzo();
			Carta cartaPescata = mazzo.pescaCarta();
			
			Player giocatore = model.getCurrentPlayer();
			giocatore.setCartaPescata(cartaPescata);
			screen.getRootPanel().repaint();
		}
	}
}
