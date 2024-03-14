package controller;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Observable;
import java.util.Stack;

import controller.player.BotController;
import controller.player.PlayerController;
import controller.player.RealPlayerController;
import controller.player.TurnState;
import model.Carta;
import model.Database;
import model.Player;
import model.PlayerBot;
import model.PlayerUtente;
import view.Frame;
import view.GameScreen;

/*
 * Bisogna creare un campo per memorizzare le carta pescata:
 * da capire bene dove farlo, se nella classe player o qui nella
 * classe jtrash.
 * Bisogna quindi sistemare l'action listener che permette di pescare una carta
 * 
 * The method toArray is undefined for the type collection
 * 
 */
public class JTrash extends Observable {

	public static void main(String[] args) {

		// crea il model
		JTrash model = JTrash.getInstance();

		// crea la view
		Frame view = Frame.getInstance();

		// innesca il meccanismo observer observable
		// model.addObserver(view.getGameScreen());
		
		//AudioManager audioManager = AudioManager.getInstance();
		//audioManager.runMusic("res/music/Casino-Super-Mario-64-DS-Music-Extended.wav");
		//audioManager.runMusic("res/music/TECHNO.wav");
		
		while (true) {

		}
	}

	private int indexCurrentPlayer;
	private final ArrayList<Player> players = new ArrayList<Player>();
	private final ArrayList<PlayerController> controllers = new ArrayList<>();

	private Stack<Carta> mazzo;
	private Stack<Carta> stockPile;
	private boolean roundWon = false;
	private int firstWinner = Integer.MAX_VALUE;
	private ArrayList<Integer> lastWinners = new ArrayList<>();
	private PlayerUtente playerUtente = null;
	
	private static JTrash instance;
	Database database = new Database();

	private JTrash() {
		indexCurrentPlayer = 0;
		// players = new ArrayList<Player>();
		stockPile = new Stack<>();
	}

	/*
	 * Inizializa il gioco
	 */
	public void setup(int numeroGiocatori) {
		lastWinners.clear();
		mazzo = new Stack<>();
		stockPile = new Stack<>();
		riempiMazzo(numeroGiocatori);
		if (numeroGiocatori > 2) {
			riempiMazzo(numeroGiocatori);
		}
		Collections.shuffle(mazzo);
		stockPile.push(mazzo.pop());
		creaGiocatori(numeroGiocatori);
		distribuisciCarte();
		setChanged();
		notifyObservers("setup");
		notifyNewTurn();
	}

	/*
	 * Notifica alla view se il giocatore corrente è l'utente oppure è un bot
	 */
	public void notifyNewTurn() {
		if (indexCurrentPlayer == 0) {
			setChanged();
			//System.out.println("notificato nuovo turno");
			notifyObservers(indexCurrentPlayer);

		} else {
			PlayerController controller = controllers.get(indexCurrentPlayer);
			TurnState state = null;
			while (state != TurnState.END && state != TurnState.WIN_ROUND) {
				state = controller.playTurn(getStockPileFront(), null, -1);
				switch (state) {
				case DRAWFROMDISCARDPILE:
					state = controller.playTurn(null, stockPile.pop(), -1);
					break;
				case DRAWFROMPILE:
					state = controller.playTurn(null, mazzo.pop(), -1);
					break;
				default:
					break;
				}
			}
			//System.out.println("notifyNewTurn: stato " + state);
			evaluateTurn(state);
		}
	}

	/*
	 * Riempie il mazzo di carte
	 */
	public void riempiMazzo(int numeroGiocatori) {
		for (Carta.Seme seme : Carta.Seme.values()) {
			for (Carta.Valore valore : Carta.Valore.values()) {
				if (valore != Carta.Valore.JOLLY) {
					mazzo.push(new Carta(seme, valore));
				}
			}
		}
		mazzo.push(new Carta(Carta.Seme.CUORI, Carta.Valore.JOLLY));
		mazzo.push(new Carta(Carta.Seme.PICCHE, Carta.Valore.JOLLY));
	}

