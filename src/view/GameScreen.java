package view;

import javax.swing.JPanel;

import controller.JTrash;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;
import java.util.stream.Collectors;

public class GameScreen extends JPanel implements Observer {

	private static final long serialVersionUID = 1L;
	private DeckScreen deck;
	private ArrayList<PlayerScreen> players;
	private JTrash controller = JTrash.getInstance();

	/**
	 * Create the panel.
	 */
	public GameScreen(int numPlayers) {
		GridLayout grid = new GridLayout(3, 3, 0, 0);
		deck = new DeckScreen();
		players = new ArrayList<>();
		for (int i = 0; i < numPlayers; i++) {
			players.add(new RealPlayerScreen(i));
		}
		for (int i = 0; i < 4 - numPlayers; i++) {
			players.add(new EmptyPlayerScreen());
		}

		setLayout(grid);
		add("empty1", new EmptyPlayerScreen());
		add("Player1", players.get(0));
		add("empty2", new EmptyPlayerScreen());

		add("Player4", players.get(3));
		add("deck", deck);
		add("Player2", players.get(1));

		add("empty3", new EmptyPlayerScreen());
		add("Player3", players.get(2));
		add("empty4", new EmptyPlayerScreen());
	}

	@Override
	public void update(Observable o, Object arg) {
		// System.out.println("ricevuto update "+ arg);
		if (arg.equals("setup")) {// "setup"
			deck.updateStockPile();
		} else if (arg.getClass() == String.class && ((String) arg).startsWith("jolly,")) {// "jolly,{playerIndex}"
			// TODO va differenziato king dal jolly
			String sArg = (String) arg;
			int player = Integer.parseInt(sArg.split(",")[1]);
			players.get(player).setJolly(player);
		} else if (arg.equals("end turn")) { // "end turn"
			players.get(JTrash.getInstance().getIndexCurrentPlayer()).update();
			deck.updateStockPile();
		} else if (arg.equals("trash")) {
			trash();
		} else if (arg.equals("round won")) {
			for (var player : players) {
				player.update();
			}
			roundWon();
		} else if (arg.equals("restart")) {
			for (var player : players) {
				player.update();
			}
			restart();
		}
	}

	/*
	 * Crea una finestra in pop-up nel momento in cui un giocatore vince il turno,
	 * con scritto TRASH!
	 */
	private void trash() {
		JLabel label = new JLabel("TRASH!");
		JFrame frame = new JFrame();
		frame.add(label);
		frame.setSize(150, 150);
		frame.setVisible(true);
	}

	private void roundWon() {
		String winners = controller.getLastWinners().stream().map(p -> controller.getPlayer(p).getNome())
				.collect(Collectors.joining());
		JLabel label = new JLabel(
				"Il round è stato vinto da: " + winners);
		JFrame frame = new JFrame();
		frame.setBackground(new Color(0, 100, 0));
		frame.add(label, BorderLayout.NORTH);
		frame.setSize(150, 150);
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
	}

	/*
	 * Aggiunge una finestra pop-up in cui si dichiara il vincitore e che permette
	 * di far ripartire il gioco oppure tornare al menu principale
	 */
	private void restart() {
		String winners = controller.getLastWinners().stream().map(p -> controller.getPlayer(p).getNome())
				.collect(Collectors.joining());
		// TODO verificare che funzioni (anche roundWon)
		JLabel label = new JLabel("La partita è stata vinta da: " + winners);
		JButton exitButton = new JButton("Esci");
		JFrame frame = new JFrame();
		frame.setBackground(new Color(0, 100, 0));
		frame.add(exitButton, BorderLayout.SOUTH);
		frame.add(label, BorderLayout.NORTH);
		frame.setSize(150, 150);
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
	}
}
