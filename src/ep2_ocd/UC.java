package ep2_ocd;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.HashMap;

class UC {
    private List<String> memoriaDeControle = new ArrayList<>();
    private int CAR;
    private String CBR;
    private Map<String,Integer> demuxCAR = new HashMap<>();
    Map<Integer,String> portasEntrada = new HashMap<>();
    Map<Integer,String> portasSaida = new HashMap<>();
    
    public UC (){
        preencheMemoriaDeControle();
        relacionaPortas();
        execucao();
    }

    //metodo que controla a execucao das micro-operacoes
    private void execucao(){
        CAR = 0;
        CBR = memoriaDeControle.get(CAR);
        executaLinhaCBR();
        for (Object obj : MemoriaPrincipal.MemoriaPrincipalBinario){
           if(obj instanceof Instrucao){
                //executa o ciclo de instrucao
           }
            
            //chama o ciclo de busca dnv
        }
    }

    private void executaLinhaCBR(){
        //PROBLEMAS COM A MEMORIA - NAO CONSEGUIMOS DIFERENCIAR
        //QUANDO A MEMORIA RECEBE DADOS DO MAR OU DO MBR

        //barramento interno recebendo os dados dos registradores
        for(int i = 0; i < 24; i++){
            if(CBR.charAt(i) == '1' && portasSaida.containsKey(i)) CPU.barramentoInterno = portasSaida.get(i);           
        }
        
        //sinais de controle ula
        String[] recebeCodigo = CBR.split(" ");
        CPU.ULA.executaSinalDeControle(recebeCodigo[1]);

        //registradores recebendo os dados do barramento interno
        administraPortasDeEntrada(CPU.barramentoInterno, CBR);
        
        //barramento externo recebendo os dados dos registradores 
        for(int i = 24; i < 28; i++){
            if(CBR.charAt(i) == '1' && portasSaida.containsKey(i)) CPU.barramentoMemoria = portasSaida.get(i);      
        }

        //sinais de controle memoria
        MemoriaPrincipal.executaSinaldeControle(recebeCodigo[2]);

        //condição de pulo | endereço de pulo

        //registradores recebendo os dados do barramento externo
        administraPortasDeEntrada(CPU.barramentoMemoria, CBR);
        
    }

    private void administraPortasDeEntrada(String barramento, String CBR){

        for(int i = 0; i < 28; i++){
            if(CBR.charAt(i) == '1'){
                switch(i){
                    case 0:{
                        CPU.PC = barramento;
                    }break;

                    case 2:{
                        CPU.MAR = barramento;
                    }break;

                    case 3:{
                        CPU.MBR = barramento;
                    }break;

                    case 5:{
                        CPU.s1 = barramento;
                    }break;

                    case 7:{
                        CPU.s2 = barramento;
                    }break;

                    case 9:{
                        CPU.s3 = barramento;
                    }break;

                    case 11:{
                        CPU.s4 = barramento;
                    }break;

                    case 13:{
                        CPU.IR.opcode = barramento;
                    }break;

                    case 14:{
                        CPU.IR.P1 = barramento;
                    }break;

                    case 16:{
                        CPU.IR.P2 = barramento;
                    }break;

                    case 18:{
                        CPU.IR.P3 = barramento;
                    }break;

                    case 20:{
                        CPU.ULA.X = barramento;
                    }break;

                    case 21:{
                        CPU.ULA.valor = barramento;
                    }break;

                    case 25:{
                        CPU.MBR = barramento;
                    }break;

                    case 26:{
                        MemoriaPrincipal.enderecoMar = barramento;
                    }break;

                }
            }
        }

    }

