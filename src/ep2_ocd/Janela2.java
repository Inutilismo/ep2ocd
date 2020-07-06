package ep2_ocd;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.BorderLayout;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTabbedPane;

public class Janela2 {
	private static JTextField txtFAssembly;

	public static void main(String[] args) {
		initialize();
	}

	public static void initialize() {
		JFrame frame = new JFrame("Janela2");
		frame.setBounds(100, 100, 759, 525);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		//campo de texto da instrucao de assembly atual
		txtFAssembly = new JTextField();
		txtFAssembly.setEditable(false);
		txtFAssembly.setBounds(37, 27, 686, 35);
		frame.getContentPane().add(txtFAssembly);
		txtFAssembly.setColumns(10);
		
		//botao para a micro operacao anterior
		JButton btnAnterior = new JButton("ANTERIOR");
		btnAnterior.setBounds(37, 422, 334, 44);
		frame.getContentPane().add(btnAnterior);
		
		//botao para a proxima micro operacao
		JButton btnProximo = new JButton("PRÓXIMO");
		btnProximo.setBounds(389, 422, 334, 44);
		frame.getContentPane().add(btnProximo);
		
		//painel de rolagem para display
		JTextArea textAreaPreview = new JTextArea();
		textAreaPreview.setEditable(false);
		JScrollPane scrollPane = new JScrollPane(textAreaPreview);
		scrollPane.setBounds(41, 74, 324, 336);
		frame.getContentPane().add(scrollPane);
		
		//tabela
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBounds(389, 74, 324, 336);
		frame.getContentPane().add(tabbedPane);
		
		frame.setVisible(true);
	}
}
