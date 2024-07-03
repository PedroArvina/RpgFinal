package Mob;

public class Mago extends Personagem {
    public Mago() {
        super("Mago", 50, 10, 2, 3);
    }

    @Override
    public void usarHabilidadeEspecial() {
        // Implementação vazia para o exemplo
    }

    // Método para curar aliados
    public void curar(Personagem aliado) {
        aliado.setHp(aliado.getHp() + 5);
    }

	@Override
	protected int calcularDistancia(Personagem inimigo) {
		// TODO Auto-generated method stub
		return 0;
	}
}
