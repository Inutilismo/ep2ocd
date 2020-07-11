package ep2_ocd;

import java.util.ArrayList;
import java.util.List;

public class MemoriaPrincipal {

    public static List<Object> MemoriaPrincipalBinario = new ArrayList<>();

    //Armazena os valores enviados pelo MBR
    public static Object valorMBR;

    //Armazena os valores enviados pelo MAR
    public static String enderecoMar;

    public static String retornoMemoria;

    /* Metodo que simula o funcionamento de um
	 * DEMUX das operacoes da memoria principal baseado no 
	 * sinal de controle recebido pelo barramento
	 */
    public static void execSinaldeControle(String sinalDeControle){

        switch (sinalDeControle) { 
            case "001":
                read();
                break;

            case "010":
                write();
                break;

            case "100":
                address();
                break;
        
        }
    }

    /*
	 * Os metodos abaixo realizam as operacoes que a memoria principal
	 * realiza e armazenam o retorno na variavel retornoMemoria
	 */

    public static void read(){

        //print da memoria completa
       for(int i = 0; i<MemoriaPrincipalBinario.size(); i++){
            System.out.println("MEMORIA:                           " + MemoriaPrincipalBinario.get(i));
        }
        //System.out.println("mar que a memoria recebeu: " + enderecoMar);

        //checa se o comando read foi chamado para retornar um codigo em assembly
        if(MemoriaPrincipalBinario.get(Integer.parseInt(enderecoMar)) instanceof Instrucao){
            Instrucao aux = (Instrucao) MemoriaPrincipalBinario.get(Integer.parseInt(enderecoMar));
            retornoMemoria = aux.opcode + " " + aux.parametro1 + " " + aux.parametro2 + " " + aux.parametro3;
        }
        //ou um valor qualquer aramzenado
        else {
            retornoMemoria = (String) MemoriaPrincipalBinario.get(Integer.parseInt(enderecoMar));
        }
    }

    public static void write(){
        //System.out.println("adicionou no write");
        MemoriaPrincipalBinario.set(Integer.parseInt(enderecoMar), valorMBR);
    }

    public static void address(){
        retornoMemoria = enderecoMar;
    }


}