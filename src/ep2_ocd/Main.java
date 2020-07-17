package ep2_ocd;

import java.util.ArrayList;
import java.util.List;
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

	public static List<Object> backupMemoria = new ArrayList();
	
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
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 361);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JTextArea textAreaPreview = new JTextArea();
		
		JScrollPane scrollPane = new JScrollPane(textAreaPreview);
		scrollPane.setBounds(29, 93, 404, 168);
		frame.getContentPane().add(scrollPane);
		textAreaPreview.setEditable(false);
				
		
		JButton btnArquivo = new JButton("Arquivo");
		
		//carregamento do arquivo contendo o codigo em assembly
		btnArquivo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				JFileChooser chooser = new JFileChooser();
				chooser.showOpenDialog(null);
				File file = chooser.getSelectedFile();
				fileName = file.getAbsolutePath();
				
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
		
		JLabel lblNewLabel = new JLabel("Escolha um arquivo: ");
		lblNewLabel.setBounds(153, 19, 145, 16);
		frame.getContentPane().add(lblNewLabel);
		
		//Inicio do processamento do codigo, quando eh pressionado o botao
		JButton btnProxPasso = new JButton("Próximo passo");
		btnProxPasso.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {	

				//Processa o codigo em assembly
				ProcessamentoTxt(fileName);

				//Traduz o codigo processado para linguagem de maquina em binario
				EP2_OCD.traduzAssembly();

				backupMemoria.addAll(MemoriaPrincipal.MemoriaPrincipalBinario);

				//Inicia a execucao inicial do codigo (compilacao)
				CPU.inicio();

				MemoriaPrincipal.MemoriaPrincipalBinario.clear();
				MemoriaPrincipal.MemoriaPrincipalBinario.addAll(backupMemoria);

				JOptionPane.showMessageDialog (null, "Codigo carregado com sucesso!");
				frame.setVisible(false);
				Janela2 janela = new Janela2();
				janela.initialize();//roda a janela 2

				/*
				 * 
				 * criar nova janela e começar o programa
				 * 
				 * 
				 */
				for(int i : UC.valoresCAR){
					System.out.println("CAR: "+i);
				}
				//zerar os registradores para rodar novamente mostrando na interface grafica
				 CPU.PC = EP2_OCD.PCinicial;
				 CPU.s1 = "";
				 CPU.s2 = "";
				 CPU.s3 = "";
				 CPU.s4 = "";
				 CPU.IR.P1 = "";
				 CPU.IR.P2 = "";
				 CPU.IR.P3 = "";

				 
			}
		});
			
		btnProxPasso.setBounds(129, 280, 192, 40);
		frame.getContentPane().add(btnProxPasso);
	}
	
	/* o metodo a seguir realiza o processamento do arquivo selecionado.
	 * aqui, processar o arquivo significa escrever os vetores - declarados na
	 * parte .data do codigo - na memoria principal e transformar cada linha
	 * do codigo em um objeto do tipo instrucao, facilitando a traducao para
	 * linguagem de maquina mais adiante
	 */
	public void ProcessamentoTxt(String fileName) {
		try {
			FileReader reader = new FileReader(fileName);
			BufferedReader br = new BufferedReader(reader);
			Scanner scan = new Scanner(br);
			
			while(scan.hasNext()) {
				
				String x = scan.nextLine();

				// Processamento dos vetores criados na parte .data
				if(x.contains(".data")) {
					
					x = scan.nextLine();
					
					while(!x.contains(".text")) {
						
						String[] aux = x.split(": .word "); //RELATORIO >>> na declaracao de vetores, eh necessario que seja utilizado 'nomedovetor: .word '
						String[] aux2 = aux[1].split(",");
						
						//inserimos o primeiro item do vetor e armazenamos o index dele para futuras consultas
						MemoriaPrincipal.MemoriaPrincipalBinario.add(Integer.toBinaryString(Integer.parseInt(aux2[0])));
						int posicao = MemoriaPrincipal.MemoriaPrincipalBinario.size()-1;
						
						//adiciona os itens subsequentes
						for(int i = 1; i < aux2.length; i++){
							MemoriaPrincipal.MemoriaPrincipalBinario.add(Integer.toBinaryString(Integer.parseInt(aux2[i])));
						}
							
						//guarda o nome do vetor com a posicao do primeiro item
						EP2_OCD.label.put(aux[0], posicao);
						
						x = scan.nextLine();
					}
				}
				
				/* Processamento do codigo em si, a partir do .text
				 * Aqui organizamos a linha do codigo em assembly em um objeto Instrucao
				 * (onde temos um atributo para o opcode e tambem para cada parametro)
				 * e armazenamos no ArrayList criado na classe EP2_OCD
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
			br.close();
		}
		catch(Exception ex){
			JOptionPane.showMessageDialog (null, ex);
			System.out.println(ex.getMessage());
			ex.printStackTrace();
		}
	}
}