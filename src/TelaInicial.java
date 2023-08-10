import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.sound.sampled.*;
import java.io.File;
import java.io.IOException;

public class TelaInicial extends JFrame implements ActionListener {

    private boolean isPlaying = true;
    private SourceDataLine line;

    public TelaInicial() {
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

        new Thread(() -> reproduzirMusica("imagens/The_Suburbs_-_Arcade_Fire.wav")).start();

        setVisible(true);
    }

private JPanel criarTelaInicialPanel() {
        // Criando painel de menu inicial
        JPanel Telamenuinicial = new JPanel() {
            // Sobrescreva o método paintComponent para desenhar a imagem de fundo
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                String caminhoDati = "imagens/WhatsApp Image 2023-07-30 at 01.18.49.jpeg";
                ImageIcon imagemti = new ImageIcon(caminhoDati);
                Image img = imagemti.getImage();
                g.drawImage(img, 0, 0, getWidth(), getHeight(), this);
            }
        };
        Telamenuinicial.setLayout(null); //para editar a localização dos botões livremente


        // Criando os botões do menu inicial:
        String caminhonj = "imagens/pngwing.com.png";
        ImageIcon fotonj = new ImageIcon(caminhonj);
        JButton novoJogoButton = new JButton(fotonj);
        novoJogoButton.setLocation(250, 250);
        novoJogoButton.setSize(110,50);
        novoJogoButton.setContentAreaFilled(false);
        novoJogoButton.setBorderPainted(false);
        String caminhoso = "imagens/59060c290cbeef0acff9a659 (1).png";
        ImageIcon fotoso = new ImageIcon(caminhoso);
        JButton sobreButton = new JButton(fotoso);
        sobreButton.setBounds(220, 310, 160, 50);
        sobreButton.setContentAreaFilled(false);
        sobreButton.setBorderPainted(false);
        String caminhosa = "imagens/ir.png";
        ImageIcon fotosa = new ImageIcon(caminhosa);
        JButton sairButton = new JButton(fotosa);
        sairButton.setBounds(270, 370, 50, 50);
        sairButton.setContentAreaFilled(false);
        sairButton.setBorderPainted(false);
        JButton musicButton = new JButton("Musga");
        musicButton.setBounds(10, 5, 50, 50);

        // Criando um novo painel para o título e centralizando:
        String caminhoDaImagem = "imagens/dc3d2c5cc28562c174703cded1ed335e.png";
        ImageIcon imagemIcone = new ImageIcon(caminhoDaImagem);
        JLabel labelTitulo = new JLabel(imagemIcone);
        labelTitulo.setBounds(40, 50, 500, 99);
        Telamenuinicial.add(labelTitulo);
    
        // ActionListeners
        novoJogoButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent event) {
                new TelaJogo();
            }
        });
        // ActionListener do botão sobre:
        sobreButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent event) {
                new ExibirCreditos();
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

    private void reproduzirMusica(String filePath) {
        try {
            File audioFile = new File(filePath);
            AudioInputStream audioStream = AudioSystem.getAudioInputStream(audioFile);
            AudioFormat format = audioStream.getFormat();
            DataLine.Info info = new DataLine.Info(SourceDataLine.class, format);

            if (!AudioSystem.isLineSupported(info)) {
                System.err.println("Line not supported");
                return;
            }

            line = (SourceDataLine) AudioSystem.getLine(info);
            line.open(format);
            line.start();

            int bufferSize = 4096;
            byte[] buffer = new byte[bufferSize];
            int bytesRead = 0;

            while (isPlaying && bytesRead != -1) {
                bytesRead = audioStream.read(buffer, 0, buffer.length);
                if (bytesRead >= 0) {
                    line.write(buffer, 0, bytesRead);
                }
            }

            line.drain();
            line.close();
            audioStream.close();
        } catch (LineUnavailableException | IOException | UnsupportedAudioFileException e) {
            e.printStackTrace();
        }
    }

    private void paraControleMusica() {
        if (isPlaying) {
            isPlaying = false;
            if (line != null) {
                line.stop();
                line.close();
            }
        } else {
            isPlaying = true;
            if (line != null) {
                line.start();
            }
        }
    }


    public static void main(String[] args) {
        try {
            // Define o Look and Feel Windows (para sistemas Windows)
            UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
        } catch (Exception e) {
            e.printStackTrace();
        }

        SwingUtilities.invokeLater(() -> new TelaInicial());
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'actionPerformed'");
    }
}