    private void relacionaPortas(){
        
        portasEntrada.put(1, "PC");
        portasEntrada.put(3, "MAR");
        portasEntrada.put(4, "MBR");
        portasEntrada.put(6, "s1");
        portasEntrada.put(8, "s2");
        portasEntrada.put(10, "s3");
        portasEntrada.put(12, "s4");
        portasEntrada.put(14, "opcode");
        portasEntrada.put(15, "P1");
        portasEntrada.put(17, "P2");
        portasEntrada.put(19, "P3");
        portasEntrada.put(21, "X");
        portasEntrada.put(22, "ULA");
        portasEntrada.put(26, "MBR");
        portasEntrada.put(27, "MemoriaPrincipal");

        
        portasSaida.put(2, CPU.PC);
        portasSaida.put(5, CPU.MBR);
        portasSaida.put(7, CPU.s1);
        portasSaida.put(9, CPU.s2);
        portasSaida.put(11, CPU.s3);
        portasSaida.put(13, CPU.s4);
        portasSaida.put(16, CPU.IR.P1);
        portasSaida.put(18, CPU.IR.P2);
        portasSaida.put(20, CPU.IR.P3);
        portasSaida.put(23, CPU.ULA.AC);
        portasSaida.put(24, CPU.MAR);
        portasSaida.put(25, CPU.MBR);
        portasSaida.put(28, MemoriaPrincipal.retornoMemoria);       

    }

