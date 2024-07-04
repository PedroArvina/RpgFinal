package Mapas;

import java.awt.BorderLayout;
import java.awt.Image;
import java.util.ArrayList;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

import Construtor.Tabuleiro;
import Construtor2.Tabuleiro;
import Construtor1.Tabuleiro;
import Construtor3.Tabuleiro;

public class EncontrosArido {
	
	
	
	

    private static JFrame frame;
    private static JLabel questionLabel, imageLabel;
    private static JButton yesButton, noButton, startButton, declineButton;
    private static List<Question> questions;
    private static int currentQuestionIndex = 0;
    private static int score = 0;

    public static void main(String[] args) {
        SwingUtilities.invokeLater(EncontrosArido::encontroComGolem);
    }

    public static void encontroComGolem() {
        prepareIntroGUI();
    }

    private static void prepareIntroGUI() {
        frame = new JFrame("Desafio do Golem - Reflexões sobre a Vida");
        frame.setSize(600, 600);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setLayout(new BorderLayout());

        JLabel storyLabel = new JLabel("<html><div style='text-align: center;'>Olá, viajante! Eu sou um Golem sábio, guardião de segredos antigos. Responda ao meu quiz reflexivo para provar sua sabedoria. Está pronto para o desafio?</div></html>", JLabel.CENTER);
        frame.add(storyLabel, BorderLayout.NORTH);

        // Carregando e redimensionando a imagem do golem
        ImageIcon originalIcon = new ImageIcon(EncontrosArido.class.getResource("/Fotos/A14.png")); // Assegure que o caminho está correto
        Image image = originalIcon.getImage();
        Image newimg = image.getScaledInstance(200, 200, java.awt.Image.SCALE_SMOOTH);
        ImageIcon icon = new ImageIcon(newimg);
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

        frame.add(imageLabel, BorderLayout.NORTH);

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

        String resultMessage = score >= 8 ? "<html><div style='text-align: center;'>Você se mostrou sábio e digno. O maior tesouro que posso te dar é este conhecimento.</div></html>"
                                         : "<html><div style='text-align: center;'>Você ainda tem muito a aprender. Continue buscando o conhecimento.</div></html>";

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
        questions.add(new Question("Se você alimentá-lo, ele vive; se você der água, ele morre. É verdadeiro?", true)); // Resposta: verdadeiro (fogo)
        questions.add(new Question("Um homem pode casar com a irmã da sua viúva?", false)); // Resposta: falso (ele estaria morto)
        questions.add(new Question("Pode um burro chamar outro burro de burro?", true)); // Resposta: verdadeiro (eles não falam, mas podem bramir)
        questions.add(new Question("Você pode segurar sua respiração até morrer?", false)); // Resposta: falso (os instintos assumem o controle)
        questions.add(new Question("Você pode fazer um quadrado com três linhas retas?", false)); // Resposta: falso
        questions.add(new Question("Um ovo pode flutuar em água salgada?", true)); // Resposta: verdadeiro
        questions.add(new Question("O reflexo de um espelho pode mentir?", false)); // Resposta: falso (um espelho reflete exatamente o que vê)
        questions.add(new Question("Se um avião cair na fronteira entre dois países, os sobreviventes são enterrados no local?", false)); // Resposta: falso (sobreviventes não são enterrados)
        questions.add(new Question("Um balão pode flutuar no espaço?", false)); // Resposta: falso (não há ar no espaço)
        questions.add(new Question("Você pode ver uma sombra à noite sem nenhuma luz?", false)); // Resposta: falso (sombras precisam de luz)
    }


    static class Question {
        String text;
        boolean answer;

        Question(String text, boolean answer) {
            this.text = text;
            this.answer = answer;
        }
    }
    
    private static void updateButtons(JPanel buttonPanel, String[] options, JFrame frame, JLabel storyLabel) {
        buttonPanel.removeAll(); // Remove todos os componentes anteriores

        // Cria novos botões com base nas opções fornecidas
        for (String option : options) {
            JButton button = new JButton(option);
            button.addActionListener(e -> {
                // Aqui você pode definir a ação específica para cada botão
                if (option.equals("Porque compartilhamos um inimigo comum.")) {
                    storyLabel.setText("<html><div style='text-align: center;'>A Feiticeira considera sua proposta com um olhar pensativo...</div></html>");
                    // Possivelmente adicionar mais lógica ou iniciar uma batalha
                    new Construtor4.Tabuleiro(); // Supondo que essa linha deva ser chamada aqui
                } else if (option.equals("Por minha honra, eu sirvo ao bem maior!")) {
                    storyLabel.setText("<html><div style='text-align: center;'>A Feiticeira sorri ironicamente, 'Honra? Vamos ver como você se defende!'</div></html>");
                    new Construtor4.Tabuleiro(); // Supondo que essa linha deva ser chamada aqui
                } else {
                    storyLabel.setText("<html><div style='text-align: center;'>A Feiticeira parece intrigada com sua oferta...</div></html>");
                    // Adicione qualquer lógica adicional ou chamadas de função aqui
                }
                frame.repaint(); // Atualiza a janela após adicionar os novos botões
            });
            buttonPanel.add(button); // Adiciona o novo botão ao painel
        }

        buttonPanel.revalidate();
        buttonPanel.repaint(); // Redesenha o painel para mostrar os novos botões
    }

    
    public static void BatalhaFinal() {
        // Configuração inicial da janela
        JFrame frame = new JFrame("Desafio Final com a Feiticeira");
        frame.setSize(600, 600);
        frame.setUndecorated(true); // Remove a decoração da janela
        frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE); // Previne fechamento padrão
        frame.setLayout(new BorderLayout());
        frame.setResizable(false); // Impede redimensionamento da janela
        frame.setLocationRelativeTo(null); // Centraliza a janela

