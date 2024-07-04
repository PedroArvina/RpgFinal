package Construtor1;

import Mob.*;

import java.util.Arrays;
import java.util.List;
import java.util.Queue;
import java.util.LinkedList;

public class Turno {
    private Queue<Personagem> filaTurnos;
    private List<Personagem> todosPersonagens;

    public Turno(Personagem... personagens) {
        todosPersonagens = Arrays.asList(personagens);
        filaTurnos = new LinkedList<>(todosPersonagens);
    }

    // Próximo personagem a agir
    public Personagem proximoTurno() {
        Personagem personagem = filaTurnos.poll();
        filaTurnos.offer(personagem);
        return personagem;
    }

    // Reseta os turnos para que todos possam agir no próximo turno
    public void resetarTurnos() {
        filaTurnos.clear();
        filaTurnos.addAll(todosPersonagens);
    }

    // Verifica se todos os personagens já agiram no turno atual
    public boolean todosAgiram() {
        return filaTurnos.isEmpty();
    }
}
