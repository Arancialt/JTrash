package model;

import java.util.ArrayList;
import java.util.Random;

public class PlayerBot extends Player {
	
    private static String[] randomNomi = {"Mario Rui", "Silvio Berlusconi"};

    public PlayerBot() {
        super(getRandomNome());
    }

    public static String getRandomNome() {
		Random rnd = new Random();
		int rndNumber = rnd.nextInt(0, 1);
		return randomNomi[rndNumber];
	}
}
