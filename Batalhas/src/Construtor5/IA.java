package Construtor5;

import Mob.Personagem;
import Mob.Monstro;

import java.awt.*;
import java.util.List;

public class IA {
    private final Tabuleiro tabuleiro;

    public IA(Tabuleiro tabuleiro) {
        this.tabuleiro = tabuleiro;
    }

    public void movimentarOuAtacarIndividuo(String simbolo) {
        Personagem monstro = tabuleiro.encontrarPersonagemPorSimbolo(simbolo);
        if (monstro != null) {
            Point posicaoAtual = tabuleiro.encontrarPosicaoPersonagem(simbolo);
            if (posicaoAtual != null) {
                Point destinoMaisProximo = encontrarDestinoMaisProximo(posicaoAtual, monstro);

                // Tentar atacar primeiro
                if (!atacarPersonagemProximo(posicaoAtual, monstro)) {
                    // Se não houver ninguém para atacar, então mova-se
                    if (destinoMaisProximo != null && !destinoMaisProximo.equals(posicaoAtual)) {
                        tabuleiro.removerPersonagem(posicaoAtual.x, posicaoAtual.y);
                        tabuleiro.posicionarPersonagem(destinoMaisProximo.x, destinoMaisProximo.y, simbolo, monstro);
                    }
                }
            }
        }
    }

    private Point encontrarDestinoMaisProximo(Point origem, Personagem atacante) {
        List<Point> quadradosValidos = tabuleiro.obterQuadradosValidos(origem.x, origem.y, atacante.getAlcanceMovimento());

        Point alvo = encontrarPersonagemMaisProximo(origem, atacante instanceof Monstro);
        if (alvo == null) {
            return null;
        }

        Point destinoMaisProximo = null;
        int menorDistancia = Integer.MAX_VALUE;

        for (Point p : quadradosValidos) {
            // Verifica se o quadrado não está ocupado
            if (!tabuleiro.estaOcupado(p)) {
                int distancia = calcularDistancia(p, alvo);
                if (distancia < menorDistancia) {
                    menorDistancia = distancia;
                    destinoMaisProximo = p;
                }
            }
        }

        return destinoMaisProximo;
    }

    private Point encontrarPersonagemMaisProximo(Point origem, boolean procurarHerois) {
        String[] herois = {"M", "G", "A"};
        String[] monstros = {"P", "L", "D"};
        String[] alvos = procurarHerois ? herois : monstros;

        Point alvoMaisProximo = null;
        int menorDistancia = Integer.MAX_VALUE;

        for (String simbolo : alvos) {
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

    private boolean atacarPersonagemProximo(Point origem, Personagem atacante) {
        List<Point> quadradosAtaque = tabuleiro.obterQuadradosValidos(origem.x, origem.y, atacante.getRange());

        for (Point p : quadradosAtaque) {
            if (tabuleiro.contemInimigo(p, atacante)) {
                tabuleiro.reduzirVidaEAtacar(p, atacante);
                return true;
            }
        }

        return false;
    }

    private int calcularDistancia(Point a, Point b) {
        return Math.abs(a.x - b.x) + Math.abs(a.y - b.y);
    }
}
