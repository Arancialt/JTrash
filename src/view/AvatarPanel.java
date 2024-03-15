package view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FilenameFilter;
import java.util.ArrayList;

public class AvatarPanel extends JPanel {
    private JPanel imagePanel;
    private JLabel imageLabel;
    private JButton previousButton;
    private JButton nextButton;
    private ArrayList<File> imageFiles;
    private int currentIndex;

    public AvatarPanel() {
        // Creazione del pannello delle immagini
        imagePanel = new JPanel();
        imagePanel.setLayout(new BorderLayout());

        // Inizializzazione dell'arraylist delle immagini
        imageFiles = new ArrayList<>();
        // Specifica il percorso della cartella contenente le immagini
        File folder = new File("C:\\Users\\lafog\\eclipse-workspace\\JTrash\\res\\avatar");
        // Filtra solo i file con estensione jpg, gif, png, ecc.
        File[] files = folder.listFiles(new FilenameFilter() {
            @Override
            public boolean accept(File dir, String name) {
                return name.toLowerCase().endsWith(".jpg") ||
                       name.toLowerCase().endsWith(".jpeg") ||
                       name.toLowerCase().endsWith(".gif") ||
                       name.toLowerCase().endsWith(".png");
            }
        });
        // Aggiungi i file filtrati all'arraylist
        for (File file : files) {
            imageFiles.add(file);
        }

        // Inizializzazione dell'etichetta delle immagini con la prima immagine
        currentIndex = 0;
        ImageIcon imageIcon = new ImageIcon(imageFiles.get(currentIndex).getAbsolutePath());
        imageLabel = new JLabel(imageIcon);
        imageLabel.setSize(new Dimension(getHeight(), getWidth()));
        imagePanel.add(imageLabel, BorderLayout.CENTER);

        // Creazione del bottone per passare all'immagine precedente
        previousButton = new JButton("Indietro");
        previousButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                currentIndex = (currentIndex - 1 + imageFiles.size()) % imageFiles.size();
                ImageIcon icon = new ImageIcon(imageFiles.get(currentIndex).getAbsolutePath());
                imageLabel.setIcon(icon);
            }
        });

        // Creazione del bottone per passare all'immagine successiva
        nextButton = new JButton("Avanti");
        nextButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                currentIndex = (currentIndex + 1) % imageFiles.size();
                ImageIcon icon = new ImageIcon(imageFiles.get(currentIndex).getAbsolutePath());
                imageLabel.setIcon(icon);
            }
        });

        // Aggiunta dei componenti al frame principale
        JPanel buttonPanel = new JPanel();
        buttonPanel.add(previousButton);
        buttonPanel.add(nextButton);
        add(imagePanel, BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);
    }
}