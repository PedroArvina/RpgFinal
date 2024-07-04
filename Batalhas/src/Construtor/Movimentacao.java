package Construtor;

import javax.swing.*;
import java.awt.*;

public class Movimentacao {
    private static final int ALTURA = 8;
    private static final int LARGURA = 15;
    private JPanel[][] quadrados;

    public Movimentacao(JPanel[][] quadrados) {
        this.quadrados = quadrados;
    }

    // Método para mover um personagem para uma nova posição
    public void moverPersonagem(int origemX, int origemY, int destinoX, int destinoY, String simbolo) {
        if (estaDentroDosLimites(origemX, origemY) && estaDentroDosLimites(destinoX, destinoY)) {
            // Limpar a posição de origem
            quadrados[origemX][origemY].removeAll();
            quadrados[origemX][origemY].revalidate();
            quadrados[origemX][origemY].repaint();

            // Adicionar o símbolo à posição de destino
            JLabel label = new JLabel(simbolo, SwingConstants.CENTER);
            label.setFont(new Font("Arial", Font.BOLD, 18));
            quadrados[destinoX][destinoY].add(label);
            quadrados[destinoX][destinoY].revalidate();
            quadrados[destinoX][destinoY].repaint();
        } else {
            System.out.println("Movimento fora dos limites do tabuleiro!");
        }
    }

    // Verifica se uma posição está dentro dos limites do tabuleiro
    private boolean estaDentroDosLimites(int x, int y) {
        return x >= 0 && x < ALTURA && y >= 0 && y < LARGURA;
    }
}
