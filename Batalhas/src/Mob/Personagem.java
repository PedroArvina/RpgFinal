package Mob;

public abstract class Personagem {
    protected String nome;
    protected int hp;
    protected int ataque;
    protected int range;
    protected int alcanceMovimento;

    public Personagem(String nome, int hp, int ataque, int range, int alcanceMovimento) {
        if (nome == null || nome.trim().isEmpty()) throw new IllegalArgumentException("Nome não pode ser nulo ou vazio.");
        this.nome = nome;
        this.hp = hp;
        this.ataque = ataque;
        this.range = range;
        this.alcanceMovimento = alcanceMovimento;
    }

    public String getNome() {
        return nome;
    }

    public int getHp() {
        return hp;
    }

    public void setHp(int hp) {
        this.hp = hp;
    }

    public int getAtaque() {
        return ataque;
    }

    public void setAtaque(int ataque) {
        this.ataque = ataque;
    }

    public int getRange() {
        return range;
    }

    public void setRange(int range) {
        this.range = range;
    }

    public int getAlcanceMovimento() {
        return alcanceMovimento;
    }

    public void setAlcanceMovimento(int alcanceMovimento) {
        this.alcanceMovimento = alcanceMovimento;
    }

    public abstract void usarHabilidadeEspecial();

    public void receberDano(int dano) {
        this.hp -= dano;
        if (this.hp < 0) this.hp = 0;
    }

    public void atacar(Personagem inimigo) throws IllegalStateException {
        if (this.hp <= 0) {
            throw new IllegalStateException("Não é possível atacar, pois " + this.nome + " está incapacitado.");
        }
        if (this.range >= calcularDistancia(inimigo)) {
            System.out.println(this.nome + " atacou " + inimigo.getNome() + " causando " + this.ataque + " de dano.");
            inimigo.receberDano(this.ataque);
        } else {
            System.out.println(this.nome + " está fora de alcance para atacar " + inimigo.getNome());
        }
    }

    public void adicionarVida(int vida) throws SaudeInvalidaException {
        if (this.hp + vida > 70) {
            int excesso = vida - (70 - this.hp);  // Calcula o excesso de vida que seria adicionado
            vida = 70 - this.hp;  // Ajusta a vida para atingir exatamente 70
            this.hp += vida;
            System.out.println(this.nome + " agora tem " + this.hp + " de vida.");
            throw new SaudeInvalidaException("Não é possível adicionar essa quantidade de vida sem ultrapassar o limite de 70. Excesso de " + excesso + " foi ignorado.");
        } else {
            this.hp += vida;
            System.out.println(this.nome + " agora tem " + this.hp + " de vida.");
        }
    }




    protected abstract int calcularDistancia(Personagem inimigo);
}
