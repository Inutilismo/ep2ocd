package ep2_ocd;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JTextArea;
import javax.swing.JScrollPane;
import javax.swing.JButton;
import javax.swing.JLabel;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Janela2 {

	private JFrame frame;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Janela2 window = new Janela2();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Janela2() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	public void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 806, 625);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JTextArea textAreaRegistradores = new JTextArea();
		textAreaRegistradores.setBounds(545, 41, 244, 493);
		frame.getContentPane().add(textAreaRegistradores);
		textAreaRegistradores.setEditable(false);
		
		JTextArea textAreaCodigo = new JTextArea();
		textAreaCodigo.setEditable(false);
		
		JScrollPane scrollPaneCodigo = new JScrollPane(textAreaCodigo);
		scrollPaneCodigo.setBounds(17, 41, 508, 249);
		frame.getContentPane().add(scrollPaneCodigo);
		
		JTextArea textAreaMemoria = new JTextArea();
		textAreaMemoria.setEditable(false);
		
		JScrollPane scrollPaneMemoria = new JScrollPane(textAreaMemoria);
		scrollPaneMemoria.setBounds(16, 333, 508, 201);
		frame.getContentPane().add(scrollPaneMemoria);
		
		JButton btnProximo = new JButton("Próximo");
		btnProximo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				textAreaCodigo.setText("codigo");
				textAreaMemoria.setText("memoria");
				textAreaRegistradores.setText("registradores");
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
	}
}
