package Mob;

public class Pug extends Monstro {
    public Pug() {
        super("Pug", 50, 10, 1, 2);
    }

    @Override
    public void usarHabilidadeEspecial() {
        System.out.println(nome + " usou sua habilidade especial: Mordida Mortal.");
    }

	@Override
	protected int calcularDistancia(Personagem inimigo) {
		// TODO Auto-generated method stub
		return 0;
	}
}
