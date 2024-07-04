package Mapas;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.List;

public class Inter {

    private JFrame frame;
    private JLabel imageLabel;
    private List<ImageIcon> images;
    private int imageIndex;

    public Inter() {
        images = new ArrayList<>();
        // Adicionando imagens ao array
        images.add(new ImageIcon("src/Fotos/M7.png"));
        images.add(new ImageIcon("src/Fotos/M6.png"));
        
        imageIndex = 0;

        createAndShowGUI();
    }

    private void createAndShowGUI() {
        frame = new JFrame("Início da História");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(300, 200);

        imageLabel = new JLabel(images.get(0), JLabel.CENTER);
        frame.setLayout(new BorderLayout()); // Define o layout do frame para BorderLayout
        frame.add(imageLabel, BorderLayout.CENTER); // Adiciona o imageLabel ao centro do frame

        frame.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                advanceImage();
            }
        });

        frame.pack(); // Ajusta o tamanho do frame baseado nos componentes
        frame.setVisible(true);
        frame.requestFocusInWindow(); // Necessário para capturar eventos de teclado
    }

    private void advanceImage() {
        imageIndex++;
        if (imageIndex < images.size()) {
            imageLabel.setIcon(images.get(imageIndex));
            frame.repaint(); // Repinta o frame para mostrar a nova imagem
        } else {
            frame.dispose(); // Fecha a janela de introdução
            iniciarMundo(); // Chama o método para iniciar o mundo do jogo
        }
    }

    private void iniciarMundo() {
        // Inicia a próxima janela do jogo
        JFrame mapaVerde = new MapaArido();
        mapaVerde.setVisible(true);
    }

    public static void main(String[] args) {
        new Inter(); // Inicia o programa
    }
}
