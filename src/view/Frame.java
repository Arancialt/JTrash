package view;

import java.awt.CardLayout;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JFrame;
import javax.swing.JPanel;

import controller.JTrash;

public class Frame extends JFrame implements Observer {
	
	private static Frame instance;
	
	private CardLayout cardLayout;
	private JPanel cardPanel;
	private GameScreen gameScreen;

	private Frame() {
		
		super("JTrash");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setExtendedState(JFrame.MAXIMIZED_BOTH);
		// setLocationRelativeTo(null);
		// setSize(500,100);
		
		cardLayout = new CardLayout();
		cardPanel = new JPanel(cardLayout);
		
		TitleScreen titleScreen = new TitleScreen();
		NumeroAvversariScreen numeroAvversariScreen = new NumeroAvversariScreen(this);
		ProfileScreen profileScreen = new ProfileScreen();
		
		cardPanel.add(titleScreen, "TitleScreen");
		cardPanel.add(numeroAvversariScreen, "NumeroAvversariScreen");
		cardPanel.add(profileScreen, "ProfileScreen");
		
		cardLayout.show(cardPanel, "StartScreen");
		
		add(cardPanel);
		
		setVisible(true);
	}
	
    // Metodo pubblico per ottenere l'istanza singleton
    public static Frame getInstance() {
        // Se l'istanza non è stata creata, la crea
        if (instance == null) {
            instance = new Frame();
        }
        // Restituisce l'istanza esistente
        return instance;
    }
	
    public CardLayout getCardLayout() {
		return cardLayout;
	}

	public void setCardLayout(CardLayout cardLayout) {
		this.cardLayout = cardLayout;
	}

	public JPanel getCardPanel() {
    	return cardPanel;
    }
    
    public void setCardPanel(JPanel cardPanel) {
    	this.cardPanel = cardPanel;
    }

	@Override
	public void update(Observable o, Object arg) {
		// TODO Auto-generated method stub
		
	}

	public Observer getGameScreen() {
		// TODO Auto-generated method stub
		return gameScreen;
	}
	
	public void setPlayers(int numPlayers) {
		gameScreen = new GameScreen(numPlayers);
		cardPanel.add(gameScreen, "GameScreen");
		JTrash controller = JTrash.getInstance();
		controller.addObserver(gameScreen);
		controller.setup(numPlayers);
	}
}
