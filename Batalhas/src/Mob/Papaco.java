package Mob;

public class Papaco extends Monstro {
    public Papaco() {
        super("Papaco", 70, 5, 500, 500);
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