    private void preencheMemoriaDeControle(){
    //quais portas devo abrir | o que a ULA tem que fazer | sinais de controle da memória | condição de pulo | endereço de pulo
    //0000000000000000000000000000 0000 00 0 000000000
    
    //------------------------ciclo de busca
    memoriaDeControle.add("0110000000000000000001000000 0000 000 0 000000000");   //t1 
    memoriaDeControle.add("0000000000000000000000010010 0001 001 0 000000000");   //t2
    memoriaDeControle.add("1000000000000000000000100101 0000 010 0 000000000");   //t3
    memoriaDeControle.add("0000100000000100000000000000 0000 000 0 000000000");   //t4
    
    //------------------------ciclos de execucao
    //ADD 
    memoriaDeControle.add("0000000000000000000010000000 0000 000 0 000000000");   //t1 
    memoriaDeControle.add("0000000000000000000001000000 0010 000 0 000000000");   //t2
    memoriaDeControle.add("0000000000000000000000100000 0000 000 0 000000000");   //t3

    //ADDI
    memoriaDeControle.add("0000000000000000000010000000 0000 000 0 000000000");   //t1 
    memoriaDeControle.add("0000000000000000000101000000 0011 000 0 000000000");   //t2
    memoriaDeControle.add("0000000000000000000000100000 0000 000 0 000000000");   //t3

    //SUB
    memoriaDeControle.add("0000000000000000000010000000 0000 000 0 000000000");   //t1 
    memoriaDeControle.add("0000000000000000000001000000 0100 000 0 000000000");   //t2
    memoriaDeControle.add("0000000000000000000000100000 0000 000 0 000000000");   //t3

    //SUBI
    memoriaDeControle.add("0000000000000000000010000000 0000 000 0 000000000");   //t1 
    memoriaDeControle.add("0000000000000000000101000000 0101 000 0 000000000");   //t2
    memoriaDeControle.add("0000000000000000000000100000 0000 000 0 000000000");   //t3
    
    //LI
    memoriaDeControle.add("0000000000000000010000000000 0000 000 0 000000000");   //t1 

    //LW
    memoriaDeControle.add("0010000000000000010000000000 0000 000 0 000000000");   //t1 
    memoriaDeControle.add("0000000000000000000000010010 0000 001 0 000000000");   //t2
    memoriaDeControle.add("0000000000000000000000000101 0000 001 0 000000000");   //t3
    memoriaDeControle.add("0000100000000000000000000000 0000 001 0 000000000");   //t4

    //SW
    memoriaDeControle.add("0010000000000000010000000000 0000 000 0 000000000");   //t1 
    memoriaDeControle.add("0001000000000000000000010010 0000 010 0 000000000");   //t2
    memoriaDeControle.add("0000000000000000000000001010 0000 000 0 000000000");   //t3

    //MOVE
    memoriaDeControle.add("0000000000000000000000000000 0000 000 0 000000000");   //t1 

    //BEQrr
    memoriaDeControle.add("0000000000000000000010000000 0000 000 0 000000000");   //t1
    memoriaDeControle.add("0000000000000000000001000000 0110 000 0 000000000");   //t2
    memoriaDeControle.add("0010000000000000000100000000 0000 000 0 000000000");   //t3
    memoriaDeControle.add("0000000000000000000000010010 0000 000 0 000000000");   //t4
    memoriaDeControle.add("0000000000000000000000000101 0000 100 0 000000000");   //t5
    memoriaDeControle.add("0100100000000000000000000000 0000 000 0 000000000");   //t6

    //BEQrc
    memoriaDeControle.add("0000000000000000000010000000 0000 000 0 000000000");   //t1
    memoriaDeControle.add("0000000000000000010001000000 0111 000 0 000000000");   //t2
    memoriaDeControle.add("0010000000000000000100000000 0000 000 0 000000000");   //t3
    memoriaDeControle.add("0000000000000000000000010010 0000 000 0 000000000");   //t4
    memoriaDeControle.add("0000000000000000000000000101 0000 100 0 000000000");   //t5
    memoriaDeControle.add("0100100000000000000000000000 0000 000 0 000000000");   //t6

    //BNErr
    memoriaDeControle.add("0000000000000000000010000000 0000 000 0 000000000"); //t1
    memoriaDeControle.add("0000000000000000000001000000 1000 000 0 000000000"); //t2
    memoriaDeControle.add("0010000000000000000100000000 0000 000 0 000000000"); //t3
    memoriaDeControle.add("0000000000000000000000010010 0000 100 0 000000000"); //t4
    memoriaDeControle.add("0000000000000000000000000101 0000 000 0 000000000"); //t5
    memoriaDeControle.add("1000100000000000000000000000 0000 000 0 000000000"); //t6

    //BNErc
    memoriaDeControle.add("0000000000000000000010000000 0000 000 0 000000000"); //t1
    memoriaDeControle.add("0000000000000000010001000000 1001 000 0 000000000"); //t2
    memoriaDeControle.add("0010000000000000000100000000 0000 000 0 000000000"); //t3
    memoriaDeControle.add("0000000000000000000000010010 0000 100 0 000000000"); //t4
    memoriaDeControle.add("0000000000000000000000000101 0000 000 0 000000000"); //t5
    memoriaDeControle.add("1000100000000000000000000000 0000 000 0 000000000"); //t6

    //j
    memoriaDeControle.add("0010000000000001000000000000 0000 000 0 000000000"); //t1
    memoriaDeControle.add("0000000000000000000000010010 0000 100 0 000000000"); //t2
    memoriaDeControle.add("0000000000000000000000000101 0000 000 0 000000000"); //t3
    memoriaDeControle.add("1001000000000000000000000000 0000 000 0 000000000"); //t4

    //slt
    memoriaDeControle.add("0000000000000000000010000000 0000 000 0 000000000");   //t1
    memoriaDeControle.add("0000000000000000000001000000 1010 000 0 000000000");   //t2
    memoriaDeControle.add("0000000000000000000000100000 0000 000 0 000000000");   //t3

    //la
    memoriaDeControle.add("0010000000000000010000000000 0000 000 0 000000000");   //t1
    memoriaDeControle.add("0000000000000000000000010010 0000 100 0 000000000");   //t2
    memoriaDeControle.add("0000000000000000000000000101 0000 000 0 000000000");   //t3
    memoriaDeControle.add("0000100000000000000000000000 0000 000 0 000000000");   //t4

    demuxCAR.put("CicloDeBusca", 0);
    demuxCAR.put("ADD", 4);
    demuxCAR.put("ADDI", 7);
    demuxCAR.put("SUB", 10);
    demuxCAR.put("SUBI", 13);
    demuxCAR.put("LI", 16);
    demuxCAR.put("LW", 17);
    demuxCAR.put("SW", 21);
    demuxCAR.put("MOVE", 24);
    demuxCAR.put("BEQrr", 25);
    demuxCAR.put("BEQrc", 31);
    demuxCAR.put("BNErr", 37);
    demuxCAR.put("BNErc", 43);
    demuxCAR.put("J", 49);
    demuxCAR.put("SLT", 53);
    demuxCAR.put("LA", 56);

    }

}
