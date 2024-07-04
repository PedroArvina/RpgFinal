package Mob;

public class Rei extends Monstro {
    public Rei() {
        super("Rei", 50, 30, 1, 9);
    }

    @Override
    public void usarHabilidadeEspecial() {
        System.out.println(nome + " usou sua habilidade especial: Investida Brutal.");
    }

	@Override
	protected int calcularDistancia(Personagem inimigo) {
		// TODO Auto-generated method stub
		return 0;
	}
}
