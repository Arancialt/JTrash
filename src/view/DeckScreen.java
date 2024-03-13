package view;

import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JPanel;
import javax.swing.JSplitPane;

import controller.JTrash;
import model.Carta;

import java.awt.FlowLayout;
import java.awt.Image;
import java.util.Observable;
import java.util.Observer;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.ImageIcon;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class DeckScreen extends JPanel {

	private static final long serialVersionUID = 1L;
	private JButton stockPile, drawDeck;
	private final static int WIDTH = 120, HEIGHT = 175;

	public DeckScreen() {
		setBackground(new Color(0, 100, 0));
		setLayout(new FlowLayout(FlowLayout.LEFT, 5, 5));

		JSplitPane splitPane = new JSplitPane();
		add(splitPane);

		stockPile = new JButton();
		// Image image = new
		// ImageIcon("res\\cards\\background.png").getImage().getScaledInstance(WIDTH,
		// HEIGHT,Image.SCALE_DEFAULT);
		// stockPile.setIcon(new ImageIcon(image));
		// TODO background.png deve avere le stesse dimensioni di hidden_card.png
		// e deve essere tutta verde (0,100,0)
		splitPane.setLeftComponent(stockPile);

		drawDeck = new JButton();
		Image image = new ImageIcon("res\\cards\\hidden_card.png").getImage().getScaledInstance(WIDTH, HEIGHT,
				Image.SCALE_DEFAULT);
		drawDeck.setIcon(new ImageIcon(image));
		splitPane.setRightComponent(drawDeck);

		stockPile.addActionListener(e -> {
			JTrash controller = JTrash.getInstance();
			if (controller.getIndexCurrentPlayer() == 0) {
				controller.drawFromPile(true);
			}

		});
		drawDeck.addActionListener(e -> {
			JTrash controller = JTrash.getInstance();
			if (controller.getIndexCurrentPlayer() == 0) {
				controller.drawFromPile(false);
			}
		});
	}

	/*
	 * Aggiorna il mazzo di scarto con la prima carta
	 */
	public void updateStockPile() {
		// TODO forse qui si deve fare la cosa che la carta scoperta si colora in base alle
		// carte a terra del giocatore (solo se tocca al giocatore 0)
		JTrash controller = JTrash.getInstance();
		Carta card = controller.getStockPileFront();
		Image image = new ImageIcon("res\\cards\\" + card.toString() + ".png").getImage().getScaledInstance(WIDTH,
				HEIGHT, Image.SCALE_DEFAULT);
		stockPile.setIcon(new ImageIcon(image));
	}
}