	public void creaGiocatori(int numeroGiocatori) {
		// TODO nome
		if(!players.isEmpty() && players.size() != 1) {
			players.forEach(p -> p.restart());
		} else {
			if (playerUtente == null) {
				playerUtente = new PlayerUtente("utente1");
				players.add(playerUtente);
			}
			// System.out.println("nome giocatore: " + playerUtente.getNome());
			controllers.add(new RealPlayerController(playerUtente));
			for (int i = 1; i < numeroGiocatori; i++) {
				// System.out.println("crea un bot giocatore");
				PlayerBot botPlayer = new PlayerBot();
				players.add(botPlayer);
				controllers.add(new BotController(botPlayer));
			}
		}
		// System.out.println(players);
		// System.out.println("numero giocatori: " + numeroGiocatori);
		// players.stream().map(p -> p.getNome()).forEach(System.out::println);
	}

	/*
	 * Distribuisce 10 carte coperte
	 */
	public void distribuisciCarte() {
		for (int i = 0; i < 10; i++) {
			for (Player player : players) {
				if (player.getMano().size() < player.getMaxCards()) {
					player.pescaCarta(mazzo.pop());
				}
			}
		}
	}

	/*
	 * Permette di pescare dagli scarti
	 */
	public void drawFromPile(boolean fromStockPile) {
		RealPlayerController controller = (RealPlayerController) controllers.get(indexCurrentPlayer);
		Carta card = null;
		if (fromStockPile) {
			card = stockPile.pop();
			// aggiunti dopo
			players.get(indexCurrentPlayer).setCartaPescata(card);
		} else {
			card = mazzo.pop();
			// aggiunti dopo
			players.get(indexCurrentPlayer).setCartaPescata(card);
		}
		TurnState result = controller.playTurn(null, card, -1);
		//System.out.println("drawFromPile TurnState risultato " + result);
		evaluateTurn(result);
	}

	/*
	 * Permette all'utente di scegliere dove posizionare il jolly.
	 */
	public void placeJolly(int position) {
		RealPlayerController controller = (RealPlayerController) controllers.get(indexCurrentPlayer);
		TurnState result = controller.playTurn(null, null, position);
		evaluateTurn(result);
	}

	public void evaluateTurn(TurnState result) {
		System.out.println(result);
		switch (result) {
		case JOLLY:
			setChanged();
			notifyObservers("jolly," + indexCurrentPlayer);
			break;
		case END:
			// qua, se il giocatori ha il jolly in mano lo devo scartare
			
			stockPile.push(controllers.get(indexCurrentPlayer).getLastCard());
			endTurn(false);
			break;
		case WIN_ROUND:
			if (firstWinner == Integer.MAX_VALUE) {
				firstWinner = indexCurrentPlayer;
			}
			System.out.println("Win round con ultima carta: " + controllers.get(indexCurrentPlayer).getLastCard());
			stockPile.push(controllers.get(indexCurrentPlayer).getLastCard());
			endTurn(!roundWon);
			roundWon = true;
			break;
		}
	}

	public void endTurn(boolean win) {
		setChanged();
		notifyObservers("end turn");
		indexCurrentPlayer = (indexCurrentPlayer + 1) % players.size();
		if (firstWinner == indexCurrentPlayer) {
			roundWon = false;
			lastWinners.add(firstWinner);
			firstWinner = Integer.MAX_VALUE;
			players.stream().filter(p -> p.hasFinishedRound()).forEach(p -> {
				p.winRound();
			});
			if (players.stream().filter(p -> p.getMaxCards()==0).count()!=0) {
				// TODO qui la partita finisce definitivamente
				return;
			}
			indexCurrentPlayer=0;
			setup(players.size());
			setChanged();
			notifyObservers("round won");
		} else if (win) {
			setChanged();
			notifyObservers("trash");
		}
		notifyNewTurn();
	}
	
	public void setPlayerUtente(String nome) {
		playerUtente = new PlayerUtente(nome);
		players.add(playerUtente);
	}
	
	public PlayerUtente getPlayerUtente() {
		return playerUtente;
	}
	
	public Player getCurrentPlayer() {
		// System.out.println(players);
		return players.get(indexCurrentPlayer);
	}

	public Player getPlayer(int player) {
		// System.out.println(players);
		return players.get(player);
	}

	public void setIndexCurrentPlayer(int indexCurrentPlayer) {
		this.indexCurrentPlayer = indexCurrentPlayer;
	}

	public int getIndexCurrentPlayer() {
		return indexCurrentPlayer;
	}
	
	public ArrayList<Integer> getLastWinners() {
		return lastWinners;
	}

	public static JTrash getInstance() {
		if (instance == null) {
			instance = new JTrash();
		}
		return instance;
	}

	public Carta getStockPileFront() {
		return stockPile.peek();
	}

	public Database getDatabase() {
		return database;
	}
}