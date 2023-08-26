import java.awt.*;
import java.util.Random;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ImageIcon;
import java.net.URL;
import javax.imageio.ImageIO;
import java.awt.Dimension;


import javax.swing.*;
// A classe Tela jogo extende a classe JFrame o que significa que vamos criar uma janela
// A classe Tela jogo implementa ActionListener para ser capaz de responder à ações como clicar
public class TelaJogo extends JFrame implements ActionListener {
    JLabel informacoes;

    public TelaJogo() {
        // Carregar ícones a partir de URLs
        ImageIcon aguaIcon = createImageIconFromURL("https://i.imgur.com/mLXvLKQ.jpg");    // Ícone para água (não contém navio)
        ImageIcon navioIcon = createImageIconFromURL("https://i.imgur.com/KjpIXx8.jpg");   // Ícone para navio
        ImageIcon tiroIcon = createImageIconFromURL("https://i.imgur.com/XkECuvd.jpg");     // Ícone para tiro (clicou, não tem navio)

        // Configurações da janela
        setTitle("Batalha Naval");
        setSize(600, 600);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setResizable(false);
        setUndecorated(true);

        //Criando container (onde vão ficar botões, rotulos, paineis, etc)
        Container caixa = getContentPane();
        OverlayLayout overlayLayout = new OverlayLayout(caixa);
        caixa.setLayout(overlayLayout);

        //Criando tabuleiro
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

        //Criando gerador de numero aleatorio
        Random gerador = new Random();

        //Criando mapa (0 e 1) onde os navios estarão
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
        // GridBagConstraints fornece as coodenadas de cada botão
        tabuleiro.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.BOTH;

        //matriz de botoes
        JToggleButton[][] botoes = new JToggleButton[5][5];
        informacoes = new JLabel("Aperte em uma das casas");
        //configurandos os botoes
        for (int i = 0; i<5; i++) {
            for (int j = 0; j<5; j++) {
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
                        int coluna = -1;
                        int linha = -1;
                        for (int i = 0; i < 5; i++) {
                            for (int j = 0; j < 5; j++) {
                                if (botoes[i][j] == botaoClicado) {
                                    coluna = i;
                                    linha = j;
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
                        }  else {
                            botaoClicado.setIcon(tiroIcon);
                        }
                        informacoes.setText("Há "+ naviosNaColuna + " navio(s) na coluna e " + naviosNaLinha+ " navio(s) nessa linha");
                    }

                });
            }
        }


        janela.pack(); // Faz a janela se ajustar automaticamente ao tamanho dos componentes
        janela.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        janela.setVisible(true);

        JPanel info = new JPanel();
        info.setLayout(new FlowLayout(FlowLayout.CENTER));
        info.add(informacoes);

        caixa.setLayout(new GridLayout(2,1));
        caixa.add(tabuleiro);
        caixa.add(info);

        setVisible(true);
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
        new TelaJogo();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'actionPerformed'");
    }
}
