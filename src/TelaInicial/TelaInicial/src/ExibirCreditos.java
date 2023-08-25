import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

public class ExibirCreditos extends JFrame {
    private TelaInicial telaInicial;
    public ExibirCreditos(TelaInicial telaInicial){
        this.telaInicial = telaInicial;
        JFrame Janelacreditos = new JFrame("Créditos");
        Janelacreditos.setSize(600, 600);
        Janelacreditos.setUndecorated(true);
        Janelacreditos.setResizable(false);
        Janelacreditos.setExtendedState(JFrame.NORMAL);
        Janelacreditos.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        JPanel Fotocreditos = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                String caminhoDatc = "imagens/Telacre.png";
                ImageIcon imagemdi = new ImageIcon(caminhoDatc);
                Image img = imagemdi.getImage();
                g.drawImage(img, 0, 0, getWidth(), getHeight(), this);
            }
        };
        Fotocreditos.setLayout(null);
        String texto = "<html><body><div style='text-align: center; font-family: Arial; font-size: 20px; color: white;'>Jogo feito por:<br>Beatriz Guedes da Silva<br>Camila Vasconcelos Moi<br>Danilo Silva<br>Emanuelle Rocha Marreira<br>Gabriel Cortez de São Paulo Rozeno<br>Italo Ferreira Fonseca<br>Italo Guilherme Monte<br>João Vitor Silva de Carvalho<br>Jonatan da Silva Frota<br>Alunos de Engenharia de Computação pela UEA -<br>Universidade Estadual do Amazonas</div></body></html>";
        JLabel label = new JLabel(texto);
        label.setBounds(40, 5, 500, 500);

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
        Fotocreditos.add(voltarButton);
        Fotocreditos.add(label);
        Janelacreditos.setLocationRelativeTo(null);
        Janelacreditos.getContentPane().add(Fotocreditos);
        Janelacreditos.setVisible(true);
    }


    public static void main(String[] args) {
        TelaInicial telaInicial = new TelaInicial();
        SwingUtilities.invokeLater(() -> new ExibirCreditos(telaInicial));
    }
}


