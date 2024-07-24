package Mapas;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class MapaVerde extends JFrame {
    private JLabel imageLabel;
    private JPanel buttonPanel;
    private List<Scene> scenes;
    private int currentIndex = 0;

    public MapaVerde() {
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

    private void initializeScenes() {
        scenes = new ArrayList<>();
        
        // Cena 1
        scenes.add(new Scene(
            "src/Fotos/1.png",
            new String[]{"Iniciar Aventura"},
            new ActionListener[]{
                e -> updateScene(11)
            }
        ));

        // Cena 2
        scenes.add(new Scene(
            "src/Fotos/2.png",
            new String[]{"Voltar", "Avançar"},
            new ActionListener[]{
                e -> updateScene(0),
                e -> updateScene(2)
            }
        ));

        // Cena 3
        scenes.add(new Scene(
            "src/Fotos/3.png",
            new String[]{"Voltar", "Falar com o Velho", "Avançar"},
            new ActionListener[]{
                e -> updateScene(11),
                e -> { 
                    Encontros.encontroComVelho(); 
                    updateScene(2); 
                },
                e -> updateScene(3)
            }
        ));


     // Cena 4
        scenes.add(new Scene(
            "src/Fotos/4.png",
            new String[]{"Voltar", "Avançar"},
            new ActionListener[]{
                e -> {
                    JOptionPane.showMessageDialog(null, "não ha tempo a perder, deixe de perder tempo com aventuras inúteis, Avante!", "Aviso", JOptionPane.WARNING_MESSAGE);
                },
                e -> updateScene(4)
            }
        ));


        // Cena 5
        scenes.add(new Scene(
            "src/Fotos/5.png",
            new String[]{"Voltar", "Avançar"},
            new ActionListener[]{
                e -> updateScene(3),
                e -> updateScene(5)
            }
        ));
        
     // Cena 6
        scenes.add(new Scene(
            "src/Fotos/6.png",
            new String[]{"Voltar", "Avançar"},
            new ActionListener[]{
                e -> updateScene(4),
                e -> updateScene(6)
            }
        ));
        
     // Cena 7
        scenes.add(new Scene(
            "src/Fotos/7.png",
            new String[]{"Voltar", "Avançar"},
            new ActionListener[]{
                e -> updateScene(5),
                e -> updateScene(7)
            }
        ));
        
     // Cena 8
        scenes.add(new Scene(
            "src/Fotos/8.png",
            new String[]{"Voltar", "Avançar"},
            new ActionListener[]{
                e -> updateScene(6),
                e -> updateScene(8)
            }
        ));
        
     // Cena 9
        scenes.add(new Scene(
            "src/Fotos/9.png",
            new String[]{"Voltar", "Falar com os Aldeões", "Avançar"},
            new ActionListener[]{
                e -> updateScene(7),
                e -> { 
                    Encontros.encontroComAldeoes(); 
                    updateScene(8); 
                },
                e -> updateScene(9)
            }
        ));
        
     // Cena 10
        scenes.add(new Scene(
            "src/Fotos/10.png",
            new String[]{"Voltar", "Avançar"},
            new ActionListener[]{
                e -> updateScene(8),
                e -> updateScene(10)
            }
        ));
        
     // Cena 11 - aqui o avançar dará para a função de luta 1
        scenes.add(new Scene(
            "src/Fotos/11.png",
            new String[]{"Voltar", "Avançar"},
            new ActionListener[]{
                e -> updateScene(9),
                e -> {
                    Encontros.encontroComGuardas();
                    dispose(); // Fecha o MapaVerde atual
                }
            }
        ));

     // Cena Z1 1 a 12
        scenes.add(new Scene(
            "src/Fotos/Z1.png",
            new String[]{"Voltar", "Atalho", "Caminho da Sabedoria"},
            new ActionListener[]{
                e -> updateScene(0),
                e -> updateScene(2),
                e -> updateScene(12)
            }
        ));
        
     // Cena Z2 2 a 13
        scenes.add(new Scene(
            "src/Fotos/Z2.png",
            new String[]{"Voltar", "Direita", "Esquerda"},
            new ActionListener[]{
                e -> updateScene(11),
                e -> updateScene(13),
                e -> updateScene(17)
            }
        ));
        
     // Cena Z3 3 a 14
        scenes.add(new Scene(
            "src/Fotos/Z3.png",
            new String[]{"Voltar", "Direita", "Esquerda"},
            new ActionListener[]{
                e -> updateScene(12),
                e -> updateScene(14),
                e -> updateScene(20)
            }
        ));
        
        // Cena 4 a 15
        scenes.add(new Scene(
            "src/Fotos/Z4.png",
            new String[]{"Voltar", "Direita", "Esquerda"},
            new ActionListener[]{
                e -> updateScene(13),
                e -> updateScene(15),
                e -> updateScene(22)
            }
        ));
        
     // Cena 5 a 16
        scenes.add(new Scene(
            "src/Fotos/Z5.png",
            new String[]{"Voltar", "Avançar"},
            new ActionListener[]{
                e -> updateScene(14),
                e -> updateScene(16),
                
            }
        ));
        
     // Cena 6 a 17
        scenes.add(new Scene(
            "src/Fotos/Z6.png",
            new String[]{"Voltar", "Avançar"},
            new ActionListener[]{
                e -> updateScene(15),
                e -> updateScene(26),
                
            }
        ));
        
     // Cena 18 d 18
        scenes.add(new Scene(
            "src/Fotos/Z7.png",
            new String[]{"Voltar", "Avançar"},
            new ActionListener[]{
                e -> updateScene(12),
                e -> updateScene(18),
                
            }
        ));
        
     // Cena 19 d
        scenes.add(new Scene(
            "src/Fotos/Z8.png",
            new String[]{"Voltar", "Avançar"},
            new ActionListener[]{
                e -> updateScene(17),
                e -> updateScene(19),
                
            }
        ));
        
     // Cena 20 d
        scenes.add(new Scene(
            "src/Fotos/Z9.png",
            new String[]{"Voltar", "Avançar"},
            new ActionListener[]{
                e -> updateScene(18),
                e -> updateScene(23),
                
            }
        ));
        
     // Cena 21 c 
        scenes.add(new Scene(
            "src/Fotos/Z8.png",
            new String[]{"Voltar", "Avançar"},
            new ActionListener[]{
                e -> updateScene(13),
                e -> updateScene(21),
                
            }
        ));
        
     // Cena 22
        scenes.add(new Scene(
            "src/Fotos/Z9.png",
            new String[]{"Voltar", "Avançar"},
            new ActionListener[]{
                e -> updateScene(20),
                e -> updateScene(24),
                
            }
        ));
        
     // Cena 23
        scenes.add(new Scene(
            "src/Fotos/Z9.png",
            new String[]{"Voltar", "Avançar"},
            new ActionListener[]{
                e -> updateScene(14),
                e -> updateScene(25),
                
            }
        ));
        
     // Cena 24 - Mago 1 d
        scenes.add(new Scene(
                "src/Fotos/3.png",
                new String[]{"Voltar", "Falar com o Velho", "Avançar"},
                new ActionListener[]{
                    e -> updateScene(19),
                    e -> { 
                        Encontros.encontroComVelho(); 
                        
                    },
                    e -> updateScene(3)
                }
            ));
        
     // Cena 25 - Mago 2 c
        scenes.add(new Scene(
                "src/Fotos/3.png",
                new String[]{"Voltar", "Falar com o Velho", "Avançar"},
                new ActionListener[]{
                    e -> updateScene(21),
                    e -> { 
                        Encontros.encontroComVelho(); 
                         
                    },
                    e -> updateScene(3)
                }
            ));
        
     // Cena 26 - Mago 3 b
        scenes.add(new Scene(
                "src/Fotos/3.png",
                new String[]{"Voltar", "Falar com o Velho", "Avançar"},
                new ActionListener[]{
                    e -> updateScene(22),
                    e -> { 
                        Encontros.encontroComVelho(); 
                         
                    },
                    e -> updateScene(3)
                }
            ));
     // Cena 27 - Mago 3 a
        scenes.add(new Scene(
                "src/Fotos/3.png",
                new String[]{"Voltar", "Falar com o Velho", "Avançar"},
                new ActionListener[]{
                    e -> updateScene(16),
                    e -> { 
                        Encontros.encontroComVelho(); 
                         
                    },
                    e -> updateScene(3)
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
    SwingUtilities.invokeLater(() -> new MapaVerde().setVisible(true));
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
