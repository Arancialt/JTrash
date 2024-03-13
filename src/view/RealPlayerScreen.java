package view;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.Image;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import controller.JTrash;
import model.Carta;
import model.Player;

public class RealPlayerScreen extends PlayerScreen {

	private static final long serialVersionUID = 1L;
	private JPanel cardsPanel;
	private JLabel hand;
	private ArrayList<JButton> cards;
	private final static int WIDTH = 120, HEIGHT = 175;
	private int player;
	private JTrash controller = JTrash.getInstance();

	/**
	 * Create the panel.
	 */
	public RealPlayerScreen(int player) {
		this.player = player;
		setBackground(new Color(0, 100, 0));
		FlowLayout mainLayout = new FlowLayout(FlowLayout.LEFT, 5, 5);
		setLayout(mainLayout);
		cardsPanel = new JPanel();
		add(cardsPanel);
		hand = new JLabel();
		add(hand);
		cardsPanel.setLayout(new GridLayout(2, 5));
		cardsPanel.setBackground(new Color(100, 100, 100));
		cards = new ArrayList<>();
		for (int i = 0; i < 10; i++) {
			Image icon = new ImageIcon("res\\cards\\hidden_card.png").getImage().getScaledInstance(60, 80,
					Image.SCALE_DEFAULT);
			JButton label = new JButton(new ImageIcon(icon));
			cardsPanel.add(label);
			cards.add(label);
			final int p = i;
			cards.get(i).addActionListener(e -> {
				if (controller.getPlayer(this.player).getMano().get(p).getIsCoperta()) {
					controller.placeJolly(p);
				}
			});
		}
	}

	public void setActive() {
		setBackground(new Color(100, 100, 100));
	}

	@Override
	public void update() {
		JTrash controller = JTrash.getInstance();
		Player player = controller.getPlayer(this.player);
		System.out.println(player.getMaxCards() + " " + cards.size());
		while (player.getMaxCards() < cards.size()) {
			JButton removed = cards.remove(cards.size()-1);
			removed.setVisible(false);
		}
		hand.setIcon(null);
		var terra = player.getMano();
		for (int i = 0; i < terra.size(); i++) {
			if (terra.get(i).getIsCoperta()) {
				Image icon = new ImageIcon("res\\cards\\hidden_card.png").getImage().getScaledInstance(60, 80,
						Image.SCALE_DEFAULT);
				cards.get(i).setIcon(new ImageIcon(icon));
			} else {
				Image icon = new ImageIcon("res\\cards\\" + terra.get(i) + ".png").getImage().getScaledInstance(60, 80,
						Image.SCALE_DEFAULT);
				cards.get(i).setIcon(new ImageIcon(icon));
			}
		}
	}

	public void setJolly(int playerIndex) {
		// aggiunto dopo: differenziato joker dal king 
		update();
		Player player = controller.getPlayer(this.player);
		System.out.println(player.getCartaPescata());
		
		Image icon = null;
		if (controller.getCurrentPlayer().getCartaPescata().getValore().equals(Carta.Valore.KING)) {
			icon = new ImageIcon("res\\cards\\KING_di_Cuori.png").getImage().getScaledInstance(120, 160,
					Image.SCALE_DEFAULT);
		} else {
			icon = new ImageIcon("res\\cards\\JOLLY_di_Cuori.png").getImage().getScaledInstance(120, 160,
					Image.SCALE_DEFAULT);
		}

		hand.setIcon(new ImageIcon(icon));
	}
}
