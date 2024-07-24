package Mapas;

import javax.swing.*;

import Mapas.MapaVerde2.Scene;

import java.awt.*;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class MapaArido2 extends JFrame {
    private JLabel imageLabel;
    private JPanel buttonPanel;
    private List<Scene> scenes;
    private int currentIndex = 0;

    public MapaArido2() {
        setTitle("A Lenda de Eldara");
        setSize(500, 500);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        imageLabel = new JLabel();
        imageLabel.setHorizontalAlignment(JLabel.CENTER); // Alinha a imagem no centro do JLabel
        imageLabel.setVerticalAlignment(JLabel.CENTER);
        add(imageLabel, BorderLayout.CENTER);

        buttonPanel = new JPanel();
        add(buttonPanel, BorderLayout.SOUTH);

        initializeScenes();
        updateScene(currentIndex);
    }

    private void initializeScenes() {
        scenes = new ArrayList<>();

        scenes.add(new Scene(
            "src/Fotos/A7.png",
            new String[]{"Voltar", "Avançar"},
            new ActionListener[]{
                e -> JOptionPane.showMessageDialog(null, "Não podemos voltar, Pode ter mais deles", "Aviso", JOptionPane.WARNING_MESSAGE),
                e -> updateScene(1)
            }
        ));
        
        
     // Cena 1
        scenes.add(new Scene(
            "src/Fotos/A8.png",
            new String[]{"Voltar", "Avançar"},
            new ActionListener[]{
                e -> updateScene(0),
                e -> updateScene(2)
            }
        ));
        
     // Cena 2
        scenes.add(new Scene(
            "src/Fotos/A9.png",
            new String[]{"Voltar", "Avançar"},
            new ActionListener[]{
                e -> updateScene(1),
                e -> updateScene(3)
            }
        ));
        
     // Cena 3
        scenes.add(new Scene(
            "src/Fotos/A10.png",
            new String[]{"Voltar", "Avançar"},
            new ActionListener[]{
                e -> updateScene(2),
                
                e -> updateScene(4)
            }
        ));
        
     // Cena 4
        scenes.add(new Scene(
            "src/Fotos/A11.png",
            new String[]{"Voltar", "Avançar"},
            new ActionListener[]{
                e -> updateScene(3),
                e -> updateScene(5)
            }
        ));
        
     // Cena 5
        scenes.add(new Scene(
            "src/Fotos/A12.png",
            new String[]{"Voltar", "Avançar"},
            new ActionListener[]{
                e -> updateScene(4),
                e -> { 
                    EncontrosArido.BatalhaFinal(); 
                    dispose();
                    
                },
            }
        ));
        
    }

    private void updateScene(int index) {
        if (index >= 0 && index < scenes.size()) {
            Scene scene = scenes.get(index);
            imageLabel.setIcon(scene.getScaledIcon(500, 500)); // Adapta a imagem ao tamanho do frame
            buttonPanel.removeAll();
            for (JButton button : scene.getButtons()) {
                buttonPanel.add(button);
            }
            buttonPanel.revalidate();
            buttonPanel.repaint();
            currentIndex = index;
        }
    }

    public static void main(String[] args) {
    SwingUtilities.invokeLater(() -> new MapaArido2().setVisible(true));
    }

    class Scene {
        private String imagePath;
        private JButton[] buttons;

        public Scene(String imagePath, String[] buttonLabels, ActionListener[] actions) {
            this.imagePath = imagePath;
            this.buttons = new JButton[buttonLabels.length];
            for (int i = 0; i < buttonLabels.length; i++) {
                buttons[i] = new JButton(buttonLabels[i]);
                if (i < actions.length) {
                    buttons[i].addActionListener(actions[i]);
                }
            }
        }

        public ImageIcon getScaledIcon(int width, int height) {
            ImageIcon icon = new ImageIcon(imagePath);
            Image image = icon.getImage().getScaledInstance(width, height, Image.SCALE_SMOOTH);
            return new ImageIcon(image);
        }

        public JButton[] getButtons() {
            return buttons;
        }
    }
}