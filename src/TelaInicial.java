import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

public class TelaInicial extends JFrame implements ActionListener {
    private boolean isPlaying = true;
    private JToggleButton musicButton;
    private ImageIcon fotoMusicaTocando;
    private ImageIcon fotoMusicaPausada;
    private boolean isMusicPlaying = false;

    public TelaInicial() {
        setTitle("BATALHA NAVAL");
        setBounds(300, 300, 600, 600);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        setUndecorated(true);
        setExtendedState(JFrame.NORMAL);

        JPanel telaInicialPanel = criarTelaInicialPanel();
        add(telaInicialPanel);

        setVisible(true);
        reproduzirMusica("imagens/The_Suburbs_-_Arcade_Fire.wav");
    }

    private JPanel criarTelaInicialPanel() {
        JPanel Telamenuinicial = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                String caminhoDati = "imagens/TelaInicio.jpg";
                ImageIcon imagemti = new ImageIcon(caminhoDati);
                Image img = imagemti.getImage();
                g.drawImage(img, 0, 0, getWidth(), getHeight(), this);
            }
        };
        Telamenuinicial.setLayout(null); //para editar a localização dos botões livremente


        // Criando os botões do menu inicial:
        String caminhonj = "imagens/Botaojo.jpeg";
        ImageIcon fotonj = new ImageIcon(caminhonj);
        JButton novoJogoButton = new JButton(fotonj);
        novoJogoButton.setLocation(250, 250);
        novoJogoButton.setSize(100,50);
        novoJogoButton.setContentAreaFilled(false);
        novoJogoButton.setBorderPainted(false);
        String caminhoso = "imagens/Botaoso.jpeg";
        ImageIcon fotoso = new ImageIcon(caminhoso);
        JButton sobreButton = new JButton(fotoso);
        sobreButton.setBounds(250, 310, 100, 50);
        sobreButton.setContentAreaFilled(false);
        sobreButton.setBorderPainted(false);
        String caminhosa = "imagens/BotaoSai.jpeg";
        ImageIcon fotosa = new ImageIcon(caminhosa);
        JButton sairButton = new JButton(fotosa);
        sairButton.setBounds(250, 430, 100, 50);
        sairButton.setContentAreaFilled(false);
        sairButton.setBorderPainted(false);
        String caminhocj = "imagens/Botaocj.jpeg";
        ImageIcon fotocj = new ImageIcon(caminhocj);
        JButton cjButton = new JButton(fotocj);
        cjButton.setBounds(250, 370, 100, 50);
        cjButton.setContentAreaFilled(false);
        cjButton.setBorderPainted(false);
        String caminhomuTocando = "imagens/Botaosom.jpeg";
        fotoMusicaTocando = new ImageIcon(caminhomuTocando);
        String caminhomuPausada = "imagens/Botaosem.jpeg";
        fotoMusicaPausada = new ImageIcon(caminhomuPausada);
        musicButton = new JToggleButton();
        musicButton.setBounds(10, 5, 50, 50);
        musicButton.setContentAreaFilled(false);
        musicButton.setBorderPainted(false);
        musicButton.setIcon(fotoMusicaTocando);
        musicButton.setSelected(isMusicPlaying);


        // Criando um novo painel para o título e centralizando:
        String caminhoDaImagem = "imagens/Titulo.png";
        ImageIcon imagemIcone = new ImageIcon(caminhoDaImagem);
        JLabel labelTitulo = new JLabel(imagemIcone);
        labelTitulo.setBounds(40, 90, 500, 99);
        Telamenuinicial.add(labelTitulo);

        // ActionListeners
        novoJogoButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent event) {
                SwingUtilities.invokeLater(() -> {
                    EscolherDificuldade escolherDificuldade = new EscolherDificuldade(TelaInicial.this);
                    setVisible(false); // Oculta a janela atual após a nova janela ser exibida
                    dispose();
                });
            }
        });
        // ActionListener do botão sobre:
        sobreButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent event) {
                ExibirCreditos exibirCreditos = new ExibirCreditos(TelaInicial.this);
                setVisible(false);
            }
        });

        // ActionListener do botão sair:
        sairButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent event) {
                System.exit(0);
            }
        });

        musicButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent event) {
                toggleMusica();
                atualizarBotaoMusica();
                if (isMusicPlaying) {
                    clip.start();
                } else {
                    clip.stop();
                }
            }
        });

        cjButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ComoJogar comoJogar = new ComoJogar(TelaInicial.this);
                setVisible(false);
            }
        });

        // Adicionando os botões ao painel de menu inicial:
        Telamenuinicial.add(novoJogoButton);
        Telamenuinicial.add(sobreButton);
        Telamenuinicial.add(sairButton);
        Telamenuinicial.add(musicButton);
        Telamenuinicial.add(cjButton);


        return Telamenuinicial;
    }


    private Clip clip;
    private long position; // Variável para armazenar a posição da reprodução

    private void toggleMusica() {
        isMusicPlaying = !isMusicPlaying; // Alterna o estado da música

        if (isMusicPlaying) {
            if (clip == null||!clip.isRunning()) {
                reproduzirMusica("imagens/The_Suburbs_-_Arcade_Fire.wav");
            }
        } else {
            paraControleMusica();
        }

        atualizarBotaoMusica(); // Atualiza a imagem do botão após as ações
    }


    private void atualizarBotaoMusica() {
        if (isMusicPlaying) {
            musicButton.setIcon(fotoMusicaTocando);
        } else {
            musicButton.setIcon(fotoMusicaPausada);
        }
    }

    private void reproduzirMusica(String filePath) {
        try {
            if (!isMusicPlaying) {
                AudioInputStream audioStream = AudioSystem.getAudioInputStream(new File(filePath));
                clip = AudioSystem.getClip();
                clip.open(audioStream);

                if (position > 0) {
                    clip.setMicrosecondPosition(position);
                }

                clip.start();
                isMusicPlaying = true;
                atualizarBotaoMusica(); // Atualiza o botão para refletir o estado da música
            }
        } catch (LineUnavailableException | IOException | UnsupportedAudioFileException e) {
            e.printStackTrace();
        }
    }

    private void paraControleMusica() {
        if (clip != null && clip.isRunning()) {
            position = clip.getMicrosecondPosition();
            clip.stop();
            //clip.close();
        }
        isMusicPlaying = false; // Atualiza o estado da música
        atualizarBotaoMusica(); // Atualiza o botão para refletir o estado da música
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