        // Configuração do rótulo de história
        JLabel storyLabel = new JLabel("<html><div style='text-align: center;'>Você alcançou a caverna escura onde a Feiticeira espera. Ela sorri, 'Você finalmente chegou, herói. Qual será seu destino agora?'</div></html>", JLabel.CENTER);
        frame.add(storyLabel, BorderLayout.NORTH);

        // Carregamento e configuração da imagem
        ImageIcon originalIcon = new ImageIcon(EncontrosArido.class.getResource("/Fotos/A15.png"));
        Image image = originalIcon.getImage();
        Image newimg = image.getScaledInstance(300, 300, Image.SCALE_SMOOTH);
        ImageIcon icon = new ImageIcon(newimg);
        JLabel imageLabel = new JLabel(icon);
        frame.add(imageLabel, BorderLayout.CENTER);

        // Botões de interação
        
        JButton option2 = new JButton("Lutar até o fim!");
        

        // Adicionando botões ao painel
        JPanel buttonPanel = new JPanel();
        
        buttonPanel.add(option2);
        
        frame.add(buttonPanel, BorderLayout.SOUTH);

       

        option2.addActionListener(e -> {
            storyLabel.setText("<html><div style='text-align: center;'>A Feiticeira ergue a mão, 'Então seja, herói! Prepare-se para a batalha!'</div></html>");
            new Construtor5.Tabuleiro(); 
        });

        

        // Tornando a janela visível
        frame.setVisible(true);
    }
    
    public static void encontroNoDeserto() {
        // Configuração inicial da janela
    	JFrame frame = new JFrame("Desafio do Papaco");
        frame.setSize(810, 810);
        frame.setUndecorated(true); // Remove a decoração da janela
        frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE); // Impede que a janela seja fechada pelo usuário
        frame.setLayout(new BorderLayout());
        frame.setResizable(false); // Impede que a janela seja redimensionada
        frame.setLocationRelativeTo(null); // Centraliza a janela na tela

        // Configuração do rótulo de história
        JLabel storyLabel = new JLabel("<html><div style='text-align: center;'>Algum problema amigo? Meu nome é Papaco um experiente ladrão do deserto. Entregue seus pertences para nós, somos um grupo grande!</div></html>", JLabel.CENTER);
        frame.add(storyLabel, BorderLayout.NORTH);

        // Carregamento e configuração da imagem
        ImageIcon originalIcon = new ImageIcon(EncontrosArido.class.getResource("/Fotos/A13.png"));
        Image image = originalIcon.getImage();
        Image newimg = image.getScaledInstance(300, 300, Image.SCALE_SMOOTH);
        ImageIcon icon = new ImageIcon(newimg);
        JLabel imageLabel = new JLabel(icon);
        frame.add(imageLabel, BorderLayout.CENTER);

        // Botões de interação
        JButton startButton = new JButton("Eu já vi essa história, ela não acaba bem");
        JButton surpriseButton = new JButton("Vamos lá, estou pronto!");

        // Adicionando botões ao painel
        JPanel buttonPanel = new JPanel();
        buttonPanel.add(startButton);
        frame.add(buttonPanel, BorderLayout.SOUTH);

        // Listeners para os botões
        startButton.addActionListener(e -> {
            storyLabel.setText("<html><div style='text-align: center;'>Vamos te colocar nesse caixão</div></html>");
            buttonPanel.removeAll();
            buttonPanel.add(surpriseButton);
            buttonPanel.revalidate();
            buttonPanel.repaint();
        });

        surpriseButton.addActionListener(e -> {
            // Verifique a execução correta desta função
            new Construtor4.Tabuleiro(); 
            frame.dispose();
        });

        // Tornando a janela visível
        frame.setVisible(true);
    }





    

    

    

    
}
