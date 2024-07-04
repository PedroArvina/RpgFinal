package Construtor1;

import javax.swing.*;
import Mob.Monstro;
import Mob.Personagem;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.Map;
import Construtor1.Inventario1;

public class ControlesDeJogo extends JPanel {

    private Acessório acessorio;
    private Turno turno;
    private IA ia;
    private Personagem mago;
    private Personagem guerreiro;
    private Personagem anao;
    private Monstro pug;
    private Monstro lug;
    private Monstro dug;
    private Personagem personagemSelecionado;
    private Map<String, JButton> quadrados;
    private Poderes poderes; 
    private Inventario1 inventario1;
    private int quantidadePocoesMago = 3;

    public ControlesDeJogo(Personagem personagemSelecionado, Acessório acessorio) {
        this.acessorio = acessorio;
        this.poderes = new Poderes();
        this.inventario1 = new Inventario1();
        this.personagemSelecionado = personagemSelecionado;
        setLayout(new BorderLayout());
        quadrados = new HashMap<>();

        JLabel titulo = new JLabel("Painel de Controle", SwingConstants.CENTER);
        titulo.setFont(new Font("Arial", Font.BOLD, 16));
        add(titulo, BorderLayout.NORTH);

        JPanel controlePanel = new JPanel(new GridLayout(3, 1));
        controlePanel.add(criarPainelHeroi("Mago", "Fotos/P1", "quadradoMago"));
        add(controlePanel, BorderLayout.CENTER);

        JButton botaoInventario1 = new JButton("Abrir Inventário");
        botaoInventario1.addActionListener(e -> inventario1.abrirInventario1(this.personagemSelecionado));
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        buttonPanel.add(botaoInventario1);
        add(buttonPanel, BorderLayout.SOUTH);

        inicializarAcoesETextos();
    }

    private JPanel criarPainelHeroi(String nomeHeroi, String linkImagem, String prefixoQuadrados) {
        JPanel painelHeroi = new JPanel(new BorderLayout());
        JLabel imagemHeroi = new JLabel(new ImageIcon(new ImageIcon(linkImagem).getImage().getScaledInstance(20, 20, Image.SCALE_SMOOTH)));
        JLabel nomeHeroiLabel = new JLabel(nomeHeroi, SwingConstants.LEFT);
        nomeHeroiLabel.setFont(new Font("Arial", Font.BOLD, 12));

        JPanel painelSuperior = new JPanel(new FlowLayout(FlowLayout.LEFT));
        painelSuperior.add(imagemHeroi);
        painelSuperior.add(nomeHeroiLabel);
        painelHeroi.add(painelSuperior, BorderLayout.NORTH);

        JPanel painelInferior = new JPanel(new GridLayout(1, 1));
        JButton botaoAcao = new JButton("Usar Porção de Cura (Restantes: " + quantidadePocoesMago + ")");
        botaoAcao.setActionCommand(prefixoQuadrados + "1");
        botaoAcao.addActionListener(new QuadradoActionListener());
        quadrados.put(prefixoQuadrados + "1", botaoAcao);
        painelInferior.add(botaoAcao);
        painelHeroi.add(painelInferior, BorderLayout.CENTER);

        return painelHeroi;
    }

    private class QuadradoActionListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            if (quantidadePocoesMago > 0) {
                quantidadePocoesMago--;
                atualizarTextoBotao("quadradoMago1", "Usar Porção de Cura (Restantes: " + quantidadePocoesMago + ")");
                acionarPoder(e.getActionCommand());
            } else {
                JOptionPane.showMessageDialog(null, "Não há mais poções de cura disponíveis para o Mago.", "Erro", JOptionPane.ERROR_MESSAGE);
            }
            atualizarPainelAcessorio();
        }
    }

    private void acionarPoder(String nomeQuadrado) {
        poderes.acionar(nomeQuadrado, personagemSelecionado);
    }

    private void atualizarPainelAcessorio() {
        if (personagemSelecionado != null) {
            acessorio.atualizarStatusMago(personagemSelecionado.getHp());
        }
    }

    private void inicializarAcoesETextos() {
        // Inicialmente não necessário, pois o texto já é configurado na criação do botão.
    }

    private void atualizarTextoBotao(String nomeQuadrado, String texto) {
        JButton quadrado = quadrados.get(nomeQuadrado);
        if (quadrado != null) {
            quadrado.setText(texto);
        }
    }
}
