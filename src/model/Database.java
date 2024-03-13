package model;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/*
 * fai un csv con queste colonne
 * nome,partite giocate,partite vinte,partite perse
 */

public class Database {
	private ArrayList<DatabaseEntry> players;

	public Database() {
		players = new ArrayList<DatabaseEntry>();
		try (BufferedReader br = new BufferedReader(new FileReader("res/database.csv"))) {
			String line;
			while ((line = br.readLine()) != null) {
				players.add(new DatabaseEntry(line));
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public DatabaseEntry addPlayer(String name) {
		if (players.stream().filter(player -> player.name.equals(name)).findAny().isPresent()) {
			return null;
		}
		var result = DatabaseEntry.empty(name);
		players.add(result);

		FileWriter fileWriter;
		try {
			fileWriter = new FileWriter("res/database.csv", true);
			fileWriter.append(result.toString() + "\n");
			fileWriter.flush();
			fileWriter.close();
		} catch (IOException e) {
			// TODO gestire il caso in cui non si riesce a scrivere sul file
			e.printStackTrace();
		}

		return result;
	}

	public List<DatabaseEntry> getEntries() {
		return players;
	}

	/*
	 * Salva un giocatore su File
	 */
	public void salvaSuFile(PlayerUtente player) throws IOException {
		FileOutputStream fos = new FileOutputStream("\"C:\\Users\\lafog\\OneDrive\\Desktop\\giocatori");
		ObjectOutputStream oos = new ObjectOutputStream(fos);

		oos.writeObject(player);

		oos.close();
		fos.close();
		System.out.println("Giocatore salvato su " + fos);
	}

	public void updateEntry(String name, boolean win) {
		players.stream().filter(player -> player.name.equals(name)).forEach(player -> {
			player.played += 1;
			if (win)
				player.won += 1;
			else
				player.lost += 1;
		});
		FileWriter fileWriter;
		try {
			fileWriter = new FileWriter("res/database.csv", false);
			for (var entry : players) {
				fileWriter.append(entry.toString() + "\n");
				fileWriter.flush();
				fileWriter.close();
			}
		} catch (IOException e) {
			// TODO gestire il caso in cui non si riesce a scrivere sul file
			e.printStackTrace();
		}
	}

	/*
	 * public void caricaDaFile(File file) throws IOException { FileInputStream fis
	 * = new FileInputStream(file); ObjectInputStream ois = new
	 * ObjectInputStream(fis);
	 * 
	 * try { PlayerUtente[] utentiCaricati = (PlayerUtente[]) ois.readObject();
	 * 
	 * players.clear();
	 * 
	 * players.addAll(Arrays.asList(utentiCaricati));
	 * 
	 * } catch (ClassNotFoundException | IOException e) { e.printStackTrace(); }
	 * 
	 * ois.close(); fis.close(); }
	 */
}
