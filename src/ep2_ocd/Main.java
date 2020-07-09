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
	
	static String fileName = "";

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
//		frame.setVisible(true);
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 361);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JTextArea textAreaPreview = new JTextArea();
		
		JScrollPane scrollPane = new JScrollPane(textAreaPreview);
		scrollPane.setBounds(29, 93, 404, 168);
		frame.getContentPane().add(scrollPane);
		textAreaPreview.setEditable(false);//------------------------------------
				
		
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
				EP2_OCD.traduzAssembly();

				CPU.inicio();

				System.out.println("-------------------------------------");
				for(Object o : MemoriaPrincipal.MemoriaPrincipalBinario) {
					System.out.println(o);
				}

				JOptionPane.showMessageDialog (null, "Codigo carregado com sucesso!");//popup com mensagem de sucesso
				
				frame.setVisible(false);//nao mostra mais a tela anterior
				
				Janela2 janela = new Janela2();
				janela.initialize();//roda a janela 2

				
				

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
						
						
						//split utilizado para separar o nome do vetor dos valores do mesmo
						String[] aux = x.split(": .word ");//na declaracao de vetores, eh necessario que seja utilizado 'nomedovetor: .word '
										
						//split utilizado para separar cada valor que compoe o vetor
						String[] aux2 = aux[1].split(",");
						
						//adiciona o primeiro item separado para conseguir a posicao dele
						MemoriaPrincipal.MemoriaPrincipalBinario.add(Integer.toBinaryString(Integer.parseInt(aux2[0])));
						int posicao = MemoriaPrincipal.MemoriaPrincipalBinario.size()-1;
						
						//adiciona os itens subsequentes
						for(int i = 1; i < aux2.length; i++){
							MemoriaPrincipal.MemoriaPrincipalBinario.add(Integer.toBinaryString(Integer.parseInt(aux2[i])));
						}
							
						//guarda o nome do vetor com a posicao do primeiro item
						EP2_OCD.label.put(aux[0], posicao);
						
						//passa para a proxima linha
						x = scan.nextLine();
						
					}
					
					
				}
				
				for(Object in : MemoriaPrincipal.MemoriaPrincipalBinario) {
					System.out.println(in);
				}
/*				
				System.out.println("-------------");
				System.out.println(x);
				System.out.println("-------------");
*/				
				
				
				if(x.contains(".text")){
					
					while (scan.hasNext()){
						x = scan.nextLine();
						Instrucao in = new Instrucao();
						

						String[] divideOpcode = x.split(" ");
						
						in.opcode = divideOpcode[0];		
						
						String[] divideInstrucao = divideOpcode[1].split(",");
						
						if(in.opcode.equals("lw") || in.opcode.equals("sw") ) {
							
							in.parametro1 = divideInstrucao[0];
							in.parametro2 = divideInstrucao[1]+" "+divideOpcode[2];
							
						}
						else {
							
							if(divideInstrucao[0] != null) in.parametro1 = divideInstrucao[0];
							if(divideInstrucao.length >= 2 && divideInstrucao[1] != null) in.parametro2 = divideInstrucao[1];
							if(divideInstrucao.length == 3 && divideInstrucao[2] != null) in.parametro3 = divideInstrucao[2];
						}

						EP2_OCD.MemoriaAuxiliar.add(in);
						
					}
				}
				
			}
			
/*			System.out.println("------------------------------------------------------------------");
			
			for(Instrucao in : EP2_OCD.MemoriaPrincipalAssembly) {
				System.out.println(in);
			}
			
			System.out.println("------------------------------------------------------------------");
*/			
			
			
			br.close();
			
		}
		catch(Exception ex){
			JOptionPane.showMessageDialog (null, ex);
			System.out.println(ex.getMessage());
			ex.printStackTrace();
		}
	}
}