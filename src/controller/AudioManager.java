package controller;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import java.io.File;
import java.io.IOException;

public class AudioManager {
	
	private static AudioManager instance;
	
	private AudioManager() {
		
	}
	
	public static AudioManager getInstance() {
		if (instance == null) {
			return new AudioManager();
		}
		return instance;
	}
	
    public void runMusic(String filePath) {

        // Crea un oggetto AudioInputStream per il file audio
        try (AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(new File(filePath))) {

            // Ottieni un oggetto Clip
            Clip clip = AudioSystem.getClip();

            // Apri il clip con l'AudioInputStream
            clip.open(audioInputStream);

            // Avvia la riproduzione del clip
            clip.start();

            // Attendi fino alla fine della riproduzione prima di uscire
            while (!clip.isRunning())
                Thread.sleep(10);
            while (clip.isRunning())
                Thread.sleep(10);

            // Chiudi il clip dopo la riproduzione
            clip.close();

        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException | InterruptedException e) {
            e.printStackTrace();
        }
    }
}
