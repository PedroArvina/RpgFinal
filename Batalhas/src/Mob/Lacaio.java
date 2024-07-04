package Mob;

public class Lacaio extends Monstro {
    public Lacaio() {
        super("Lacaio", 1, 5, 1, 100);
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
