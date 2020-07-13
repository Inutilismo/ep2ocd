package ep2_ocd;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JTextArea;
import javax.swing.JScrollPane;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Janela2 {

	static int contador = 0;
	static int contadorAssembly = 0;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	public static void initialize() {
		JFrame frame = new JFrame();
		frame.setBounds(100, 100, 950, 625);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);

		JTextArea textAreaRegistradores = new JTextArea();
		textAreaRegistradores.setBounds(545, 41, 385, 493);
		frame.getContentPane().add(textAreaRegistradores);
		textAreaRegistradores.setEditable(false);

		JTextArea textAreaCodigo = new JTextArea();
		textAreaCodigo.setEditable(false);
		// textAreaCodigo.setBorder(BorderFactory.createCompoundBorder(textAreaCodigo.getBorder(),
		// BorderFactory.createEmptyBorder(5, 10, 5, 5)));

		JScrollPane scrollPaneCodigo = new JScrollPane(textAreaCodigo);
		scrollPaneCodigo.setBounds(17, 41, 508, 249);
		frame.getContentPane().add(scrollPaneCodigo);

		JTextArea textAreaMemoria = new JTextArea();
		textAreaMemoria.setEditable(false);
		// textAreaMemoria.setBorder(BorderFactory.createCompoundBorder(textAreaMemoria.getBorder(),
		// BorderFactory.createEmptyBorder(5, 10, 5, 5)));

		JScrollPane scrollPaneMemoria = new JScrollPane(textAreaMemoria);
		scrollPaneMemoria.setBounds(16, 333, 508, 201);
		frame.getContentPane().add(scrollPaneMemoria);

		JButton btnProximo = new JButton("Próximo");
		btnProximo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				String instrucaoAtual;
				
				if(contador-1 < CPU.UC.codigoCompilado.size()-1){
					contador++;

					CPU.UC.CAR = contador-1;
					CPU.UC.CBR = CPU.UC.codigoCompilado.get(CPU.UC.CAR);
					CPU.UC.executaLinhaCBR();

					textAreaMemoria.setText(MemoriaPrincipal.toStr());
					
					textAreaRegistradores.setText(CPU.toSTR());

					if(contador == 1){
						textAreaCodigo.append("\n------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------\n");
						textAreaCodigo.append(EP2_OCD.MemoriaAuxiliar.get(0).toString() + "\n");
						textAreaCodigo.append("Ciclo de Busca\n");
					}
					if ((contador < CPU.UC.codigoCompilado.size()-1 && CPU.UC.codigoCompilado.get(contador).equals("0110000000000000000001000000 0000 000 0 000000000"))){
						contadorAssembly ++;
						textAreaCodigo.append("\n------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------\n");
						textAreaCodigo.append(EP2_OCD.MemoriaAuxiliar.get(contadorAssembly).toString() + "\n");
						textAreaCodigo.append("Ciclo de Busca\n");
					} 
					if (CPU.UC.codigoCompilado.get(contador-1).equals("0000100000000100000000000000 0000 000 0 000000000")){
						textAreaCodigo.append("Ciclo de Execução\n");
					} 
					textAreaCodigo.append(CPU.UC.CBR + "	");
					textAreaCodigo.append(CPU.UC.microCompilado.get(CPU.UC.CAR) + "\n");
				}else{
					JOptionPane.showMessageDialog (null, "Fim do código");
					btnProximo.setEnabled(false);
				}
			}
		});

		btnProximo.setBounds(386, 546, 138, 39);
		frame.getContentPane().add(btnProximo);
		
		JLabel lblNewLabel = new JLabel("Memória");
		lblNewLabel.setBounds(17, 305, 61, 16);
		frame.getContentPane().add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Código");
		lblNewLabel_1.setBounds(20, 16, 61, 16);
		frame.getContentPane().add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("Registradores");
		lblNewLabel_2.setBounds(546, 16, 100, 16);
		frame.getContentPane().add(lblNewLabel_2);

		frame.setVisible(true);
	}
}
