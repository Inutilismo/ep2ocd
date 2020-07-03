package ep2_ocd;

import java.util.Scanner;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.awt.event.ActionEvent;
import javax.swing.JTextArea;
import javax.swing.JScrollPane;

public class Main {
	
	String fileName = "";

	private JFrame frame;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Main window = new Main();
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
	public Main() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
//		final String fileName = "";
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 361);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JTextArea textAreaPreview = new JTextArea();
		
		JScrollPane scrollPane = new JScrollPane(textAreaPreview);
		scrollPane.setBounds(29, 93, 404, 168);
		frame.getContentPane().add(scrollPane);
				
		
		JButton btnArquivo = new JButton("Arquivo");
		btnArquivo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFileChooser chooser = new JFileChooser();
				chooser.showOpenDialog(null);
				
				File file = chooser.getSelectedFile();
				fileName = file.getAbsolutePath();
				
//				FileNameExtensionFilter filter = new FileNameExtensionFilter("txt files", "txt");
//				chooser.setFileFilter(filter);
				
				try {
					FileReader reader = new FileReader(fileName);
					BufferedReader br = new BufferedReader(reader);
					textAreaPreview.read(br, null);
					br.close();
					textAreaPreview.requestFocus();
				}
				catch(Exception ex){
					JOptionPane.showMessageDialog (null, ex);
				}
				
			}
		});
		btnArquivo.setBounds(167, 49, 117, 29);
		frame.getContentPane().add(btnArquivo);
		
		JLabel lblNewLabel = new JLabel("Escolha um arquivo txt");
		lblNewLabel.setBounds(153, 19, 145, 16);
		frame.getContentPane().add(lblNewLabel);
		
		JButton btnProxPasso = new JButton("Próximo passo");
		btnProxPasso.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {	

				ProcessamentoTxt(fileName);

				JOptionPane.showMessageDialog (null, "Codigo carregado com sucesso!");

				/*
				 * 
				 * criar nova janela e começar o programa
				 * 
				 * 
				 */

				 
			}
		});
			
		
		btnProxPasso.setBounds(129, 280, 192, 40);
		frame.getContentPane().add(btnProxPasso);
	}
	
	public void ProcessamentoTxt(String fileName) {
		try {
			FileReader reader = new FileReader(fileName);
			BufferedReader br = new BufferedReader(reader);
				
			Scanner scan = new Scanner(br);
			
			while(scan.hasNext()) {
				
				String x = scan.nextLine();
				
				if(x.contains(".data")) {
					
					x = scan.nextLine();
					
					while(!x.contains(".text")) {
						
						String[] aux = x.split(": .word ");//na declaracao de vetores, eh necessario que seja utilizado 'nomedovetor: .word'
						// 3,4,5,6
						String[] aux2 = aux[1].split(",");
						
						EP2_OCD.MemoriaPrincipalBinario.add(aux2[0]);
						int posicao = EP2_OCD.MemoriaPrincipalBinario.size()-1;
						for(int i = 0; i < aux2.length; i++){
							EP2_OCD.MemoriaPrincipalBinario.add(aux2[i]);
						}
							
						EP2_OCD.label.put(aux[0], posicao);
						
					}
				}
				else if(x.contains(".text")){
					
					while (scan.hasNext()){
						Instrucao in = new Instrucao();

						String[] divideOpcode = x.split(" ");
						String[] divideInstrucao = divideOpcode[1].split(",");
						
						in.opcode = divideOpcode[0];
						if(divideInstrucao[0] != null) in.parametro1 = divideInstrucao[0];
						if(divideInstrucao[1] != null) in.parametro2 = divideInstrucao[1];
						if(divideInstrucao[2] != null) in.parametro3 = divideInstrucao[2];

						EP2_OCD.MemoriaPrincipalAssembly.add(in);

						x = scan.nextLine();
					}
				}
				
			}
			
			br.close();
			
		}
		catch(Exception ex){
			JOptionPane.showMessageDialog (null, ex);
		}
	}
}
