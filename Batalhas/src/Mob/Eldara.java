package Mob;

public class Eldara extends Monstro {
    public Eldara() {
        super("Eldara", 300, 5, 10, 10);
    }

    @Override
    public void usarHabilidadeEspecial() {
        System.out.println(nome + " usou sua habilidade especial: Golpe Duplo.");
    }

	@Override
	protected int calcularDistancia(Personagem inimigo) {
		// TODO Auto-generated method stub
		return 0;
	}
}
