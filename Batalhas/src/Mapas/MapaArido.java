package Mapas;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class MapaArido extends JFrame {
    private JLabel imageLabel;
    private JPanel buttonPanel;
    private List<Scene> scenes;
    private int currentIndex = 0;

    public MapaArido() {
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
        
        // Cena 1
        scenes.add(new Scene(
            "src/Fotos/A1.png",
            new String[]{"Avante"},
            new ActionListener[]{
                e -> updateScene(1)
            }
        ));

        // Cena 2
        scenes.add(new Scene(
            "src/Fotos/A2.png",
            new String[]{"Voltar", "Avançar"},
            new ActionListener[]{
                e -> updateScene(0),
                e -> updateScene(2)
            }
        ));

        // Cena 3
        scenes.add(new Scene(
            "src/Fotos/A3.png",
            new String[]{"Voltar", "Falar com o Golem", "Avançar"},
            new ActionListener[]{
                e -> updateScene(1),
                e -> { 
                    EncontrosArido.encontroComGolem(); 
                    updateScene(2); 
                },
                e -> updateScene(3)
            }
        ));


        // Cena 4
        scenes.add(new Scene(
            "src/Fotos/A4.png",
            new String[]{"Voltar", "Avançar"},
            new ActionListener[]{
                e -> updateScene(2),
                e -> updateScene(4)
            }
        ));

        // Cena 5
        scenes.add(new Scene(
            "src/Fotos/A5.png",
            new String[]{"Voltar", "Avançar"},
            new ActionListener[]{
                e -> updateScene(3),
                e -> updateScene(5)
            }
        ));
        
     // Cena 6 Desafio Ladrão
        scenes.add(new Scene(
                "src/Fotos/A6.png",
                new String[]{"Voltar", "Avançar"},
                new ActionListener[]{
                    e -> updateScene(4),
                    e -> { 
                        EncontrosArido.encontroNoDeserto(); 
                        updateScene(6); 
                    },
                }
            ));
        
     // Cena 7
        scenes.add(new Scene(
            "src/Fotos/A7.png",
            new String[]{"Voltar", "Avançar"},
            new ActionListener[]{
                e -> updateScene(5),
                e -> updateScene(7)
            }
        ));
        
     // Cena 8
        scenes.add(new Scene(
            "src/Fotos/A8.png",
            new String[]{"Voltar", "Avançar"},
            new ActionListener[]{
                e -> updateScene(6),
                e -> updateScene(8)
            }
        ));
        
     // Cena 9
        scenes.add(new Scene(
            "src/Fotos/A9.png",
            new String[]{"Voltar", "Avançar"},
            new ActionListener[]{
                e -> updateScene(7),
                
                e -> updateScene(9)
            }
        ));
        
     // Cena 10
        scenes.add(new Scene(
            "src/Fotos/A10.png",
            new String[]{"Voltar", "Avançar"},
            new ActionListener[]{
                e -> updateScene(8),
                e -> updateScene(10)
            }
        ));
        
     // Cena 11 - aqui o avançar dará para a função de luta 1
        scenes.add(new Scene(
            "src/Fotos/A11.png",
            new String[]{"Voltar", "Avançar"},
            new ActionListener[]{
                e -> updateScene(9),
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
    SwingUtilities.invokeLater(() -> new MapaArido().setVisible(true));
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