import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

public class TelaJogo extends JFrame implements ActionListener{
	JFrame janela;
	Container caixa;
	JPanel tabuleiro, info;
	JButton um, dois, tres, quatro;
	JLabel teste;
	TelaJogo(){
	    JFrame janela = new JFrame("Batalha Naval");
		
		Container caixa = janela.getContentPane();

		JPanel tabuleiro = new JPanel();
		JButton um = new JButton();
		um.setText("~");
		JButton dois = new JButton();
		dois.setText("~");
		JButton tres = new JButton();
		tres.setText("~");
		JButton quatro = new JButton();
		quatro.setText("~");
		tabuleiro.setLayout(new GridLayout(2,2));
		tabuleiro.add(um);
		tabuleiro.add(dois);
		tabuleiro.add(tres);
		tabuleiro.add(quatro);
		
		JPanel info = new JPanel();
		info.setLayout(new FlowLayout(FlowLayout.CENTER));
		JLabel teste = new JLabel("teste"); 
		info.add(teste);
		
		caixa.setLayout(new GridLayout(2,1));//usando layout compostos
		
		um.addActionListener(this);
		dois.addActionListener(this);
		tres.addActionListener(this);
		quatro.addActionListener(this);
		
		janela.add(tabuleiro);
		janela.add(info);
		janela.setBounds(500,700,700,500);
		janela.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		janela.setVisible(true);

	}
	
	public static void main(String args[]) {
		new TelaJogo();
	}
	

	 @Override
	    public void actionPerformed(ActionEvent e) {
	        JButton botaoClicado = (JButton) e.getSource();
	        botaoClicado.setText("X");
	    }
}
