package Mapas;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.List;

public class inicio {

    private JFrame frame;
    private JLabel imageLabel;
    private List<ImageIcon> images;
    private int imageIndex;

    public inicio() {
        images = new ArrayList<>();
        // Adicionando a extensão das imagens e corrigindo o caminho
        images.add(new ImageIcon("src/Fotos/M1.png"));
        images.add(new ImageIcon("src/Fotos/M2.png"));
        images.add(new ImageIcon("src/Fotos/M3.png"));
        images.add(new ImageIcon("src/Fotos/M4.png"));
        images.add(new ImageIcon("src/Fotos/M5.png"));
        imageIndex = 0;

        createAndShowGUI();
    }

    private void createAndShowGUI() {
        frame = new JFrame("Início da História");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(300, 200);
        

        imageLabel = new JLabel(images.get(0), JLabel.CENTER);
        frame.setLayout(new BorderLayout());  // Define o layout do frame para BorderLayout
        frame.add(imageLabel, BorderLayout.CENTER);  // Adiciona o imageLabel ao centro do frame

        frame.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                advanceImage();
            }
        });

        frame.pack();  // Ajusta o tamanho do frame baseado nos componentes
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
            iniciarMundo(); // Chama o método para iniciar o mundo ASCII
        }
    }

    private void iniciarMundo() {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new MapaVerde().setVisible(true); // Inicia a próxima janela do jogo
            }
        });
    }

    public static void main(String[] args) {
        new inicio(); // Adicionando um método main para iniciar o programa
    }
}
