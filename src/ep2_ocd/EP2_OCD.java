package ep2_ocd;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Map;

public class EP2_OCD {

    public static List<Instrucao> MemoriaPrincipalAssembly = new ArrayList<>();
    public static List<Object> MemoriaPrincipalBinario = new ArrayList<>();
    public static Map<String,Integer> label = new HashMap<>();	//chave � o nome do label e o valor eh a posicao da memoria para a qual ele aponta
    
    // public static List<String> codigo = new ArrayList<>();
    
    
    public static void main(String[] args) {
    	
    }
    
    public static void traduzAssembly(){
    	
    	for(Instrucao in : MemoriaPrincipalAssembly) {
    		Instrucao newIn = new Instrucao();
    		switch(in.opcode) {
    			case "add": {
    				newIn.opcode = "00001";
    				newIn.parametro1 = PreencheP1(in);
    				
    				newIn.parametro2 = PreencheP2(in);
    				
    				newIn.parametro3 = PreencheP3(in);
    			}
    			break;
    			
    			case "addi": {
    				newIn.opcode = "00010";
    				newIn.parametro1 = PreencheP1(in);
    				
    				newIn.parametro2 = PreencheP2(in);
    				
    				newIn.parametro3 = Integer.toBinaryString(Integer.parseInt(in.parametro3));
    				if(newIn.parametro3.length() > 9) throw new InputMismatchException("Numero de bits excede o limite");
    			}break;
    			
    			case "sub":{
    				newIn.opcode = "00011";
    				newIn.parametro1 = PreencheP1(in);
    				
    				newIn.parametro2 = PreencheP2(in);
    				
    				newIn.parametro3 = PreencheP3(in);   				
    			}break;
    			
    			case "subi": {
    				newIn.opcode = "00100";
    				newIn.parametro1 = PreencheP1(in);
    				
    				newIn.parametro2 = PreencheP2(in);
    				
    				newIn.parametro3 = Integer.toBinaryString(Integer.parseInt(in.parametro3));
    				if(newIn.parametro3.length() > 9) throw new InputMismatchException("Numero de bits excede o limite");    				
    			}break;
    			
    			case "li": {
    				newIn.opcode = "00101";
    				System.out.println(in);
    				newIn.parametro1 = PreencheP1(in);
    				
    				newIn.parametro2 = Integer.toBinaryString(Integer.parseInt(in.parametro2));
    				if(newIn.parametro2.length() > 9) throw new InputMismatchException("Numero de bits excede o limite");  
    			
    				newIn.parametro3 = "000000000";
    			}break;
    			
    			case "lw": {		//quando criarmos os vetores na mem�ria, temos que salvar o indexOf da 1� posi��o deles no ArrayList
    				newIn.opcode = "00110";
    				newIn.parametro1 = PreencheP1(in);
    				
    				System.out.println(in.parametro2);
    				
    				String[] x = in.parametro2.split(" ");	//[16,(endere�o de mem�ria que indica o label)]
    				System.out.println(x.length);
    				x[1] = x[1].substring(1, x[1].length()-1);
    				
    				int aux = Integer.parseInt(x[0])/4;
    				newIn.parametro2 = Integer.toBinaryString(aux + Integer.parseInt(x[1]));
    				
    				newIn.parametro3 = "000000000";
    			}break;
    			
    			case "sw": {
    				newIn.opcode = "00111";
    				newIn.parametro1 = PreencheP1(in);
    				
    				String[] x = in.parametro2.split(" ");	//[16,(endere�o de mem�ria que indica o label)]
    				x[1] = x[1].substring(1, x[1].length()-1);
    				
    				int aux = Integer.parseInt(x[0])/4;
    				newIn.parametro2 = Integer.toBinaryString(aux + Integer.parseInt(x[1]));    	
    				
    				newIn.parametro3 = "000000000";
    			}break;
    			
    			case "move": {
    				newIn.opcode = "01000";

    				newIn.parametro1 = PreencheP1(in);
    				
    				newIn.parametro2 = PreencheP2(in); 
    				
    				newIn.parametro3 = "000000000";
    			}break;
    			
    			case "beq": {
    				
    				newIn.parametro1 = PreencheP1(in);
    				
    				if(in.parametro2.contains("$s")) {	//se eh um registrador
	    				if(in.parametro2 == "$s1") {
	    					newIn.parametro2 = "000000001";
	    					newIn.opcode = "01010";
	    				}
	    				else if(in.parametro2 == "$s2") {
	    					newIn.parametro2 = "000000010";
	    					newIn.opcode = "01010";
	    				}
	    				else if(in.parametro2 == "$s3") {
	    					newIn.parametro2 = "000000011";
	    					newIn.opcode = "01010";
	    				}
	    				else if(in.parametro2 == "$s4") {
	    					newIn.parametro2 = "000000100";
	    					newIn.opcode = "01010";
	    				}
	    				else throw new InputMismatchException("Parametro invalido"); 
    				}
    				else {
    					newIn.opcode = "01001";
	    				newIn.parametro2 = Integer.toBinaryString(Integer.parseInt(in.parametro2));
	    				if(newIn.parametro2.length() > 9) throw new InputMismatchException("Numero de bits excede o limite");  
    				}
    			
    				newIn.parametro3 = Integer.toBinaryString(Integer.parseInt(in.parametro3));
    				if(newIn.parametro3.length() > 9) throw new InputMismatchException("Numero de bits excede o limite");
    			}break;
    			
    			case "bne": {
    				newIn.parametro1 = PreencheP1(in);
    				
    				if(in.parametro2.contains("$s")) {	//se eh um registrador
	    				if(in.parametro2 == "$s1") {
	    					newIn.parametro2 = "000000001";
	    					newIn.opcode = "01100";
	    				}
	    				else if(in.parametro2 == "$s2") {
	    					newIn.parametro2 = "000000010";
	    					newIn.opcode = "01100";
	    				}
	    				else if(in.parametro2 == "$s3") {
	    					newIn.parametro2 = "000000011";
	    					newIn.opcode = "01100";
	    				}
	    				else if(in.parametro2 == "$s4") {
	    					newIn.parametro2 = "000000100";
	    					newIn.opcode = "01100";
	    				}
	    				else throw new InputMismatchException("Parametro invalido"); 
    				}
    				else {
    					newIn.opcode = "01011";
	    				newIn.parametro2 = Integer.toBinaryString(Integer.parseInt(in.parametro2));
	    				if(newIn.parametro2.length() > 9) throw new InputMismatchException("Numero de bits excede o limite");  
    				}
    			
    				newIn.parametro3 = Integer.toBinaryString(Integer.parseInt(in.parametro3));
    				if(newIn.parametro3.length() > 9) throw new InputMismatchException("Numero de bits excede o limite");    				
    			}break;
    			
    			case "j":{
    				newIn.opcode = "01101";
    				
    				newIn.parametro1 = Integer.toBinaryString(Integer.parseInt(in.parametro1));
    				if(newIn.parametro1.length() > 9) throw new InputMismatchException("Numero de bits excede o limite");      		
    				
    				newIn.parametro2 = "000000000";
    				newIn.parametro3 = "000000000";
    			}break;
    			
    			case "slt": {
    				newIn.opcode = "01110";
    				newIn.parametro1 = PreencheP1(in);
    				
    				newIn.parametro2 = PreencheP2(in);
    				
    				newIn.parametro3 = PreencheP3(in);    				
    			}break;
    			
    			case "la": {
    				newIn.opcode = "01111";
    				
    				newIn.parametro1 = PreencheP1(in);
    				
    				newIn.parametro2 = Integer.toBinaryString(label.get(in.parametro2));
				}break;
				
				
    		}
    		
    		MemoriaPrincipalBinario.add(newIn);
    	}
    }
    
    public static String PreencheP1(Instrucao in) {
    	if(in.parametro1.equals("$s1")) return "000000001";
		else if(in.parametro1.equals("$s2")) return "000000010";
		else if(in.parametro1.equals("$s3")) return "000000011";
		else if(in.parametro1.equals("$s4")) return "000000100";
		else throw new InputMismatchException("Parametro invalido");
    }
    
    public static String PreencheP2(Instrucao in) {
    	if(in.parametro2.equals("$s1")) return "000000001";
		else if(in.parametro2.equals("$s2")) return "000000010";
		else if(in.parametro2.equals("$s3")) return "000000011";
		else if(in.parametro2.equals("$s4")) return "000000100";
		else throw new InputMismatchException("Parametro invalido");
    }
    
    public static String PreencheP3(Instrucao in) {
    	if(in.parametro3.equals("$s1")) return "000000001";
		else if(in.parametro3.equals("$s2")) return "000000010";
		else if(in.parametro3.equals("$s3")) return "000000011";
		else if(in.parametro3.equals("$s4")) return "000000100";
		else throw new InputMismatchException("Parametro invalido");
    }
    
}
