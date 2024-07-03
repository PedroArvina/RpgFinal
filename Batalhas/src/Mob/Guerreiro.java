package Mob;

public class Guerreiro extends Personagem {
    public Guerreiro() {
        super("Guerreiro", 70, 15, 1, 2);
    }

    @Override
    public void usarHabilidadeEspecial() {
        System.out.println(nome + " usou sua habilidade especial: Golpe Poderoso.");
    }

	@Override
	protected int calcularDistancia(Personagem inimigo) {
		// TODO Auto-generated method stub
		return 0;
	}
}
