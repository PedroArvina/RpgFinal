package Mapas;

import javax.swing.*;

import Mapas.MapaVerde2.Scene;

import java.awt.*;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class MapaVerde3 extends JFrame {
    private JLabel imageLabel;
    private JPanel buttonPanel;
    private List<Scene> scenes;
    private int currentIndex = 0;

    public MapaVerde3() {
        setTitle("RPG Sequencial");
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

    public void enableButtons(boolean enable) {
        for (Scene scene : scenes) {
            for (JButton button : scene.getButtons()) {
                button.setEnabled(enable);
            }
        }
    }

    private void initializeScenes() {
        scenes = new ArrayList<>();
        

        
        
        
        scenes.add(new Scene(
        	    "src/Fotos/13.png",
        	    new String[]{"Voltar", "Falar com a dama", "Avançar"},
        	    new ActionListener[]{
        	        e -> {
        	            updateScene(4);
        	        },
        	        e -> {
        	            Encontros.encontroComDama(this); // 'this' refere-se ao MapaVerde2
        	        },
        	        e -> updateScene(1)
        	    }
        	));


        scenes.add(new Scene(
            "src/Fotos/14.png",
            new String[]{"Voltar", "Avançar"},
            new ActionListener[]{
                e -> updateScene(0),
                e -> updateScene(2)
            }
        ));

        scenes.add(new Scene(
            "src/Fotos/15.png",
            new String[]{"Voltar", "Avançar"},
            new ActionListener[]{
                e -> updateScene(1),
                e -> updateScene(3)
            }
        ));

        scenes.add(new Scene(
        	    "src/Fotos/16.png",
        	    new String[]{"Voltar", "Falar com o Rei"},
        	    new ActionListener[]{
        	        e -> updateScene(2),
        	        e -> Encontros.encontroComRei(this, () -> {
        	            dispose(); // Fecha o MapaVerde atual
        	        })
        	    }
        	));
        
        scenes.add(new Scene(
                "src/Fotos/12.png",
                new String[]{"Voltar", "Avançar"},
                new ActionListener[]{
                    e -> JOptionPane.showMessageDialog(null, "Não podemos voltar, podem ter aparecido mais guardas, nosso tempo é curto", "Aviso", JOptionPane.WARNING_MESSAGE),
                    e -> updateScene(0)
                }
            ));

    }

    private void updateScene(int index) {
        if (index >= 0 && index < scenes.size()) {
            Scene scene = scenes.get(index);
            imageLabel.setIcon(scene.getScaledIcon(500, 500));
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
        SwingUtilities.invokeLater(() -> new MapaVerde3().setVisible(true));
    }

    class Scene {
        private String imagePath;
        private JButton[] buttons;

        public Scene(String imagePath, String[] buttonLabels, ActionListener[] actions) {
            this.imagePath = imagePath;
            this.buttons = new JButton[buttonLabels.length];
            for (int i = 0; i < buttonLabels.length; i++) {
                buttons[i] = new JButton(buttonLabels[i]);
                buttons[i].addActionListener(actions[i]);
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
