package Menu;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextArea;

public class Instrucoes {

    public static void instrucoes(JFrame frame) {
        JPanel painelInstrucoes = new JPanel();
        
        JTextArea areaTexto = new JTextArea(10, 30); // Define a área de texto com 10 linhas e 30 colunas
        areaTexto.setText("Regras do Combate:\n"
                + "1. Cada personagem tem uma vez por turno para atacar ou usar um item.\n"
                + "2. O combate é baseado em turnos, e a ordem é determinada pela agilidade dos personagens.\n"
                + "3. Após cada movimento clique no botão Proximo Turno\n"
                + "4. A vitória é alcançada quando todos os adversários são derrotados e o botão Proximo turno é pressionado.");
        areaTexto.setEditable(false); // Impede a edição do texto

        painelInstrucoes.add(areaTexto);
        frame.add(painelInstrucoes);
        frame.pack();
        frame.setVisible(true);
    }
}
