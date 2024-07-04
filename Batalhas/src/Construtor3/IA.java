package Construtor3;

import Mob.Personagem;
import Mob.Monstro;

import java.awt.*;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

public class IA {
    private final Tabuleiro tabuleiro;
    private final Random random = new Random();

    public IA(Tabuleiro tabuleiro) {
        this.tabuleiro = tabuleiro;
    }

    public void movimentarOuAtacarIndividuo(String simbolo) {
        Personagem monstro = tabuleiro.encontrarPersonagemPorSimbolo(simbolo);
        if (monstro != null) {
            Point posicaoAtual = tabuleiro.encontrarPosicaoPersonagem(simbolo);
            if (posicaoAtual != null) {
                Point destino = escolherDestinoMaisDistante(posicaoAtual, monstro);

                if (destino != null && !destino.equals(posicaoAtual)) {
                    tabuleiro.removerPersonagem(posicaoAtual.x, posicaoAtual.y);
                    tabuleiro.posicionarPersonagem(destino.x, destino.y, simbolo, monstro);
                }
            }
        }
    }

    private Point escolherDestinoMaisDistante(Point origem, Personagem atacante) {
        List<Point> quadradosValidos = tabuleiro.obterQuadradosValidos(origem.x, origem.y, atacante.getAlcanceMovimento());
        Point personagemMaisProximo = encontrarPersonagemMaisProximo(origem, true);

        if (personagemMaisProximo == null) {
            return null;
        }

        Point destinoMaisDistante = null;
        int maiorDistancia = -1;  // Inicializa com um valor muito baixo para garantir que qualquer distância seja maior

        for (Point p : quadradosValidos) {
            if (!tabuleiro.estaOcupado(p) && calcularDistancia(p, personagemMaisProximo) > 1) {
                int distancia = calcularDistancia(p, personagemMaisProximo);
                if (distancia > maiorDistancia) {
                    maiorDistancia = distancia;
                    destinoMaisDistante = p;
                }
            }
        }

        return destinoMaisDistante;
    }

    private Point encontrarPersonagemMaisProximo(Point origem, boolean procurarHerois) {
        String[] herois = {"M", "G", "A"};
        Point alvoMaisProximo = null;
        int menorDistancia = Integer.MAX_VALUE;

        for (String simbolo : herois) {
            Point posicao = tabuleiro.encontrarPosicaoPersonagem(simbolo);
            if (posicao != null) {
                int distancia = calcularDistancia(origem, posicao);
                if (distancia < menorDistancia) {
                    menorDistancia = distancia;
                    alvoMaisProximo = posicao;
                }
            }
        }

        return alvoMaisProximo;
    }

    private int calcularDistancia(Point a, Point b) {
        return Math.abs(a.x - b.x) + Math.abs(a.y - b.y);
    }
}
