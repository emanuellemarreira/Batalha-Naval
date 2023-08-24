import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class EscolherDificuldade extends JFrame implements ActionListener{
    
	public EscolherDificuldade() {
        JFrame frame = new JFrame("Escolha a dificuldade");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(300, 200);

        JPanel panel = new JPanel();
        panel.setLayout(new FlowLayout());

        JButton facil = new JButton("fácil");
        facil.addActionListener(new ActionListener() {
        	@Override
            public void actionPerformed(ActionEvent event) {
                new TelaJogo(1);
            }
        });
        JButton medio = new JButton("médio");
        medio.addActionListener(new ActionListener() {
        	@Override
            public void actionPerformed(ActionEvent event) {
                new TelaJogo(2);
            }
        });
        JButton dificil = new JButton("difícil");
        dificil.addActionListener(new ActionListener() {
        	@Override
            public void actionPerformed(ActionEvent event) {
                new TelaJogo(3);
            }
        });

        panel.add(facil);
        panel.add(medio);
        panel.add(dificil);

        frame.getContentPane().add(panel);
        frame.setVisible(true);
	}

	public static void main(String[] args) {
		SwingUtilities.invokeLater(() -> new EscolherDificuldade());
    }
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}
}
