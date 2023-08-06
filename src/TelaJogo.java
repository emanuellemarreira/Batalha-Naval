import java.awt.Container;
import java.util.Random;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;
// A classe Tela jogo extende a classe JFrame o que significa que vamos criar uma janela
// A classe Tela jogo implementa ActionListener para ser capaz de responder à ações como clicar
public class TelaJogo extends JFrame implements ActionListener {
    JLabel informacoes;

    public TelaJogo() {
    	//Criação da Tela
        JFrame janela = new JFrame("Batalha Naval");
        
        //Criando container (onde vão ficar botões, rotulos, paineis, etc)
        Container caixa = janela.getContentPane();

        //Criando tabuleiro
        JPanel tabuleiro = new JPanel();
        tabuleiro.setLayout(new GridBagLayout());

        //Criando gerador de numero aleatorio
        Random gerador = new Random();
        
        //Criando mapa (0 e 1) onde os navios estarão
        int[][] mapanavios = new int[5][5];
        int contanavios = 0;
        
        //Garantir que nunca vão ser gerados endereços iguais para dois navios
        //Pode ser otimizado futuramente
        int coluna1=-1;
        int coluna2=-1;
        int linha1=-1;
        int linha2=-1;
        for (int l =0; l <3; l++) {
        	int chave1 = gerador.nextInt(4) % 5;
        	int chave2 = gerador.nextInt(4) % 5;
        	
        	 if (l == 0) {
        		coluna1 = chave1;
        		linha1 = chave2;
        	}
        	
        	else if (l == 1) {
        		while (chave1 == coluna1 && chave2 == linha1) {
            		chave1 = gerador.nextInt(4) % 5;
            		chave2 = gerador.nextInt(4) % 5;}
        		coluna2 = chave1;
        		linha2 = chave2;
        	}
        	
        	else if(l == 2) {
        		while ((chave1 == coluna1 && chave2 == linha1) || (chave1 == coluna2 && chave2 == linha2)) {
            		chave1 = gerador.nextInt(4) % 5;
            		chave2 = gerador.nextInt(4) % 5;}
        	}
        	
		    for (int i = 0; i<5; i++) {
		    	for (int j = 0; j<5; j++) {
		    		if(contanavios == 3) {
		    			break;
		    		}
		    		
		    		if (i == chave1 && j == chave2) {
		    			mapanavios[i][j] = 1;
		    			contanavios++;
		    		}
		    	}
		    }
		}
        // GridBagConstraints fornece as coodenadas de cada botão
        GridBagConstraints gbc = new GridBagConstraints();

        //matriz de botoes
        JButton[][] botoes = new JButton[5][5];
        informacoes = new JLabel("teste");
        //configurandos os botoes
        for (int i = 0; i<5; i++) {
        	for (int j = 0; j<5; j++) {
        		JButton b = new JButton("~");
        		botoes[i][j] = b;
        		gbc.gridx = i;
        		gbc.gridy = j;        	
        		botoes[i][j].putClientProperty("temNavio", mapanavios[i][j]);
        		tabuleiro.add(botoes[i][j], gbc);
        		botoes[i][j].addActionListener(new ActionListener() {

                @Override
                public void actionPerformed(ActionEvent e) {
                    JButton botaoClicado = (JButton) e.getSource();
                    int coluna = -1;
                    int linha = -1;
                    for (int i = 0; i < 5; i++) {
                        for (int j = 0; j < 5; j++) {
                            if (botoes[i][j] == botaoClicado) {
                                coluna = i;
                                linha = j;
                                break;
                            }
                        }
                    }
                    int naviosNaLinha = 0;
                    int naviosNaColuna = 0;
                    for (int i = 0; i < 5; i++) {
                        if (mapanavios[coluna][i] == 1) {
                            naviosNaLinha++;
                        }
                        if (mapanavios[i][linha] == 1) {
                            naviosNaColuna++;
                        }
                    }
                    if ((int) botaoClicado.getClientProperty("temNavio") == 1) {
                        botaoClicado.setText("*");
                    } else {
                        botaoClicado.setText("X");
                    }
                    informacoes.setText("Há "+ naviosNaColuna + " navio(s) na coluna e " + naviosNaLinha+ " navio(s) nessa linha");
                }
                    
                });
  	}
        }
        
        JPanel info = new JPanel();
        info.setLayout(new FlowLayout(FlowLayout.CENTER));
        info.add(informacoes);

        caixa.setLayout(new GridLayout(2,1));
        caixa.add(tabuleiro);
        caixa.add(info);
        
        janela.setBounds(300, 300, 300, 300);
        janela.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        janela.setVisible(true);
    }

    public static void main(String args[]) {
        new TelaJogo();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'actionPerformed'");
    }
}
