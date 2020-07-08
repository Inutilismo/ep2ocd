package ep2_ocd;

import java.util.ArrayList;
import java.util.List;

public class MemoriaPrincipal {

    public static List<Object> MemoriaPrincipalBinario = new ArrayList<>();
    public static Object valorMBR;
    public static String enderecoMar;
    public static Object retornoMemoria;

    public static void executaSinaldeControle(String sinalDeControle){

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
        retornoMemoria = MemoriaPrincipalBinario.get(Integer.parseInt(enderecoMar));
    }

    public static void write(){
        MemoriaPrincipalBinario.add(Integer.parseInt(enderecoMar), valorMBR);
    }

    public static void address(){
        //retornoMemoria = MemoriaPrincipalBinario.

    }


}