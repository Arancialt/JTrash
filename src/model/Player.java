package model;

import java.util.ArrayList;
import java.util.Random;

import model.Carta.Valore;

public abstract class Player {

	protected ArrayList<Carta> terra;
	private Carta cartaPescata;
	protected int maxCards = 10;
	
	private String nome;
	
	public Player(String nome) {
		terra = new ArrayList<Carta>();
		cartaPescata = null;
		this.nome = nome;
	}
	
	public void restart() {
		terra.clear();
		cartaPescata = null;
	}

	/*
	 * Metodo che controlla se il giocatore ha concluso il round
	 * Per round si intende il momento in cui il giocatore 
	 * ha scoperto tutte le carte
	 */
	public boolean hasFinishedRound() {
		return terra.stream().filter(c -> c.getIsCoperta()).count() == 0;
	}
	
	/*
	 * Metodo che controlla se il giocatore ha vinto.
	 * Per vittoria si intende il momento in cui il giocatore
	 * rimane con 0 carte in mano
	 */
	public boolean hasWonGame() {
		return terra.size() == 0;
	}
	
	public Carta replace(int i, Carta card) {
		Carta aTerra = terra.get(i);
		aTerra.setIsCoperta(false);
		card.setIsCoperta(false);
		terra.set(i, card);
		return aTerra;
	}
	
	public void displayCardsInHand() {
		for (Carta c : terra) {
			System.out.print(c+ ", ");
		}
		System.out.println("");
	}
	
	public ArrayList<Carta> getMano() {
		return terra;
	}

	public void setMano(ArrayList<Carta> mano) {
		this.terra = mano;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}
	
	public void setCartaPescata(Carta cartaPescata) {
		this.cartaPescata = cartaPescata;
	}
	
	public Carta getCartaPescata() {
		return cartaPescata;
	}
	
	public void pescaCarta(Carta carta) {
		terra.add(carta);
	}
	
	public void winRound() {
		maxCards-=1;
	}
	
	public int getMaxCards() {
		return maxCards;
	}
}
