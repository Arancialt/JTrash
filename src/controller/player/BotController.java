package controller.player;

import java.util.ArrayList;

import model.Carta;
import model.PlayerBot;

public class BotController extends PlayerController {
	public BotController(PlayerBot player) {
		super(player);
	}

	@Override
	public TurnState playTurn(Carta topCard, Carta mano, int _position) {
		if (currentState == null) {
			// topCard != null && card == null && position == -1
			ArrayList<Carta> terra = player.getMano();
			Carta.Valore valore = topCard.getValore();
			if (valore == Carta.Valore.JOLLY || valore == Carta.Valore.KING
					|| (valore.getValore() <= this.player.getMaxCards() && terra.get(valore.getValore() - 1).getIsCoperta())) {
				currentState = TurnState.DRAWFROMDISCARDPILE;
				return currentState;
			}
			/*
			 * In tutti gli altri casi (jack, queen, carta già scoperta) pesca dalla pila
			 * normale
			 */
			currentState = TurnState.DRAWFROMPILE;
			return currentState;
		}

		// topCard == null && card != null && position == -1
		switch (currentState) {
		case DRAWFROMDISCARDPILE:
		case DRAWFROMPILE:
			ArrayList<Carta> terra = player.getMano();
			Carta.Valore valore = mano.getValore();
			while (valore == Carta.Valore.JOLLY || valore == Carta.Valore.KING || (valore != Carta.Valore.JACK
					&& valore != Carta.Valore.QUEEN && valore.getValore() <= this.player.getMaxCards() && terra.get(valore.getValore() - 1).getIsCoperta())) {
				if (valore == Carta.Valore.JOLLY || valore == Carta.Valore.KING) {
					for (int i = 0; i < terra.size(); i++) {
						if (terra.get(i).getIsCoperta()) {
							mano = player.replace(i, mano);
							break;
						}
					}
				} else {
					mano = player.replace(valore.getValore() - 1, mano);
				}
				valore = mano.getValore();
			}
			lastCard = mano;
			break;
		default:
			break;
		}
		currentState = null;
		//System.out.println("playTurn, playerHasFinishedTurn: " + player.hasFinishedRound());
		return player.hasFinishedRound() ? TurnState.WIN_ROUND : TurnState.END;
	}

}
