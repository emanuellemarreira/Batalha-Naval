import java.awt.*;
import java.util.Random;
import java.awt.event.*;
import javax.swing.*;
import java.net.URL;
import javax.imageio.ImageIO;
import java.awt.Dimension;

public class TelaJogo extends JFrame implements ActionListener {
    JLabel informacoes;
    int QuantidadeDeJogadas = 0; //quantidade máxima de jogadas
    int VezesJogadas = 0;
    private EscolherDificuldade escolherDificuldade;
    public TelaJogo(EscolherDificuldade escolherDificuldade) {
    	this.escolherDificuldade = escolherDificuldade;
        if( escolherDificuldade.getDificuldade() == 1) {
            QuantidadeDeJogadas = 15;
        }
        if(escolherDificuldade.getDificuldade() == 2) {
            QuantidadeDeJogadas = 10;
        }
        if(escolherDificuldade.getDificuldade() == 3) {
            QuantidadeDeJogadas = 7;
        }

        // Criando container (onde vão ficar botões, rótulos, painéis, etc)
        Container caixa = getContentPane();
        caixa.setLayout(new BorderLayout());

        // Carregar imagem de fundo a partir de URL
        String urlImagemDeFundo = "https://i.imgur.com/GS7tI7Q.png"; // Substitua pela URL real
        ImageIcon imagemDeFundo = createImageIconFromURL(urlImagemDeFundo);
        BackgroundPanel painelDeFundo = new BackgroundPanel(imagemDeFundo.getImage());
        caixa.add(painelDeFundo);

        // Carregar ícones a partir de URLs
        ImageIcon aguaIcon = createImageIconFromURL("https://i.imgur.com/mLXvLKQ.jpg");    // Ícone para água (não contém navio)
        ImageIcon navioIcon = createImageIconFromURL("https://i.imgur.com/KjpIXx8.jpg");   // Ícone para navio
        ImageIcon tiroIcon = createImageIconFromURL("https://i.imgur.com/XkECuvd.jpg");   // Ícone para tiro (clicou, não tem navio)

        // Configurações da janela
        setTitle("Batalha Naval");
        setSize(600, 600);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setResizable(false);
        setUndecorated(false);

        // Criando tabuleiro
        JPanel tabuleiro = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                String caminhoDatj = "imagens/Telajo.png";
                ImageIcon imagemtj = new ImageIcon(caminhoDatj);
                Image img = imagemtj.getImage();
                g.drawImage(img, 0, 0, getWidth(), getHeight(), this);
            }
        };
        tabuleiro.setLayout(new GridBagLayout());

        // Configurando a caixa para ter um layout vertical (topo para baixo)
        caixa.setLayout(new BoxLayout(caixa, BoxLayout.PAGE_AXIS));

        // Adicionando os componentes na caixa de conteúdo
        caixa.add(tabuleiro);
        caixa.add(painelDeFundo);
        caixa.setLayout(new FlowLayout(FlowLayout.CENTER));


        // Criando gerador de número aleatório
        Random gerador = new Random();

        // Criando mapa (0 e 1) onde os navios estarão
        int[][] mapanavios = new int[5][5];
        int contanavios = 0;

            //Garantir que nunca vão ser gerados endereços iguais para dois navios
            //Pode ser otimizado futuramente
            int coluna1=-1;
            int coluna2=-1;
            int linha1=-1;
            int linha2=-1;
            for (int l =0; l <3; l++) {
                int chave1 = gerador.nextInt(4) % 5;
                int chave2 = gerador.nextInt(4) % 5;

                if (l == 0) {
                    coluna1 = chave1;
                    linha1 = chave2;
                }

                else if (l == 1) {
                    while (chave1 == coluna1 && chave2 == linha1) {
                        chave1 = gerador.nextInt(4) % 5;
                        chave2 = gerador.nextInt(4) % 5;}
                    coluna2 = chave1;
                    linha2 = chave2;
                }

                else if(l == 2) {
                    while ((chave1 == coluna1 && chave2 == linha1) || (chave1 == coluna2 && chave2 == linha2)) {
                        chave1 = gerador.nextInt(4) % 5;
                        chave2 = gerador.nextInt(4) % 5;}
                }

                for (int i = 0; i<5; i++) {
                    for (int j = 0; j<5; j++) {
                        if(contanavios == 3) {
                            break;
                        }

                        if (i == chave1 && j == chave2) {
                            mapanavios[i][j] = 1;
                            contanavios++;
                        }
                    }
                }
            }

        // Configurando a caixa para ter um layout vertical (topo para baixo)
        caixa.setLayout(new BoxLayout(caixa, BoxLayout.Y_AXIS));
        // GridBagConstraints fornece as coordenadas de cada botão
        tabuleiro.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.BOTH;

        // matriz de botões
        JToggleButton[][] botoes = new JToggleButton[5][5];
        informacoes = new JLabel("Aperte em uma das casas");

        // configurando os botões
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                JToggleButton b = new JToggleButton(aguaIcon);
                b.setContentAreaFilled(false);
                b.setBorderPainted(false);
                botoes[i][j] = b;
                gbc.gridx = i;
                gbc.gridy = j;
                gbc.weightx = 0; // Deixar a matriz de botões unida
                gbc.weighty = 0; // Deixar a matriz de botões unida
                botoes[i][j].putClientProperty("temNavio", mapanavios[i][j]);
                Dimension buttonSize = new Dimension(50, 50);
                b.setPreferredSize(buttonSize);
                b.setMaximumSize(buttonSize);
                b.setMinimumSize(buttonSize);
                tabuleiro.add(botoes[i][j], gbc);
                b.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        JToggleButton botaoClicado = (JToggleButton) e.getSource();
                        if (VezesJogadas >= QuantidadeDeJogadas) {
                            return;
                        }
                        int coluna = -1;
                        int linha = -1;
                        for (int i = 0; i < 5; i++) {
                            for (int j = 0; j < 5; j++) {
                                if (botoes[i][j] == botaoClicado) {
                                    coluna = j;
                                    linha = i;
                                    break;
                                }
                            }
                        }
                        int naviosNaLinha = 0;
                        int naviosNaColuna = 0;
                        for (int i = 0; i < 5; i++) {
                            if (mapanavios[coluna][i] == 1) {
                                naviosNaLinha++;
                            }
                            if (mapanavios[i][linha] == 1) {
                                naviosNaColuna++;
                            }
                        }
                        if ((int) botaoClicado.getClientProperty("temNavio") == 1) {
                            botaoClicado.setIcon(navioIcon);
                        } else {
                            botaoClicado.setIcon(tiroIcon);
                        }
                        informacoes.setText("Há " + naviosNaColuna + " navio(s) na coluna e " + naviosNaLinha + " navio(s) nessa linha");
                        botaoClicado.setEnabled(false);
                        botaoClicado.requestFocus();
                        VezesJogadas++; // sobe o contador de vezes jogadas

                        if (VezesJogadas >= QuantidadeDeJogadas) { //mensagem quando a quantidade de jogadas atingirem o limite
                            informacoes.setText("Fim do jogo! Você atingiu a quantidade máxima de jogadas.");
                    }

                }});

                tabuleiro.setVisible(true);
            }

        }
        JPanel info = new JPanel();
        caixa.setLayout(new GridLayout(5, 5));
        caixa.add(tabuleiro);
        caixa.add(info);
        caixa.add(painelDeFundo);
        info.setLayout(new FlowLayout(FlowLayout.CENTER));
        info.add(informacoes);


        // Criando um painel que conterá o tabuleiro e as informações
        JPanel contentPane = new JPanel(new BorderLayout());
        contentPane.add(tabuleiro, BorderLayout.CENTER);
        contentPane.add(info, BorderLayout.SOUTH);

        // Definindo o painel de conteúdo como o conteúdo da janela
        setContentPane(caixa);
        setContentPane(contentPane);

        setVisible(true);
        painelDeFundo.setVisible(true);
    }

    private ImageIcon createImageIconFromURL(String url) {
        try {
            URL imageURL = new URL(url);
            return new ImageIcon(ImageIO.read(imageURL));
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static void main(String args[]) {
    	TelaInicial telaInicial = new TelaInicial();
    	EscolherDificuldade escolherDificuldade = new EscolherDificuldade(telaInicial);
        SwingUtilities.invokeLater(() -> new TelaJogo(escolherDificuldade));
    }

    @Override
    public void actionPerformed(ActionEvent e) {
    }
}


class BackgroundPanel extends JPanel {
    private final Image backgroundImage;

    public BackgroundPanel(Image image) {
        this.backgroundImage = image;
    }


    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (backgroundImage != null) {
            int x = (getWidth() - backgroundImage.getWidth(this)) ;
            int y = (getHeight() - backgroundImage.getHeight(this)) ;
            g.drawImage(backgroundImage, x, y, this);
        }
    }}