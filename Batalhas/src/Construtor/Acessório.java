package Construtor;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class Acessório extends JPanel {
    private JLabel magoVida;
    private JLabel guerreiroVida;
    private JLabel anaoVida;
    private JLabel pugVida;
    private JLabel lugVida;
    private JLabel dugVida;
    private JLabel turnoAtualLabel;
    private JButton mudarTurnoButton;

    public Acessório(ActionListener mudarTurnoListener) {
        setLayout(new BorderLayout());

        JLabel titulo = new JLabel("Painel de Acessórios", SwingConstants.CENTER);
        titulo.setFont(new Font("Arial", Font.BOLD, 16));
        add(titulo, BorderLayout.NORTH);

        // Painel de status dos personagens
        JPanel statusPanel = new JPanel(new GridLayout(6, 2));
        statusPanel.add(new JLabel("Mago HP: "));
        magoVida = new JLabel("50");
        statusPanel.add(magoVida);
        statusPanel.add(new JLabel("Guerreiro HP: "));
        guerreiroVida = new JLabel("70");
        statusPanel.add(guerreiroVida);
        statusPanel.add(new JLabel("Anão HP: "));
        anaoVida = new JLabel("60");
        statusPanel.add(anaoVida);
        statusPanel.add(new JLabel("Pug HP: "));
        pugVida = new JLabel("50");
        statusPanel.add(pugVida);
        statusPanel.add(new JLabel("Lug HP: "));
        lugVida = new JLabel("70");
        statusPanel.add(lugVida);
        statusPanel.add(new JLabel("Dug HP: "));
        dugVida = new JLabel("60");
        statusPanel.add(dugVida);

        add(statusPanel, BorderLayout.CENTER);

        // Painel para o botão "Mudar Turno"
        mudarTurnoButton = new JButton("Mudar Turno");
        mudarTurnoButton.addActionListener(mudarTurnoListener);
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        buttonPanel.add(mudarTurnoButton);
        add(buttonPanel, BorderLayout.SOUTH);

        // Mostrar turno atual
        turnoAtualLabel = new JLabel("Turno Atual: -", SwingConstants.CENTER);
        turnoAtualLabel.setFont(new Font("Arial", Font.BOLD, 14));
        add(turnoAtualLabel, BorderLayout.NORTH);

        setPreferredSize(new Dimension(200, 0));
    }

    // Atualiza o status do Mago
    public void atualizarStatusMago(int hp) {
        magoVida.setText(String.valueOf(hp));
    }

    // Atualiza o status do Guerreiro
    public void atualizarStatusGuerreiro(int hp) {
        guerreiroVida.setText(String.valueOf(hp));
    }

    // Atualiza o status do Anão
    public void atualizarStatusAnao(int hp) {
        anaoVida.setText(String.valueOf(hp));
    }

    // Atualiza o status do Pug
    public void atualizarStatusPug(int hp) {
        pugVida.setText(String.valueOf(hp));
    }

    // Atualiza o status do Lug
    public void atualizarStatusLug(int hp) {
        lugVida.setText(String.valueOf(hp));
    }

    // Atualiza o status do Dug
    public void atualizarStatusDug(int hp) {
        dugVida.setText(String.valueOf(hp));
    }

    // Atualiza o texto com o turno atual
    public void atualizarTurnoAtual(String texto) {
        turnoAtualLabel.setText("Turno Atual: " + texto);
    }

    // Desativa o botão "Mudar Turno"
    public void desativarBotaoMudarTurno() {
        mudarTurnoButton.setEnabled(false);
    }

    // Ativa o botão "Mudar Turno"
    public void ativarBotaoMudarTurno() {
        mudarTurnoButton.setEnabled(true);
    }
}
