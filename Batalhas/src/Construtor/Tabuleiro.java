package Construtor;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;
import Mob.*;

public class Tabuleiro extends JFrame {
    private static final int LARGURA = 15;
    private static final int ALTURA = 8;
    private static final Color HIGHLIGHT_COLOR = Color.YELLOW;
    private static final Color ATTACK_COLOR = Color.RED;
    private JPanel[][] quadrados;
    private Acessório acessorio;
    private ControlesDeJogo controlesDeJogo;
    private Turno turno;
    private IA ia;

    private Personagem mago;
    private Personagem guerreiro;
    private Personagem anao;
    private Monstro pug;
    private Monstro lug;
    private Monstro dug;
    private Personagem personagemSelecionado;
    private Point posicaoSelecionada;
    private String simboloSelecionado;
    private boolean podeMover = true;

    public Tabuleiro() {
        super("Tabuleiro de Batalha");

        // Inicializar personagens
        mago = new Mago();
        guerreiro = new Guerreiro();
        anao = new Anão();
        pug = new Pug();
        lug = new Lug();
        dug = new Dug();

        // Inicializar sistema de turnos
        turno = new Turno(mago, guerreiro, anao, pug, lug, dug);
        ia = new IA(this);
        Inventario inventario = new Inventario();

        personagemSelecionado = turno.proximoTurno(); // Garanta que sempre haja um personagem selecionado

        // Criação do tabuleiro
        JPanel painelTabuleiro = new JPanel(new GridLayout(ALTURA, LARGURA));
        quadrados = new JPanel[ALTURA][LARGURA];
        for (int i = 0; i < ALTURA; i++) {
            for (int j = 0; j < LARGURA; j++) {
                quadrados[i][j] = new JPanel();
                quadrados[i][j].setBackground(Color.WHITE);
                quadrados[i][j].setBorder(BorderFactory.createLineBorder(Color.BLACK));
                quadrados[i][j].addMouseListener(new TabuleiroClickListener(i, j));
                painelTabuleiro.add(quadrados[i][j]);
            }
        }

        // Posicionamento inicial dos personagens no tabuleiro
        posicionarPersonagem(0, 0, "M", mago); // Mago
        posicionarPersonagem(0, 1, "G", guerreiro); // Guerreiro
        posicionarPersonagem(0, 2, "A", anao); // Anão
        posicionarPersonagem(7, 12, "P", pug); // Pug
        posicionarPersonagem(7, 13, "L", lug); // Lug
        posicionarPersonagem(7, 14, "D", dug); // Dug

        // Criação do painel de acessórios
        acessorio = new Acessório(new MudarTurnoListener());
        atualizarPainelAcessorio(); // Certifique-se de que `acessorio` está inicializado antes de chamar este método

        // Criação do painel de controles
        controlesDeJogo = new ControlesDeJogo(personagemSelecionado, acessorio);

        
        JPanel painelPrincipal = new JPanel(new BorderLayout());
        painelPrincipal.add(inventario, BorderLayout.CENTER);
        painelPrincipal.add(inventario, BorderLayout.SOUTH); // Adiciona o inventário na parte inferior

        add(painelPrincipal);

        // Divisão do painel com JSplitPane
        JSplitPane splitPaneEsquerda = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, controlesDeJogo, painelTabuleiro);
        splitPaneEsquerda.setResizeWeight(0.2); // Ajuste conforme necessário para dar mais espaço ao painel de controle

