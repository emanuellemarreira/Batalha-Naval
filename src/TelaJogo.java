import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

public class TelaJogo extends JFrame implements ActionListener {
    JFrame janela;
    Container caixa;
    JPanel tabuleiro, info;
    JButton um, dois, tres, quatro;
    JLabel teste;

    TelaJogo() {
        JFrame janela = new JFrame("Batalha Naval");

        Container caixa = janela.getContentPane();


        JPanel tabuleiro = new JPanel();
        tabuleiro.setLayout(new GridBagLayout());

        // Create GridBagConstraints to control the positioning of components in the grid
        GridBagConstraints gbc = new GridBagConstraints();
        // Adding the buttons to the grid with appropriate constraints
        JButton um = new JButton("~");
        um.putClientProperty("temNavio", 0);
        gbc.gridx = 0;
        gbc.gridy = 0;
        tabuleiro.add(um, gbc);

        JButton dois = new JButton("~");
        dois.putClientProperty("temNavio", 0);
        gbc.gridx = 1;
        gbc.gridy = 0;
        tabuleiro.add(dois, gbc);

        JButton tres = new JButton("~");
        tres.putClientProperty("temNavio", 1);
        gbc.gridx = 0;
        gbc.gridy = 1;
        tabuleiro.add(tres, gbc);

        JButton quatro = new JButton("~");
        quatro.putClientProperty("temNavio", 0);
        gbc.gridx = 1;
        gbc.gridy = 1;
        tabuleiro.add(quatro, gbc);

        JPanel info = new JPanel();
        info.setLayout(new FlowLayout(FlowLayout.CENTER));
        JLabel teste = new JLabel("teste");
        info.add(teste);

        caixa.setLayout(new GridLayout(2,1));
        caixa.add(tabuleiro);
        caixa.add(info);
        
        // Using layout compostos
        um.addActionListener(this);
        dois.addActionListener(this);
        tres.addActionListener(this);
        quatro.addActionListener(this);

        janela.setBounds(300, 300, 300, 300);
        janela.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        janela.setVisible(true);
    }

    public static void main(String args[]) {
        new TelaJogo();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        JButton botaoClicado = (JButton) e.getSource();
        for (int i = 0 ; i <= 1; i++) {
        	for(int j = 0; j<=1; j++) {
        		if((int)botaoClicado.getClientProperty("temNavio") == 1) {
        			botaoClicado.setText("*");
        		}else {
        			botaoClicado.setText("X");
        		}
        	}
        }
    }
}
