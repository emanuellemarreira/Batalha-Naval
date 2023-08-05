import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Main extends JFrame implements ActionListener {

    public Main() {
        // Criando a janela do menu inicial:
        setTitle("BATALHA NAVAL");
        setBounds(300, 300, 600, 600);
        setUndecorated(true);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        setExtendedState(JFrame.NORMAL);

        // Crie um JTabbedPane
        JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
        tabbedPane.setBorder(BorderFactory.createEmptyBorder());

        // Crie os painéis para as abas do JTabbedPane
        JPanel telaInicialPanel = criarTelaInicialPanel();

        // Adicione os painéis como abas do JTabbedPane
        tabbedPane.addTab(null, telaInicialPanel);

        // Adicione o JTabbedPane à janela
        add(tabbedPane);

        setVisible(true);
    }

    private JPanel criarTelaInicialPanel() {
        // Criando painel de menu inicial
        JPanel Telamenuinicial = new JPanel() {
            // Sobrescreva o método paintComponent para desenhar a imagem de fundo
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                String caminhoDati = "C:\\Users\\Elizabeth\\Documents\\GIT\\ProjetoPP\\Fotos\\WhatsApp Image 2023-07-30 at 01.18.49.jpeg";
                ImageIcon imagemti = new ImageIcon(caminhoDati);
                Image img = imagemti.getImage();
                g.drawImage(img, 0, 0, getWidth(), getHeight(), this);
            }
        };
        Telamenuinicial.setLayout(null); //para editar a localização dos botões livremente


        // Criando os botões do menu inicial:
        String caminhonj = "C:\\Users\\Elizabeth\\Documents\\GIT\\ProjetoPP\\Fotos\\pngwing.com.png";
        ImageIcon fotonj = new ImageIcon(caminhonj);
        JButton novoJogoButton = new JButton(fotonj);
        novoJogoButton.setLocation(250, 250);
        novoJogoButton.setSize(110,50);
        novoJogoButton.setContentAreaFilled(false);
        novoJogoButton.setBorderPainted(false);
        String caminhoso = "C:\\Users\\Elizabeth\\Documents\\GIT\\ProjetoPP\\Fotos\\59060c290cbeef0acff9a659 (1).png";
        ImageIcon fotoso = new ImageIcon(caminhoso);
        JButton sobreButton = new JButton(fotoso);
        sobreButton.setBounds(220, 310, 160, 50);
        sobreButton.setContentAreaFilled(false);
        sobreButton.setBorderPainted(false);
        String caminhosa = "C:\\Users\\Elizabeth\\Documents\\GIT\\ProjetoPP\\Fotos\\ir.png";
        ImageIcon fotosa = new ImageIcon(caminhosa);
        JButton sairButton = new JButton(fotosa);
        sairButton.setBounds(270, 370, 50, 50);
        sairButton.setContentAreaFilled(false);
        sairButton.setBorderPainted(false);

        // Criando um novo painel para o título e centralizando:
        String caminhoDaImagem = "C:\\Users\\Elizabeth\\Documents\\GIT\\ProjetoPP\\Fotos\\dc3d2c5cc28562c174703cded1ed335e.png";
        ImageIcon imagemIcone = new ImageIcon(caminhoDaImagem);
        JLabel labelTitulo = new JLabel(imagemIcone);
        labelTitulo.setBounds(40, 50, 500, 99);
        Telamenuinicial.add(labelTitulo);

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

        // ActionListener do botão sair:
        sairButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent event) {
                System.exit(0);
            }
        });

        // Adicionando os botões ao painel de menu inicial:
        Telamenuinicial.add(novoJogoButton);
        Telamenuinicial.add(sobreButton);
        Telamenuinicial.add(sairButton);


        return Telamenuinicial;
    }


    private void iniciarJogo() {
        // Coloque o código para iniciar o jogo aqui
    }

        public static void exibirCreditos() {
            JFrame janelacreditos = new JFrame("Créditos");
            janelacreditos.setSize(300, 300);
            janelacreditos.setUndecorated(true);
            janelacreditos.setResizable(false);
            janelacreditos.setExtendedState(JFrame.NORMAL);
            janelacreditos.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            janelacreditos.setLayout(null);

            String texto = "<html><div style='text-align: center; height: 100%; display: flex; flex-direction: column; justify-content: center;'>Jogo feito por:<br>Beatriz Guedes da Silva<br>Camila Vasconcelos Moi<br>Danilo Silva<br>Emanuelle Rocha Marreira<br>Gabriel Cortez de São Paulo Rozeno<br>Italo Ferreira Fonseca<br>Italo Guilherme Monte<br>João Vitor Silva de Carvalho<br>Jonatan da Silva Frota<br>Alunos de Engenharia de Computação pela UEA -<br>Universidade Estadual do Amazonas</div></html>";

            JLabel label = new JLabel(texto);
            label.setBounds(55, 20, 200, 200);

            JButton voltarButton = new JButton("Voltar");
            voltarButton.setBounds(120, 250, 70,20);
            voltarButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent event) { janelacreditos.dispose(); }
            });
            janelacreditos.add(voltarButton);

            janelacreditos.add(label);

            janelacreditos.setLocationRelativeTo(null); // Centralizar a janela
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

    @Override
    public void actionPerformed(ActionEvent e) {
        // Implemente as ações dos botões aqui, se necessário
    }
}
