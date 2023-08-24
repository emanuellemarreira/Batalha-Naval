import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

public class ExibirCreditos extends JFrame {

    public ExibirCreditos(){
        JFrame janelacreditos = new JFrame("Créditos");
        janelacreditos.setSize(300, 300);
        janelacreditos.setUndecorated(true);
        janelacreditos.setResizable(false);
        janelacreditos.setExtendedState(JFrame.NORMAL);
        janelacreditos.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        janelacreditos.setLayout(null);

        String texto = "<html><div style='text-align: center; height: 100%; display: flex; flex-direction: column; justify-content: center;'>Jogo feito por:<br>Beatriz Guedes da Silva<br>Camila Vasconcelos Moi<br>Danilo Silva<br>Emanuelle Rocha Marreira<br>Gabriel Cortez de São Paulo Rozeno<br>Italo Ferreira Fonseca<br>Italo Guilherme Monte<br>João Vitor Silva de Carvalho<br>Jonatan da Silva Frota<br>Alunos de Engenharia de Computação pela UEA -<br>Universidade Estadual do Amazonas</div></html>";

        JLabel label = new JLabel(texto);
        label.setBounds(55, 20, 200, 200);

        JButton voltarButton = new JButton("Voltar");
        voltarButton.setBounds(120, 250, 70,20);
        voltarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent event) { janelacreditos.dispose(); }
        });
        janelacreditos.add(voltarButton);
        janelacreditos.add(label);
        janelacreditos.setLocationRelativeTo(null); // Centralizar a janela
        janelacreditos.setVisible(true);
    }


    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new ExibirCreditos());
    }
}


