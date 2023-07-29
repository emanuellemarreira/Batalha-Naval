import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;
import javax.swing.border.Border;

public class Main extends JFrame implements ActionListener {
    private Container caixa;
    private JPanel tabuleiro, info;
    private Random gerador;

    public Main() {
        // Criando a janela do menu inicial:
        setTitle("BATALHA NAVAL");
        setBounds(300, 300, 300, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // Criando painel de menu inicial
        JPanel Telamenuinicial = new JPanel();
        Telamenuinicial.setLayout(new GridLayout(5, 1)); // Aumente para 5 para adicionar espaço extra

        // Criando os botões do menu inicial:
        JButton novoJogoButton = new JButton("Novo Jogo");
        JButton sobreButton = new JButton("Sobre");
        JButton sairButton = new JButton("Sair");

        // Criando um novo painel para o título e centralizando:
        JPanel tituloPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        JLabel titulo = new JLabel("BATALHA NAVAL");
        tituloPanel.add(titulo);

        // ActionListener do botão iniciar:
        novoJogoButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent event) {
                iniciarJogo();
            }
        });
        // ActionListener do botão sobre:
        sobreButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent event) {
                exibirCreditos();
            }
        });

        //ActionListener do botão sair:
        sairButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent event) {
                System.exit(0);
            }
        });

        // Adicionando o painel e os botões na janela do menu inicial:
        Telamenuinicial.add(new JPanel()); // Espaço extra antes do título
        Telamenuinicial.add(tituloPanel);
        Telamenuinicial.add(novoJogoButton);
        Telamenuinicial.add(sobreButton);
        Telamenuinicial.add(sairButton);
        setContentPane(Telamenuinicial);
        setVisible(true);
    }

    // Método: Tela de créditos (Ainda será aprimorada)
    public void exibirCreditos() {
        JFrame janelacreditos = new JFrame("Creditos");
        janelacreditos.setBounds(300, 300, 300, 300);
        janelacreditos.setLayout(new BorderLayout());

        JTextArea oi = new JTextArea("Alunos: \nJonatan\nJoão\nManu\nMoi\nBia\nItalo 1\nItalo 2\nDanilo");
        janelacreditos.add(oi, BorderLayout.CENTER);

        janelacreditos.setVisible(true);
    }



    public static void main(String[] args) {
        try {
            // Define o Look and Feel Windows (para sistemas Windows)
            UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
        } catch (Exception e) {
            e.printStackTrace();
        }

        SwingUtilities.invokeLater(() -> new Main());
    }



    // Método Iniciar Jogo - Abre o tabuleiro, ativado pelo Action Listener do botão iniciar:
    public void iniciarJogo() {
        // Fecha a janela da tela inicial:
        dispose();

        // Criação da Tela do Jogo:
        new TelaJogoPrincipal();
    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }

    // Tela do Jogo Principal
    public class TelaJogoPrincipal extends JFrame implements ActionListener {
        private Container caixa;
        private JPanel tabuleiro, info;
        private Random gerador;

        public TelaJogoPrincipal() {
            setTitle("BATALHA NAVAL");
            setBounds(300, 300, 300, 300);
            setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            setLayout(new BorderLayout());

            // Criando container (onde vão ficar botões, rótulos, painéis, etc)
            caixa = getContentPane();

            // Inicialize o painel tabuleiro antes de definir a borda
            tabuleiro = new JPanel();

            // Defina a borda do painel tabuleiro
            Border bordaSimples = BorderFactory.createLineBorder(Color.BLACK);
            tabuleiro.setBorder(bordaSimples);

            tabuleiro.setLayout(new GridLayout(5, 10));

            // Criando gerador de numero aleatório
            gerador = new Random();

            // Criando mapa (0 e 1) onde os navios estarão
            int[][] mapanavios = new int[5][5];
            int contanavios = 0;

            // Garantir que nunca vão ser gerados endereços iguais para dois navios
            // Pode ser otimizado futuramente
            int coluna1 = -1;
            int coluna2 = -1;
            int linha1 = -1;
            int linha2 = -1;
            for (int l = 0; l < 3; l++) {
                int chave1 = gerador.nextInt(4) % 5;
                int chave2 = gerador.nextInt(4) % 5;

                if (l == 0) {
                    coluna1 = chave1;
                    linha1 = chave2;
                } else if (l == 1) {
                    while (chave1 == coluna1 && chave2 == linha1) {
                        chave1 = gerador.nextInt(4) % 5;
                        chave2 = gerador.nextInt(4) % 5;
                    }
                    coluna2 = chave1;
                    linha2 = chave2;
                } else if (l == 2) {
                    while ((chave1 == coluna1 && chave2 == linha1) || (chave1 == coluna2 && chave2 == linha2)) {
                        chave1 = gerador.nextInt(4) % 5;
                        chave2 = gerador.nextInt(4) % 5;
                    }
                }

                for (int i = 0; i < 5; i++) {
                    for (int j = 0; j < 5; j++) {
                        if (contanavios == 3) {
                            break;
                        }

                        if (i == chave1 && j == chave2) {
                            mapanavios[i][j] = 1;
                            contanavios++;
                        }
                    }
                }
            }

            // Criar matriz de botões
            JButton[][] botoes = new JButton[5][5];

            // Configurando os botões
            for (int i = 0; i < 5; i++) {
                for (int j = 0; j < 5; j++) {
                    JButton b = new JButton("~");
                    botoes[i][j] = b;
                    tabuleiro.add(b);
                    b.putClientProperty("temNavio", mapanavios[i][j]);
                    b.addActionListener(this);
                }
            }

            info = new JPanel();
            info.setLayout(new FlowLayout(FlowLayout.CENTER));
            JLabel teste = new JLabel("teste");
            info.add(teste);

            caixa.add(tabuleiro, BorderLayout.CENTER);
            caixa.add(info, BorderLayout.SOUTH);

            setVisible(true);
        }
        @Override
        public void actionPerformed(ActionEvent e) {
            JButton botaoClicado = (JButton) e.getSource();
            if ((int) botaoClicado.getClientProperty("temNavio") == 1) {
                botaoClicado.setText("*");
            } else {
                botaoClicado.setText("X");
            }
        }

        public static void main(String[] args) {
            // Define o design das telas:
            try {
                // Define o Look and Feel Windows (para sistemas Windows):
                UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
            } catch (Exception e) {
                e.printStackTrace();
            }

            //Inicia as definições da interface gráfica GUI antes de chamar a classe Main():
            SwingUtilities.invokeLater(() -> new Main());
        }
    }
}