        JSplitPane splitPanePrincipal = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, splitPaneEsquerda, acessorio);
        splitPanePrincipal.setResizeWeight(0.75); // Ajuste conforme necessário para equilibrar entre controles e acessórios

        add(splitPanePrincipal);

        // Configuração da janela
        setSize(1200, 600); // Ajuste o tamanho conforme necessário para acomodar todos os componentes
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setVisible(true);

        iniciarNovoTurno(); // Iniciar o primeiro turno
    }

    // Limpa os destaques do tabuleiro
    private void limparDestaques() {
        for (int i = 0; i < ALTURA; i++) {
            for (int j = 0; j < LARGURA; j++) {
                quadrados[i][j].setBackground(Color.WHITE);
            }
        }
    }

    // Atualiza os valores do painel de acessórios
    private void atualizarPainelAcessorio() {
        if (personagemSelecionado != null) {
            acessorio.atualizarStatusMago(mago.getHp());
            acessorio.atualizarStatusGuerreiro(guerreiro.getHp());
            acessorio.atualizarStatusAnao(anao.getHp());
            acessorio.atualizarStatusPug(pug.getHp());
            acessorio.atualizarStatusLug(lug.getHp());
            acessorio.atualizarStatusDug(dug.getHp());
            acessorio.atualizarTurnoAtual("Turno de " + personagemSelecionado.getNome());
        }
    }
    
 // Verifica se todos os personagens estão mortos
    private boolean todosPersonagensMortos() {
        return mago.getHp() <= 0 && guerreiro.getHp() <= 0 && anao.getHp() <= 0;
    }

    // Verifica se todos os monstros estão mortos
    private boolean todosMonstrosMortos() {
        return pug.getHp() <= 0 && lug.getHp() <= 0 && dug.getHp() <= 0;
    }


    public void iniciarNovoTurno() {
        if (todosPersonagensMortos()) {
            JOptionPane.showMessageDialog(this, "Todos os personagens estão mortos. Fim do jogo!", "Game Over", JOptionPane.INFORMATION_MESSAGE);
            fecharTodasJanelas(); // Fecha todas as janelas abertas
            System.exit(0); // Encerra a aplicação após fechar as janelas
            return;
        }

        if (todosMonstrosMortos()) {
            JOptionPane.showMessageDialog(null, "Você venceu! Aperte qualquer tecla para continuar.", "Vitória", JOptionPane.INFORMATION_MESSAGE);
            return; // Pode-se reiniciar o jogo aqui ou fechar, dependendo da lógica desejada
        }

        while (true) {
            personagemSelecionado = turno.proximoTurno();

            if (personagemSelecionado == null) {
                JOptionPane.showMessageDialog(this, "Erro no sistema de turnos. Fim do jogo!", "Erro", JOptionPane.ERROR_MESSAGE);
                return;
            }

            if (personagemSelecionado.getHp() > 0) {
                break;
            }
        }

        simboloSelecionado = personagemSelecionado.getNome().substring(0, 1);
        posicaoSelecionada = encontrarPosicaoPersonagem(simboloSelecionado);

        podeMover = true;
        limparDestaques();
        if (posicaoSelecionada != null) {
            destacarQuadrados(posicaoSelecionada.x, posicaoSelecionada.y, personagemSelecionado.getAlcanceMovimento());
        }

        atualizarPainelAcessorio();
        acessorio.ativarBotaoMudarTurno();

        if (personagemSelecionado instanceof Monstro) {
            ia.movimentarOuAtacarIndividuo(simboloSelecionado);
            iniciarNovoTurno();
        }
    }

    
    private void fecharTodasJanelas() {
        Window[] windows = Window.getWindows();
        for (Window window : windows) {
            if (window instanceof JFrame || window instanceof JDialog) {
                window.dispose(); // Fecha a janela
            }
        }
    }



    // Reduz a vida do alvo e atualiza o painel
    public void reduzirVidaEAtacar(Point alvo, Personagem atacante) {
        String simboloAtacante = atacante.getNome().substring(0, 1);
        Component[] componentes = quadrados[alvo.x][alvo.y].getComponents();
        if (componentes.length > 0 && componentes[0] instanceof JLabel) {
            JLabel label = (JLabel) componentes[0];
            String simboloAlvo = label.getText();
            Personagem alvoPersonagem = encontrarPersonagemPorSimbolo(simboloAlvo);

            if (alvoPersonagem != null && !simboloAtacante.equals(simboloAlvo)) {
                alvoPersonagem.receberDano(atacante.getAtaque());
                if (alvoPersonagem.getHp() <= 0) {
                    removerPersonagem(alvo.x, alvo.y);
                }
                atualizarPainelAcessorio();
            }
        }
    }

    // Verifica se há inimigo no quadrado
    public boolean contemInimigo(Point p, Personagem atacante) {
        Component[] componentes = quadrados[p.x][p.y].getComponents();
        if (componentes.length > 0 && componentes[0] instanceof JLabel) {
            JLabel label = (JLabel) componentes[0];
            String simbolo = label.getText();
            if (atacante instanceof Monstro) {
                return simbolo.equals("M") || simbolo.equals("G") || simbolo.equals("A");
            } else {
                return simbolo.equals("P") || simbolo.equals("L") || simbolo.equals("D");
            }
        }
        return false;
    }

    // Verifica se um quadrado está ocupado
    boolean estaOcupado(Point p) {
        return quadrados[p.x][p.y].getComponents().length > 0;
    }

    // Destaca os quadrados de movimento e ataque
    private void destacarQuadrados(int origemX, int origemY, int alcance) {
        List<Point> pontosValidos = obterQuadradosValidos(origemX, origemY, alcance);
        for (Point p : pontosValidos) {
            if (contemInimigo(p, personagemSelecionado)) {
                quadrados[p.x][p.y].setBackground(ATTACK_COLOR);
            } else {
                quadrados[p.x][p.y].setBackground(HIGHLIGHT_COLOR);
            }
        }
    }

    // Obtém os quadrados válidos para movimento e ataque
    protected List<Point> obterQuadradosValidos(int origemX, int origemY, int alcance) {
        List<Point> pontosValidos = new ArrayList<>();
        for (int i = Math.max(0, origemX - alcance); i <= Math.min(ALTURA - 1, origemX + alcance); i++) {
            for (int j = Math.max(0, origemY - alcance); j <= Math.min(LARGURA - 1, origemY + alcance); j++) {
                if (Math.abs(i - origemX) + Math.abs(j - origemY) <= alcance) {
                    pontosValidos.add(new Point(i, j));
                }
            }
        }
        return pontosValidos;
    }

    // Remove um personagem do tabuleiro
    public void removerPersonagem(int x, int y) {
        if (x >= 0 && y >= 0 && x < ALTURA && y < LARGURA) {
            quadrados[x][y].removeAll();
            quadrados[x][y].revalidate();
            quadrados[x][y].repaint();
        }
    }

    // Encontra um personagem por seu símbolo
    protected Personagem encontrarPersonagemPorSimbolo(String simbolo) {
        switch (simbolo) {
            case "M": return mago;
            case "G": return guerreiro;
            case "A": return anao;
            case "P": return pug;
            case "L": return lug;
            case "D": return dug;
            default: return null;
        }
    }

    // Posiciona um personagem em um quadrado
    protected void posicionarPersonagem(int x, int y, String simbolo, Personagem personagem) {
        if (x >= 0 && y >= 0 && x < ALTURA && y < LARGURA) {
            removerPersonagem(x, y);
            JLabel label = new JLabel(simbolo, SwingConstants.CENTER);
            label.setFont(new Font("Arial", Font.BOLD, 18));
            quadrados[x][y].add(label);
            quadrados[x][y].revalidate();
            quadrados[x][y].repaint();
        }
    }

    // Encontra a posição de um personagem no tabuleiro
    protected Point encontrarPosicaoPersonagem(String simbolo) {
        for (int i = 0; i < ALTURA; i++) {
            for (int j = 0; j < LARGURA; j++) {
                Component[] componentes = quadrados[i][j].getComponents();
                if (componentes.length > 0 && componentes[0] instanceof JLabel) {
                    JLabel label = (JLabel) componentes[0];
                    if (label.getText().equals(simbolo)) {
                        return new Point(i, j);
                    }
                }
            }
        }
        return null;
    }

    // Listener para o botão "Mudar Turno"
    private class MudarTurnoListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            iniciarNovoTurno();
        }
    }

    // Listener para os cliques no tabuleiro
    private class TabuleiroClickListener extends MouseAdapter {
        private final int x, y;

        public TabuleiroClickListener(int x, int y) {
            this.x = x;
            this.y = y;
        }

        @Override
        public void mouseClicked(MouseEvent e) {
            Point destino = new Point(x, y);
            if (posicaoSelecionada != null && podeMover) {
                // Atacar se o quadrado estiver marcado como de ataque
                if (quadrados[x][y].getBackground().equals(ATTACK_COLOR)) {
                    reduzirVidaEAtacar(destino, personagemSelecionado);
                    podeMover = false;
                    limparDestaques();
                } else if (quadrados[x][y].getBackground().equals(HIGHLIGHT_COLOR) && !estaOcupado(destino)) {
                    removerPersonagem(posicaoSelecionada.x, posicaoSelecionada.y);
                    posicionarPersonagem(x, y, simboloSelecionado, personagemSelecionado);
                    podeMover = false;
                    limparDestaques();
                } else {
                    limparDestaques();
                    destacarQuadrados(posicaoSelecionada.x, posicaoSelecionada.y, personagemSelecionado.getAlcanceMovimento());
                }
            }
        }
    }

    public static void main(String[] args) {
        new Tabuleiro();
    }
}
