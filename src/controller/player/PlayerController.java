package controller.player;

import model.Carta;
import model.Player;

public abstract class PlayerController {
	protected final Player player;
	protected TurnState currentState = null;
	protected Carta lastCard = null;
	
	public PlayerController(Player player) {
		this.player=player;
	}
	
	abstract public TurnState playTurn(Carta topCard, Carta card, int position);
	
	public Carta getLastCard() {
		return lastCard;
	}
}
