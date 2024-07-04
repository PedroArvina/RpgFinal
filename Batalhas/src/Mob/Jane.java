package Mob;

public class Jane extends Monstro {
    public Jane() {
        super("Jane", 400, 20, 1, 2);
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
