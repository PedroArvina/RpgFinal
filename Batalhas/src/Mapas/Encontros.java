package Mapas;

import java.awt.BorderLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.List;
import java.util.Timer;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.SwingUtilities;

public class Encontros {

    private static JFrame frame;
    private static JLabel questionLabel, imageLabel;
    private static JButton yesButton, noButton, startButton, declineButton;
    private static List<Question> questions;
    private static int currentQuestionIndex = 0;
    private static int score = 0;

    public static void main(String[] args) {
        SwingUtilities.invokeLater(Encontros::encontroComVelho);
    }

    public static void encontroComVelho() {
        prepareIntroGUI();
    }

    private static void prepareIntroGUI() {
        frame = new JFrame("Desafio do Mago - Java");
        frame.setSize(400, 400); // Ajustado para melhor acomodar a imagem e o texto
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setLayout(new BorderLayout());

        JLabel storyLabel = new JLabel("<html><div style='text-align: center;'>Olá! Tudo bem? Meu nome é Joaquim, eu encontro alguém para passar uma grande arma, mas primeiro, ele deve se mostrar digno, você deseja enfrentar meu teste?</div></html>", JLabel.CENTER);
        frame.add(storyLabel, BorderLayout.NORTH);

        // Carregando e redimensionando a imagem do velho Joaquim
        ImageIcon originalIcon = new ImageIcon(Encontros.class.getResource("/Fotos/P1.png")); // Assegure que o caminho está correto
        Image image = originalIcon.getImage(); // transform it 
        Image newimg = image.getScaledInstance(200, 200,  java.awt.Image.SCALE_SMOOTH); // scale it the smooth way  
        ImageIcon icon = new ImageIcon(newimg);  // transform it back
        imageLabel = new JLabel(icon);
        frame.add(imageLabel, BorderLayout.CENTER);

        startButton = new JButton("Aceitar Desafio");
        declineButton = new JButton("Recusar Desafio");

        startButton.addActionListener(e -> {
            initializeQuestions();
            prepareChallengeGUI();
        });

        declineButton.addActionListener(e -> frame.dispose());

        JPanel introPanel = new JPanel();
        introPanel.add(startButton);
        introPanel.add(declineButton);
        frame.add(introPanel, BorderLayout.SOUTH);

        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    private static void prepareChallengeGUI() {
        frame.getContentPane().removeAll();
        frame.setLayout(new BorderLayout());

        frame.add(imageLabel, BorderLayout.NORTH); // Reuse the image label

        questionLabel = new JLabel("", JLabel.CENTER);
        frame.add(questionLabel, BorderLayout.CENTER);

        yesButton = new JButton("Sim");
        noButton = new JButton("Não");

        yesButton.addActionListener(e -> answerAction(true));
        noButton.addActionListener(e -> answerAction(false));

        JPanel buttonPanel = new JPanel();
        buttonPanel.add(yesButton);
        buttonPanel.add(noButton);

        frame.add(buttonPanel, BorderLayout.SOUTH);

        updateQuestion(currentQuestionIndex);
    }

    private static void updateQuestion(int questionIndex) {
        if (questionIndex < questions.size()) {
            Question currentQuestion = questions.get(questionIndex);
            questionLabel.setText("<html><div style='text-align: center;'>" + currentQuestion.text + "</div></html>");
        } else {
            finishChallenge();
        }
    }

    private static void answerAction(boolean answer) {
        Question currentQuestion = questions.get(currentQuestionIndex);
        if (answer == currentQuestion.answer) {
            score++;
        }

        currentQuestionIndex++;
        if (currentQuestionIndex < questions.size()) {
            updateQuestion(currentQuestionIndex);
        } else {
            finishChallenge();
        }
    }

    private static void finishChallenge() {
        frame.getContentPane().removeAll();

        String resultMessage = score >= 8 ? "<html><div style='text-align: center;'>Você se mostrou hábil, o maior tesouro que posso te dar é este conhecimento.</div></html>"
                                         : "<html><div style='text-align: center;'>Você se mostrou uma vergonha para a sua espécie, pelo seu nível, deveria cursar teatro na Estácio, é mais adequado ao seu nível intelectual.</div></html>";

        JLabel resultLabel = new JLabel(resultMessage, JLabel.CENTER);
        frame.add(resultLabel, BorderLayout.CENTER);

        JButton exitButton = new JButton("Sair");
        exitButton.addActionListener(e -> frame.dispose());
        JPanel exitPanel = new JPanel();
        exitPanel.add(exitButton);
        frame.add(exitPanel, BorderLayout.SOUTH);

        frame.revalidate();
        frame.repaint();
    }

    private static void initializeQuestions() {
        questions = new ArrayList<>();
        questions.add(new Question("Java é uma linguagem de programação baseada em objetos?", true));
        questions.add(new Question("O método finalize() é garantido para ser chamado no Java?", false));
        questions.add(new Question("Java suporta herança múltipla de classes?", false));
        questions.add(new Question("É possível sobrecarregar o operador '+' em Java?", false));
        questions.add(new Question("O garbage collector em Java pode ser forçado a executar?", false));
        questions.add(new Question("O operador 'instanceof' pode ser usado para testar se um objeto é de uma classe específica em tempo de execução?", true));
        questions.add(new Question("A JVM (Java Virtual Machine) executa código-fonte Java diretamente?", false));
        questions.add(new Question("Uma classe em Java pode implementar múltiplas interfaces?", true));
        questions.add(new Question("O Java utiliza exclusivamente passagem de parâmetros por referência?", false));
        questions.add(new Question("A palavra-chave 'synchronized' é usada para prevenir condições de corrida?", true));
    }

    static class Question {
        String text;
        boolean answer;

        Question(String text, boolean answer) {
            this.text = text;
            this.answer = answer;
        }
    }







    public static void encontroComAldeoes() {
    	frame = new JFrame("Desafio do Mago - Java");
        frame.setSize(400, 400); // Ajustado para melhor acomodar a imagem e o texto
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setLayout(new BorderLayout());

        JLabel storyLabel = new JLabel("<html><div style='text-align: center;'>Os soldados estão saqueando tudo em nome da Coroa, você não deveria entrar na cidade, muitos perigos estão por aí, todos guardam segredos</div></html>", JLabel.CENTER);
        frame.add(storyLabel, BorderLayout.NORTH);

        // Carregando e redimensionando a imagem do velho Joaquim
        ImageIcon originalIcon = new ImageIcon(Encontros.class.getResource("/Fotos/P2.png")); // Assegure que o caminho está correto
        Image image = originalIcon.getImage(); // transform it 
        Image newimg = image.getScaledInstance(200, 200,  java.awt.Image.SCALE_SMOOTH); // scale it the smooth way  
        ImageIcon icon = new ImageIcon(newimg);  // transform it back
        imageLabel = new JLabel(icon);
        frame.add(imageLabel, BorderLayout.CENTER);

        startButton = new JButton("Aceitar Desafio");
        declineButton = new JButton("Continuar");

        startButton.addActionListener(e -> {
            initializeQuestions();
            prepareChallengeGUI();
        });

        declineButton.addActionListener(e -> frame.dispose());

        JPanel introPanel = new JPanel();
        
        introPanel.add(declineButton);
        frame.add(introPanel, BorderLayout.SOUTH);

        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }


   
    
    private static JFrame frame232;
    private static JLabel imageLabel232;
    private static JButton showDocumentsButton232, explainPurposeButton232, proceedButton232;
    private static JTextArea dialogueTextArea232;

    public static void encontroComGuardas() {
    	JFrame frame = new JFrame("Desafio do Mago - Java");
        frame.setSize(600, 600);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setLayout(new BorderLayout());

        JLabel storyLabel = new JLabel("<html><div style='text-align: center;'>Bom dia, serei breve, você precisa vir conosco, precisamos de um homem no moinho, se voce recusar vamos matar você</div></html>", JLabel.CENTER);
        frame.add(storyLabel, BorderLayout.NORTH);

        // Carregando e redimensionando a imagem do velho Joaquim
        ImageIcon originalIcon = new ImageIcon(Encontros.class.getResource("/Fotos/P5.png")); // Confirme o caminho
        Image image = originalIcon.getImage();
        Image newimg = image.getScaledInstance(300, 300, java.awt.Image.SCALE_SMOOTH);
        ImageIcon icon = new ImageIcon(newimg);
        JLabel imageLabel = new JLabel(icon);
        frame.add(imageLabel, BorderLayout.CENTER);

        JButton startButton = new JButton("Não, estou aqui só de passagem");
        JButton declineButton = new JButton("Não, isso não é uma opção");
        JButton surpriseButton = new JButton("Cai para dentro");
        JPanel buttonPanel = new JPanel(); // Criando um painel para os botões
        buttonPanel.add(startButton);
        buttonPanel.add(declineButton); // Corrigindo o nome do botão de recusa para declineButton
        frame.add(buttonPanel, BorderLayout.SOUTH);

        startButton.addActionListener(e -> {
            storyLabel.setText("<html><div style='text-align: center;'>Como assim não!??</div></html>");
            buttonPanel.removeAll(); // Remove todos os botões do painel
            buttonPanel.add(surpriseButton); // Adiciona o novo botão
            frame.validate(); // Valida o frame para atualizar a UI
            frame.repaint(); // Repinta o frame para garantir a atualização
        });

        declineButton.addActionListener(e -> {
        	storyLabel.setText("<html><div style='text-align: center;'>Como assim não!??</div></html>");
            buttonPanel.removeAll(); // Remove todos os botões do painel
            buttonPanel.add(surpriseButton); // Adiciona o novo botão
            frame.validate(); // Valida o frame para atualizar a UI
            frame.repaint(); // Repinta o frame para garantir a atualização
        });
         
        frame.setLocationRelativeTo(null);
        frame.setVisible(true); // Torna o frame visível

    }


    

    public static void encontroComDama() {
        JFrame frame = new JFrame("Desafio do Mago - Java");
        frame.setSize(400, 400);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setLayout(new BorderLayout());

        JLabel storyLabel = new JLabel("<html><div style='text-align: center;'>Eaí bonitão, posso te fazer uma proposta nada descente?</div></html>", JLabel.CENTER);
        frame.add(storyLabel, BorderLayout.NORTH);

        // Carregando e redimensionando a imagem do velho Joaquim
        ImageIcon originalIcon = new ImageIcon(Encontros.class.getResource("/Fotos/P4.png")); // Confirme o caminho
        Image image = originalIcon.getImage();
        Image newimg = image.getScaledInstance(300, 300, java.awt.Image.SCALE_SMOOTH);
        ImageIcon icon = new ImageIcon(newimg);
        JLabel imageLabel = new JLabel(icon);
        frame.add(imageLabel, BorderLayout.CENTER);

        JButton startButton = new JButton("Aceitar Desafio");
        JButton declineButton = new JButton("Recusar Desafio");
        JButton surpriseButton = new JButton("Eu não estava pensando nisso...");
        JPanel buttonPanel = new JPanel(); // Criando um painel para os botões
        buttonPanel.add(startButton);
        buttonPanel.add(declineButton); // Corrigindo o nome do botão de recusa para declineButton
        frame.add(buttonPanel, BorderLayout.SOUTH);

        startButton.addActionListener(e -> {
            storyLabel.setText("<html><div style='text-align: center;'>Ótimo!!! Vamos Batalhar!</div></html>");
            buttonPanel.removeAll(); // Remove todos os botões do painel
            buttonPanel.add(surpriseButton); // Adiciona o novo botão
            frame.validate(); // Valida o frame para atualizar a UI
            frame.repaint(); // Repinta o frame para garantir a atualização
        });

        declineButton.addActionListener(e -> {
            frame.dispose(); // Fecha a janela
        });
        frame.setLocationRelativeTo(null);

        frame.setVisible(true); // Torna o frame visível

    }

    public static void encontroComRei() {
    	JFrame frame = new JFrame("Desafio do Mago - Java");
        frame.setSize(600, 600);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setLayout(new BorderLayout());

        JLabel storyLabel = new JLabel("<html><div style='text-align: center;'>Então Você é aquele que tanto falam?</div></html>", JLabel.CENTER);
        frame.add(storyLabel, BorderLayout.NORTH);

        // Carregando e redimensionando a imagem do velho Joaquim
        ImageIcon originalIcon = new ImageIcon(Encontros.class.getResource("/Fotos/P8.png")); // Confirme o caminho
        Image image = originalIcon.getImage();
        Image newimg = image.getScaledInstance(300, 300, java.awt.Image.SCALE_SMOOTH);
        ImageIcon icon = new ImageIcon(newimg);
        JLabel imageLabel = new JLabel(icon);
        frame.add(imageLabel, BorderLayout.CENTER);

        JButton startButton = new JButton("Eu preciso de algo que você tem");
        JButton declineButton = new JButton("Você sabe por que eu estou aqui");
        JButton surpriseButton = new JButton("Não usaremos diálogos nesta discussão");
        JPanel buttonPanel = new JPanel(); // Criando um painel para os botões
        buttonPanel.add(startButton);
        buttonPanel.add(declineButton); // Corrigindo o nome do botão de recusa para declineButton
        frame.add(buttonPanel, BorderLayout.SOUTH);

        startButton.addActionListener(e -> {
            storyLabel.setText("<html><div style='text-align: center;'>Vamos abrir um diálogo</div></html>");
            buttonPanel.removeAll(); // Remove todos os botões do painel
            buttonPanel.add(surpriseButton); // Adiciona o novo botão
            frame.validate(); // Valida o frame para atualizar a UI
            frame.repaint(); // Repinta o frame para garantir a atualização
        });

        declineButton.addActionListener(e -> {
        	storyLabel.setText("<html><div style='text-align: center;'>Vamos abrir um diálogo</div></html>");
            buttonPanel.removeAll(); // Remove todos os botões do painel
            buttonPanel.add(surpriseButton); // Adiciona o novo botão
            frame.validate(); // Valida o frame para atualizar a UI
            frame.repaint(); // Repinta o frame para garantir a atualização
        });
         
        frame.setLocationRelativeTo(null);
        frame.setVisible(true); // Torna o frame visível

    }

    public static void batalhaContraLadrao() {
        System.out.println("Você é emboscado por um ladrão. Prepare-se para a batalha!");
    }
    
}
