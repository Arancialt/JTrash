package controller.player;

import java.util.ArrayList;

import model.Carta;
import model.PlayerUtente;

public class RealPlayerController extends PlayerController {
	public RealPlayerController(PlayerUtente player) {
		super(player);
	}

	@Override
	public TurnState playTurn(Carta topCard, Carta mano, int position) {
		if (currentState == null) {
			currentState = TurnState.RESOLVING;
		}
		switch (currentState) {
		case RESOLVING:
			//System.out.println("RealPlayerController playTurn inizio turno con " + mano);
			ArrayList<Carta> terra = player.getMano();
			Carta.Valore valore = mano.getValore();
			while (valore == Carta.Valore.JOLLY || valore == Carta.Valore.KING || (valore != Carta.Valore.JACK
					&& valore != Carta.Valore.QUEEN && valore.getValore() <= this.player.getMaxCards() && terra.get(valore.getValore() - 1).getIsCoperta())) {
				//System.out.println("ho in mano il " + valore);
				if (valore == Carta.Valore.JOLLY || valore == Carta.Valore.KING) {
					currentState = TurnState.JOLLY;
					lastCard = mano;
					return currentState;
				} else {
					mano = player.replace(valore.getValore() - 1, mano);
				}
				valore = mano.getValore();
				//System.out.println("nuova mano " + mano);
			}
			lastCard = mano;
			//System.out.println("RealPlayerController playTurn finisco il turno con " + lastCard);
			break;
		case JOLLY:
			mano = player.replace(position, lastCard);
			lastCard = mano;
			currentState = TurnState.RESOLVING;
			return playTurn(null, mano, -1);
		case END:
			// System.out.println("Sono in stato: " + TurnState.END);
			break;
			
		case WIN_ROUND:
			//System.out.println("RealPlayerController playTurn sono in " + currentState);
			currentState = null;
			return TurnState.WIN_ROUND;
		}
		// controllo se il giocatore ha vinto il round
		if (player.hasFinishedRound()) {
			// se ha vinto sono in WIN_ROUND
			currentState = null;
			//System.out.println("RealPlayerController playTurn sono in " + TurnState.WIN_ROUND);
			return TurnState.WIN_ROUND;
		// altrimenti sono in end turn
		} else {
			currentState = null;
			//System.out.println("RealPlayerController playTurn sono in " + TurnState.END);
			return TurnState.END;
		}
	}

}
