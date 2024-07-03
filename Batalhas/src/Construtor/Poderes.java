package Construtor;

import Mob.Personagem;

public class Poderes {
    // Método que aciona um poder baseado no identificador do comando de ação
    public void acionar(String acao, Personagem personagem) {
        switch (acao) {
            case "quadradoMago1":
                magoCura(personagem);
                break;
            case "quadradoMago2":
                magoFeitico(personagem);
                break;
            case "quadradoMago3":
                magoEscudo(personagem);
                break;
            case "quadradoMago4":
                magoFogo(personagem);
                break;
            case "quadradoMago5":
                magoGelo(personagem);
                break;
            case "quadradoGuerreiro1":
                guerreiroAtaque1(personagem);
                break;
            case "quadradoGuerreiro2":
                guerreiroAtaque2(personagem);
                break;
            case "quadradoGuerreiro3":
                guerreiroDefesa(personagem);
                break;
            case "quadradoGuerreiro4":
                guerreiroEspecial1(personagem);
                break;
            case "quadradoGuerreiro5":
                guerreiroEspecial2(personagem);
                break;
            case "quadradoAnao1":
                anaoMartelo(personagem);
                break;
            case "quadradoAnao2":
                anaoEscavacao(personagem);
                break;
            case "quadradoAnao3":
                anaoConstruir(personagem);
                break;
            case "quadradoAnao4":
                anaoExplodir(personagem);
                break;
            case "quadradoAnao5":
                anaoDefender(personagem);
                break;
            default:
                System.out.println("Ação não definida para " + acao);
                break;
        }
    }

    // Poderes do Mago
    private void magoFeitico(Personagem personagem) {
        System.out.println("Mago lançou Feitiço.");
    }

    private void magoCura(Personagem personagem) {
        personagem.adicionarVida(20);
        System.out.println("Mago usou Cura e adicionou 20 pontos de vida.");
    }

    private void magoEscudo(Personagem personagem) {
        System.out.println("Mago usou Escudo.");
    }

    private void magoFogo(Personagem personagem) {
        System.out.println("Mago lançou Fogo.");
    }

    private void magoGelo(Personagem personagem) {
        System.out.println("Mago lançou Gelo.");
    }

    // Poderes do Guerreiro
    private void guerreiroAtaque1(Personagem personagem) {
    	personagem.adicionarVida(20);
        
        System.out.println("Guerreiro usou Ataque 1.");
    }

    private void guerreiroAtaque2(Personagem personagem) {
        System.out.println("Guerreiro usou Ataque 2.");
    }

    private void guerreiroDefesa(Personagem personagem) {
        System.out.println("Guerreiro usou Defesa.");
    }

    private void guerreiroEspecial1(Personagem personagem) {
        System.out.println("Guerreiro usou Especial 1.");
    }

    private void guerreiroEspecial2(Personagem personagem) {
        System.out.println("Guerreiro usou Especial 2.");
    }

    // Poderes do Anão
    private void anaoMartelo(Personagem personagem) {
        System.out.println("Anão usou Martelo.");
    }

    private void anaoEscavacao(Personagem personagem) {
        System.out.println("Anão usou Escavação.");
    }

    private void anaoConstruir(Personagem personagem) {
        System.out.println("Anão usou Construir.");
    }

    private void anaoExplodir(Personagem personagem) {
        System.out.println("Anão usou Explodir.");
    }

    private void anaoDefender(Personagem personagem) {
        System.out.println("Anão usou Defender.");
    }
}
