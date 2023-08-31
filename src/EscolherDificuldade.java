import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class EscolherDificuldade extends JFrame implements ActionListener {
    private TelaInicial telaInicial;
    public EscolherDificuldade(TelaInicial telaInicial) {
        this.telaInicial = telaInicial;
        JFrame frame = new JFrame("DIFICULDADE");
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setSize(600, 600);
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);
        frame.setUndecorated(true);

        JPanel TelaDificuldade = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                String caminhoDatd = "imagens/TelaInicio.jpg";
                ImageIcon imagemdi = new ImageIcon(caminhoDatd);
                Image img = imagemdi.getImage();
                g.drawImage(img, 0, 0, getWidth(), getHeight(), this);
            }
        };
        TelaDificuldade.setLayout(null);

        String caminhofa = "imagens/Botaofa.jpeg";
        ImageIcon fotofa = new ImageIcon(caminhofa);
        JButton facil = new JButton(fotofa);
        facil.setBounds(100, 250, 100, 50);
        facil.setContentAreaFilled(false);
        facil.setBorderPainted(false);

        facil.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent event) {
            	new TelaJogo(1);
                frame.dispose(); //função para tela sumir apos clicar em alguma dificuldade
            }
        });

        String caminhome = "imagens/Botaome.jpeg";
        ImageIcon fotome = new ImageIcon(caminhome);
        JButton medio = new JButton(fotome);
        medio.setBounds(250, 250, 100, 50);
        medio.setContentAreaFilled(false);
        medio.setBorderPainted(false);
        medio.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent event) {
                new TelaJogo(2);
                frame.dispose(); //função para tela sumir apos clicar em alguma dificuldade
            }
        });

        String caminhodi = "imagens/Botaodi.jpeg";
        ImageIcon fotodi = new ImageIcon(caminhodi);
        JButton dificil = new JButton(fotodi);
        dificil.setBounds(400, 250, 100, 50);
        dificil.setContentAreaFilled(false);
        dificil.setBorderPainted(false);

        dificil.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent event) {
                new TelaJogo(3);
                frame.dispose(); //função para tela sumir apos clicar em alguma dificuldade
            }
        });

        String caminhovo = "imagens/Botaovo.jpeg";
        ImageIcon fotovo = new ImageIcon(caminhovo);
        JButton voltar = new JButton(fotovo);
        voltar.setBounds(490, 510, 100, 50);
        voltar.setContentAreaFilled(false);
        voltar.setBorderPainted(false);
        voltar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent event) {
                telaInicial.setVisible(true);
                dispose();
            }
        });

        TelaDificuldade.add(facil);
        TelaDificuldade.add(medio);
        TelaDificuldade.add(dificil);
        TelaDificuldade.add(voltar);

        frame.getContentPane().add(TelaDificuldade);
        frame.setVisible(true);
    }

    public static void main(String[] args) {

        TelaInicial telaInicial = new TelaInicial();
        SwingUtilities.invokeLater(() -> new EscolherDificuldade(telaInicial));
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        // TODO Auto-generated method stub
    }
}
