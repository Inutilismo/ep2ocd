package ep2_ocd;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Map;

public class EP2_OCD {

    static List<Instrucao> MemoriaPrincipalAssembly = new ArrayList<>();
    static List<Object> MemoriaPrincipalBinario = new ArrayList<>();
    static Map<String,String> label = new HashMap<>();	//chave é o nome do label e o valor eh a posicao da memoria para a qual ele aponta
    
    static List<String> codigo = new ArrayList<>();
    
    public static void main(String[] args) {
    	
    }
    
    public static void traduzAssembly(){
    	
    	for(Instrucao in : MemoriaPrincipalAssembly) {
    		Instrucao newIn = new Instrucao();
    		switch(in.opcode) {
    			case "add": {
    				newIn.opcode = "00001";
    				if(in.parametro1 == "$s1") newIn.parametro1 = "000000001";
    				if(in.parametro1 == "$s2") newIn.parametro1 = "000000010";
    				if(in.parametro1 == "$s3") newIn.parametro1 = "000000011";
    				if(in.parametro1 == "$s4") newIn.parametro1 = "000000100";
    				else throw new InputMismatchException("Parametro invalido");
    				
    				if(in.parametro2 == "$s1") newIn.parametro2 = "000000001";
    				if(in.parametro2 == "$s2") newIn.parametro2 = "000000010";
    				if(in.parametro2 == "$s3") newIn.parametro2 = "000000011";
    				if(in.parametro2 == "$s4") newIn.parametro2 = "000000100";
    				else throw new InputMismatchException("Parametro invalido");
    				
    				if(in.parametro3 == "$s1") newIn.parametro3 = "000000001";
    				if(in.parametro3 == "$s2") newIn.parametro3 = "000000010";
    				if(in.parametro3 == "$s3") newIn.parametro3 = "000000011";
    				if(in.parametro3 == "$s4") newIn.parametro3 = "000000100";  
    				else throw new InputMismatchException("Parametro invalido");
    			}
    			
    			case "addi": {
    				newIn.opcode = "00010";
    				if(in.parametro1 == "$s1") newIn.parametro1 = "000000001";
    				if(in.parametro1 == "$s2") newIn.parametro1 = "000000010";
    				if(in.parametro1 == "$s3") newIn.parametro1 = "000000011";
    				if(in.parametro1 == "$s4") newIn.parametro1 = "000000100";
    				else throw new InputMismatchException("Parametro invalido");
    				
    				if(in.parametro2 == "$s1") newIn.parametro2 = "000000001";
    				if(in.parametro2 == "$s2") newIn.parametro2 = "000000010";
    				if(in.parametro2 == "$s3") newIn.parametro2 = "000000011";
    				if(in.parametro2 == "$s4") newIn.parametro2 = "000000100";
    				else throw new InputMismatchException("Parametro invalido"); 
    				
    				newIn.parametro3 = Integer.toBinaryString(Integer.parseInt(in.parametro3));
    				if(newIn.parametro3.length() > 9) throw new InputMismatchException("Numero de bits excede o limite");
    			}
    			
    			case "sub":{
    				newIn.opcode = "00011";
    				if(in.parametro1 == "$s1") newIn.parametro1 = "000000001";
    				if(in.parametro1 == "$s2") newIn.parametro1 = "000000010";
    				if(in.parametro1 == "$s3") newIn.parametro1 = "000000011";
    				if(in.parametro1 == "$s4") newIn.parametro1 = "000000100";
    				else throw new InputMismatchException("Parametro invalido");
    				
    				if(in.parametro2 == "$s1") newIn.parametro2 = "000000001";
    				if(in.parametro2 == "$s2") newIn.parametro2 = "000000010";
    				if(in.parametro2 == "$s3") newIn.parametro2 = "000000011";
    				if(in.parametro2 == "$s4") newIn.parametro2 = "000000100";
    				else throw new InputMismatchException("Parametro invalido");
    				
    				if(in.parametro3 == "$s1") newIn.parametro3 = "000000001";
    				if(in.parametro3 == "$s2") newIn.parametro3 = "000000010";
    				if(in.parametro3 == "$s3") newIn.parametro3 = "000000011";
    				if(in.parametro3 == "$s4") newIn.parametro3 = "000000100";  
    				else throw new InputMismatchException("Parametro invalido");    				
    			}
    			
    			case "subi": {
    				newIn.opcode = "00100";
    				if(in.parametro1 == "$s1") newIn.parametro1 = "000000001";
    				if(in.parametro1 == "$s2") newIn.parametro1 = "000000010";
    				if(in.parametro1 == "$s3") newIn.parametro1 = "000000011";
    				if(in.parametro1 == "$s4") newIn.parametro1 = "000000100";
    				else throw new InputMismatchException("Parametro invalido");
    				
    				if(in.parametro2 == "$s1") newIn.parametro2 = "000000001";
    				if(in.parametro2 == "$s2") newIn.parametro2 = "000000010";
    				if(in.parametro2 == "$s3") newIn.parametro2 = "000000011";
    				if(in.parametro2 == "$s4") newIn.parametro2 = "000000100";
    				else throw new InputMismatchException("Parametro invalido"); 
    				
    				newIn.parametro3 = Integer.toBinaryString(Integer.parseInt(in.parametro3));
    				if(newIn.parametro3.length() > 9) throw new InputMismatchException("Numero de bits excede o limite");    				
    			}
    			
    			case "li": {
    				newIn.opcode = "00101";
    				if(in.parametro1 == "$s1") newIn.parametro1 = "000000001";
    				if(in.parametro1 == "$s2") newIn.parametro1 = "000000010";
    				if(in.parametro1 == "$s3") newIn.parametro1 = "000000011";
    				if(in.parametro1 == "$s4") newIn.parametro1 = "000000100";
    				else throw new InputMismatchException("Parametro invalido");
    				
    				newIn.parametro2 = Integer.toBinaryString(Integer.parseInt(in.parametro2));
    				if(newIn.parametro2.length() > 9) throw new InputMismatchException("Numero de bits excede o limite");  
    			
    				newIn.parametro3 = "000000000";
    			}
    			
    			case "lw": {		//quando criarmos os vetores na memória, temos que salvar o indexOf da 1° posição deles no ArrayList
    				newIn.opcode = "00110";
    				if(in.parametro1 == "$s1") newIn.parametro1 = "000000001";
    				if(in.parametro1 == "$s2") newIn.parametro1 = "000000010";
    				if(in.parametro1 == "$s3") newIn.parametro1 = "000000011";
    				if(in.parametro1 == "$s4") newIn.parametro1 = "000000100";
    				else throw new InputMismatchException("Parametro invalido");
    				
    				String[] x = in.parametro2.split(" ");	//[16,(endereço de memória que indica o label)]
    				x[1] = x[1].substring(1, x[1].length()-1);
    				
    				int aux = Integer.parseInt(x[0])/4;
    				newIn.parametro2 = Integer.toBinaryString(aux + Integer.parseInt(x[1]));
    				
    				newIn.parametro3 = "000000000";
    			}
    			
    			case "sw": {
    				newIn.opcode = "00111";
    				if(in.parametro1 == "$s1") newIn.parametro1 = "000000001";
    				if(in.parametro1 == "$s2") newIn.parametro1 = "000000010";
    				if(in.parametro1 == "$s3") newIn.parametro1 = "000000011";
    				if(in.parametro1 == "$s4") newIn.parametro1 = "000000100";
    				else throw new InputMismatchException("Parametro invalido");
    				
    				String[] x = in.parametro2.split(" ");	//[16,(endereço de memória que indica o label)]
    				x[1] = x[1].substring(1, x[1].length()-1);
    				
    				int aux = Integer.parseInt(x[0])/4;
    				newIn.parametro2 = Integer.toBinaryString(aux + Integer.parseInt(x[1]));    	
    				
    				newIn.parametro3 = "000000000";
    			}
    			
    			case "move": {
    				newIn.opcode = "01000";

    				if(in.parametro1 == "$s1") newIn.parametro1 = "000000001";
    				if(in.parametro1 == "$s2") newIn.parametro1 = "000000010";
    				if(in.parametro1 == "$s3") newIn.parametro1 = "000000011";
    				if(in.parametro1 == "$s4") newIn.parametro1 = "000000100";
    				else throw new InputMismatchException("Parametro invalido");
    				
    				if(in.parametro2 == "$s1") newIn.parametro2 = "000000001";
    				if(in.parametro2 == "$s2") newIn.parametro2 = "000000010";
    				if(in.parametro2 == "$s3") newIn.parametro2 = "000000011";
    				if(in.parametro2 == "$s4") newIn.parametro2 = "000000100";
    				else throw new InputMismatchException("Parametro invalido"); 
    				
    				newIn.parametro3 = "000000000";
    			}
    			
    			case "beq": {
    				
    				if(in.parametro1 == "$s1") newIn.parametro1 = "000000001";
    				if(in.parametro1 == "$s2") newIn.parametro1 = "000000010";
    				if(in.parametro1 == "$s3") newIn.parametro1 = "000000011";
    				if(in.parametro1 == "$s4") newIn.parametro1 = "000000100";
    				else throw new InputMismatchException("Parametro invalido");
    				
    				if(in.parametro2.contains("$s")) {	//se eh um registrador
	    				if(in.parametro2 == "$s1") {
	    					newIn.parametro2 = "000000001";
	    					newIn.opcode = "01010";
	    				}
	    				if(in.parametro2 == "$s2") {
	    					newIn.parametro2 = "000000010";
	    					newIn.opcode = "01010";
	    				}
	    				if(in.parametro2 == "$s3") {
	    					newIn.parametro2 = "000000011";
	    					newIn.opcode = "01010";
	    				}
	    				if(in.parametro2 == "$s4") {
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
    			}
    			
    			case "bne": {
    				if(in.parametro1 == "$s1") newIn.parametro1 = "000000001";
    				if(in.parametro1 == "$s2") newIn.parametro1 = "000000010";
    				if(in.parametro1 == "$s3") newIn.parametro1 = "000000011";
    				if(in.parametro1 == "$s4") newIn.parametro1 = "000000100";
    				else throw new InputMismatchException("Parametro invalido");
    				
    				if(in.parametro2.contains("$s")) {	//se eh um registrador
	    				if(in.parametro2 == "$s1") {
	    					newIn.parametro2 = "000000001";
	    					newIn.opcode = "01100";
	    				}
	    				if(in.parametro2 == "$s2") {
	    					newIn.parametro2 = "000000010";
	    					newIn.opcode = "01100";
	    				}
	    				if(in.parametro2 == "$s3") {
	    					newIn.parametro2 = "000000011";
	    					newIn.opcode = "01100";
	    				}
	    				if(in.parametro2 == "$s4") {
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
    			}
    			
    			case "j":{
    				newIn.opcode = "01101";
    				
    				newIn.parametro1 = Integer.toBinaryString(Integer.parseInt(in.parametro1));
    				if(newIn.parametro1.length() > 9) throw new InputMismatchException("Numero de bits excede o limite");      		
    				
    				newIn.parametro2 = "000000000";
    				newIn.parametro3 = "000000000";
    			}
    			
    			case "slt": {
    				newIn.opcode = "01110";
    				if(in.parametro1 == "$s1") newIn.parametro1 = "000000001";
    				if(in.parametro1 == "$s2") newIn.parametro1 = "000000010";
    				if(in.parametro1 == "$s3") newIn.parametro1 = "000000011";
    				if(in.parametro1 == "$s4") newIn.parametro1 = "000000100";
    				else throw new InputMismatchException("Parametro invalido");
    				
    				if(in.parametro2 == "$s1") newIn.parametro2 = "000000001";
    				if(in.parametro2 == "$s2") newIn.parametro2 = "000000010";
    				if(in.parametro2 == "$s3") newIn.parametro2 = "000000011";
    				if(in.parametro2 == "$s4") newIn.parametro2 = "000000100";
    				else throw new InputMismatchException("Parametro invalido");
    				
    				if(in.parametro3 == "$s1") newIn.parametro3 = "000000001";
    				if(in.parametro3 == "$s2") newIn.parametro3 = "000000010";
    				if(in.parametro3 == "$s3") newIn.parametro3 = "000000011";
    				if(in.parametro3 == "$s4") newIn.parametro3 = "000000100";  
    				else throw new InputMismatchException("Parametro invalido");    				
    			}
    			
    			case "la": {
    				newIn.opcode = "01111";
    				
    				if(in.parametro1 == "$s1") newIn.parametro1 = "000000001";
    				if(in.parametro1 == "$s2") newIn.parametro1 = "000000010";
    				if(in.parametro1 == "$s3") newIn.parametro1 = "000000011";
    				if(in.parametro1 == "$s4") newIn.parametro1 = "000000100";
    				else throw new InputMismatchException("Parametro invalido");
    				
    				newIn.parametro2 = Integer.toBinaryString(Integer.parseInt(label.get(in.parametro2)));
    			}
    		}
    	}
    }
    
}
