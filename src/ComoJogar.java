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

        String texto = "<html><body><div style='text-align: center; font-family: Arial; font-size: 16px; color: white;'>Tente acertar<br>a localização <br>de todos os barcos!<br>você terá um<br>número determinado de<br>tentativas em cada<br>dificuldade.</div></body></html>";
        JLabel label = new JLabel(texto);
        label.setBounds(40, 5, 500, 500);
        String legenda1 = "<html><body><div style='text-align: center; font-family: Arial; font-size: 16px; color: white;'>Será que o barco<br>está aqui?</div></body></html>";
        JLabel labell1 = new JLabel(legenda1);
        labell1.setBounds(170, 5, 105, 105);
        String legenda2 = "<html><body><div style='text-align: center; font-family: Arial; font-size: 16px; color: white;'>Não estava :(</div></body></html>";
        JLabel labell2 = new JLabel(legenda2);
        labell2.setBounds(300, 25, 105, 105);
        String legenda3 = "<html><body><div style='text-align: center; font-family: Arial; font-size: 16px; color: white;'>Barco abatido!</div></body></html>";
        JLabel labell3 = new JLabel(legenda3);
        labell3.setBounds(300, 80, 105, 105);

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
