import java.awt.Container;
import java.util.Random;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;
// A classe Tela jogo extende a classe JFrame o que significa que vamos criar uma janela
// A classe Tela jogo implementa ActionListener para ser capaz de responder à ações como clicar
public class TelaJogo extends JFrame implements ActionListener {
    JFrame janela;
    Container caixa;
    JPanel tabuleiro, info;
    JButton um, dois, tres, quatro;
    JLabel teste;
    Random gerador;

    TelaJogo() {
    	//Criação da Tela
        JFrame janela = new JFrame("Batalha Naval");
        
        //Criando container (onde vão ficar butões, rotulos, paineis, etc)
        Container caixa = janela.getContentPane();

        //Criando tabuleiro
        JPanel tabuleiro = new JPanel();
        tabuleiro.setLayout(new GridBagLayout());

        //Criando gerador de numero aleatorio
        Random gerador = new Random();
        
        // GridBagConstraints fornece as coodenadas de cada botão
        GridBagConstraints gbc = new GridBagConstraints();
        // Adding the buttons to the grid with appropriate constraints
        JButton um = new JButton("~");
        um.putClientProperty("temNavio", gerador.nextInt(2));
        gbc.gridx = 0;
        gbc.gridy = 0;
        tabuleiro.add(um, gbc);

        JButton dois = new JButton("~");
        dois.putClientProperty("temNavio", gerador.nextInt(2));
        gbc.gridx = 1;
        gbc.gridy = 0;
        tabuleiro.add(dois, gbc);

        JButton tres = new JButton("~");
        tres.putClientProperty("temNavio", gerador.nextInt(2));
        gbc.gridx = 0;
        gbc.gridy = 1;
        tabuleiro.add(tres, gbc);

        JButton quatro = new JButton("~");
        quatro.putClientProperty("temNavio", gerador.nextInt(2));
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
