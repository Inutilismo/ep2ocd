package ep2_ocd;

import java.util.ArrayList;
import java.util.List;

public class MemoriaPrincipal {

    public static List<Object> MemoriaPrincipalBinario = new ArrayList<>();
    public static Object valorMBR;
    public static String enderecoMar;
    public static String retornoMemoria;

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

    public static void read(){
        System.out.println("mar que a memoria recebeu: " + enderecoMar);
        if(MemoriaPrincipalBinario.get(Integer.parseInt(enderecoMar)) instanceof Instrucao){
            Instrucao aux = (Instrucao) MemoriaPrincipalBinario.get(Integer.parseInt(enderecoMar));
            retornoMemoria = aux.opcode + " " + aux.parametro1 + " " + aux.parametro2 + " " + aux.parametro3;
        }
        else {
            retornoMemoria = (String) MemoriaPrincipalBinario.get(Integer.parseInt(enderecoMar));
        }
    }

    public static void write(){
        MemoriaPrincipalBinario.add(Integer.parseInt(enderecoMar), valorMBR);
    }

    public static void address(){
        retornoMemoria = enderecoMar;
    }


}