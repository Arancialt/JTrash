package model;

import javax.swing.ImageIcon;

public class Carta {
	
	public enum Seme {
		FIORI("Fiori"),PICCHE("Picche"),QUADRI("Quadri"),CUORI("Cuori");
		
		private String seme;
		
		Seme(String seme) {this.seme = seme;}
		
		public String toString() {return seme;}
	}
	
	public enum Valore {
		JOLLY(0),ASSO(1),DUE(2),TRE(3),QUATTRO(4),CINQUE(5),SEI(6),SETTE(7),OTTO(8),NOVE(9),DIECI(10),JACK(11),QUEEN(12),KING(13);
		
		private int valore;
		
		Valore(int valore) {this.valore = valore;}
		
		public int getValore() {return valore;}
	}
	
	private Seme seme;
	private Valore valore;
	private boolean isCoperta;

	public Carta(Seme seme, Valore valore) {
		this.seme = seme;
		this.valore = valore;
		isCoperta = true;
	}
	
	public ImageIcon getImmagineCarta() {
		return new ImageIcon(toString() + ".png");
	}
	
	
	public Seme getSeme() {return seme;}
	public Valore getValore() {return valore;}
	public boolean getIsCoperta() {return isCoperta;}
	
	public void setSeme(Seme seme) {this.seme = seme;}
	public void setValore(Valore valore) {this.valore = valore;}
	public void setIsCoperta(boolean isCoperta) {this.isCoperta = isCoperta;}
	
	@Override
	public String toString() {
		return valore + "_di_" + seme;
	}

	public String getImagePath() {
		String baseImagePath = "res/cards/";
		if(isCoperta) {
			return baseImagePath + "hidden_card.png";
		} else {
			return baseImagePath + toString() + ".png";
		}
	}
}
