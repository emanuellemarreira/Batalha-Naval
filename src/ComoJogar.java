import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ComoJogar extends JFrame {
    private TelaInicial telaInicial;

    public ComoJogar(TelaInicial telaInicial) {
        this.telaInicial = telaInicial;
        JFrame Janelacj = new JFrame("Créditos");
        Janelacj.setSize(600, 600);
        Janelacj.setUndecorated(true);
        Janelacj.setResizable(false);
        Janelacj.setExtendedState(JFrame.NORMAL);
        Janelacj.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        JPanel Fotocj = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                String caminhoDatc = "imagens/Telacre.png";
                ImageIcon imagemdi = new ImageIcon(caminhoDatc);
                Image img = imagemdi.getImage();
                g.drawImage(img, 0, 0, getWidth(), getHeight(), this);
            }
        };
        Fotocj.setLayout(null);

        String texto = "<html><body><div style='text-align: center; font-family: Arial; font-size: 16px; color: white;'>Tente acertar<br>a localização <br>de todos os barcos!<br>você terá um<br>número determinado de<br>tentativas em cada<br>dificuldade para<br>descobrir onde<br>estão os navios<br>em um mapa 5x5.<br>Será dado uma<br>dica de onde<br>os barcos estão<br>a cada tentativa<br>feita.<br>NIVEL FÁCIL: 15 tentativas<br>NÍVEL MÉDIO: 10 tentativas<br>NÍVEL DIFÍCIL: 7 tentativas</div></body></html>";
        JLabel label = new JLabel(texto);
        label.setBounds(40, 30, 500, 500);
        String legenda1 = "<html><body><div style='text-align: justify; font-family: Arial; font-size: 16px; color: white;'>Será que o barco<br>está aqui?</div></body></html>";
        JLabel labell1 = new JLabel(legenda1);
        labell1.setBounds(350, 50, 100, 100);
        String legenda2 = "<html><body><div style='text-align: justify; font-family: Arial; font-size: 16px; color: white;'>Não estava :(</div></body></html>";
        JLabel labell2 = new JLabel(legenda2);
        labell2.setBounds(350, 225, 100, 100);
        String legenda3 = "<html><body><div style='text-align: justify; font-family: Arial; font-size: 16px; color: white;'>Barco abatido!</div></body></html>";
        JLabel labell3 = new JLabel(legenda3);
        labell3.setBounds(350, 400, 100, 100);
        String caminhoa = "imagens/Agua.jpeg";
        ImageIcon fotoa = new ImageIcon(caminhoa);
        JLabel inf1 = new JLabel(fotoa);
        inf1.setBounds(450, 80, 50, 50);
        String caminhonx = "imagens/Semnavio.jpeg";
        ImageIcon fotonx = new ImageIcon(caminhonx);
        JLabel inf2 = new JLabel(fotonx);
        inf2.setBounds(450, 255, 50, 50);
        String caminhons = "imagens/Temnavio.jpeg";
        ImageIcon fotons = new ImageIcon(caminhons);
        JLabel inf3 = new JLabel(fotons);
        inf3.setBounds(450, 430, 50, 50);

        String caminhovo = "imagens/Botaovo.jpeg";
        ImageIcon fotovo = new ImageIcon(caminhovo);
        JButton voltarButton = new JButton(fotovo);
        voltarButton.setBounds(490, 510, 100, 50);
        voltarButton.setContentAreaFilled(false);
        voltarButton.setBorderPainted(false);
        voltarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent event) {
                telaInicial.setVisible(true);
                dispose();
            }
        });

        Fotocj.add(voltarButton);
        Fotocj.add(label);
        Fotocj.add(labell2);
        Fotocj.add(labell1);
        Fotocj.add(labell3);
        Fotocj.add(inf1);
        Fotocj.add(inf2);
        Fotocj.add(inf3);
        Janelacj.setLocationRelativeTo(null);
        Janelacj.getContentPane().add(Fotocj);
        Janelacj.setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            TelaInicial telaInicial = new TelaInicial();
            new ComoJogar(telaInicial).setVisible(true);
        });
    }
}
