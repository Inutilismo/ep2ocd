package ep2_ocd;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.HashMap;
import java.util.Iterator;

class UC {
    private List<String> memoriaDeControle = new ArrayList<>();
    private int CAR;
    private String CBR;
    private Map<String,Integer> demuxCAR = new HashMap<>();
    //ap<Integer,String> portasEntrada = new HashMap<>();
    //Map<Integer,String> portasSaida = new HashMap<>();
    
    public UC (){
        preencheMemoriaDeControle();
        relacionaPortas();
        execucao();
    }

    private void administraCBR(String codigoRegistrador, String io){
        
        if(io.equals("i")){ //se for "i" é pra abrir a portinha de entrada do registrador
            if(codigoRegistrador.equals("000000001")) //s1
                CBR = CBR.substring(0, 5) + "1" + CBR.substring(6, CBR.length());
            else if(codigoRegistrador.equals("000000010")) //s2
                CBR = CBR.substring(0, 7) + "1" + CBR.substring(8, CBR.length());
            else if(codigoRegistrador.equals("000000011")) //s3
                CBR = CBR.substring(0, 9) + "1" + CBR.substring(10, CBR.length());
            else if(codigoRegistrador.equals("000000100")) //s4
                CBR = CBR.substring(0, 11) + "1" + CBR.substring(12, CBR.length());    

        }
        else {  //senao eh pra abrir a de saida
            if(codigoRegistrador.equals("000000001")) //s1
                CBR = CBR.substring(0, 6) + "1" + CBR.substring(7, CBR.length());
            else if(codigoRegistrador.equals("000000010")) //s2
                CBR = CBR.substring(0, 8) + "1" + CBR.substring(9, CBR.length());
            else if(codigoRegistrador.equals("000000011")) //s3
                CBR = CBR.substring(0, 10) + "1" + CBR.substring(11, CBR.length());
            else if(codigoRegistrador.equals("000000100")) //s4
                CBR = CBR.substring(0, 12) + "1" + CBR.substring(13, CBR.length()); 
        }
        
        //aqui lidamos com a parte de jump da palavra de controle
        if(CBR.charAt(38) == '1'){

            if(IR.opcode.equals("01101")){  //se for um jump

                CBR = CBR.substring(0, 40)+IR.P1;

            }

            else{   //se for um BEQ ou um BNE
                
                CBR = CBR.substring(0, 40)+ ULA.AC;
                
            }

        }
    }

    //metodo que controla a execucao das micro-operacoes
    private void execucao(){
        //este for eh utilizado APENAS para referencia da quantidade de instrucoes existentes na memoria
        //os parametros sao todos recebidos do IR
        
        

        for (int i = 0; i < MemoriaPrincipal.MemoriaPrincipalBinario.size(); i++){
            try{
                Instrucao memo = (Instrucao) MemoriaPrincipal.MemoriaPrincipalBinario.get(i);
            
                System.out.println("CICLO DE BUSCA COMECO ---------------------------------------------------------------------------------------------");
                CAR = 0;
                CBR = memoriaDeControle.get(CAR);
                executaLinhaCBR();
                CAR++;
                CBR = memoriaDeControle.get(CAR);
                executaLinhaCBR();
                CAR++;
                CBR = memoriaDeControle.get(CAR);
                executaLinhaCBR();
                CAR++;
                CBR = memoriaDeControle.get(CAR);
                executaLinhaCBR();
                
                System.out.println("CICLO DE BUSCA FINAL ---------------------------------------------------------------------------------------------");

                IR.separaInstrucao();
                System.out.println("OPCODE atual          :"+IR.opcode);
            
            
                System.out.println("ENTROU NO IF");
                //executa o ciclo de instrucao
                //Instrucao in  = (Instrucao) obj;
                String codigoRegistrador;
                switch (IR.opcode) {
                    case "00001":
                    System.out.println("ADD ---------------------------------------------------------------------------------------------");
                        CAR = demuxCAR.get("ADD");
                        CBR = memoriaDeControle.get(CAR);
                        codigoRegistrador = IR.P2;
                        // abre a portinha de saida do registrador cujo codigo esta no IR(P2)
                        administraCBR(codigoRegistrador,"o");
                        
                        // executa a primeira linha do microprograma
                        executaLinhaCBR();
                        CAR++;
                        CBR = memoriaDeControle.get(CAR);

                        // abre a portinha de saida do registrador cujo codigo esta no IR(P3)
                        codigoRegistrador = IR.P3;
                        administraCBR(codigoRegistrador,"o");

                        // executa a segunda linha do microprograma
                        executaLinhaCBR();
                        CAR++;
                        CBR = memoriaDeControle.get(CAR);                        
                        // abre a portinha de entrada registrador cujo codigo esta no IR(P1)
                        codigoRegistrador = IR.P1;
                        administraCBR(codigoRegistrador, "i");
                        executaLinhaCBR();
                        System.out.println("S1:                                  "+CPU.s1);
                        System.out.println("S2:                                  "+CPU.s2);
                        System.out.println("S3:                                  "+CPU.s3);
                        System.out.println("S4:                                  "+CPU.s4);
                        break;

                    case "00010":
                    System.out.println("ADDI ---------------------------------------------------------------------------------------------");
                        //3
                        CAR = demuxCAR.get("ADDI");
                        CBR = memoriaDeControle.get(CAR);
                        codigoRegistrador = IR.P2;
                        // abre a portinha de saida do registrador cujo codigo esta no IR(P2)
                        administraCBR(codigoRegistrador,"o");
                        
                        // executa a primeira linha do microprograma
                        executaLinhaCBR();
                        CAR++;
                        CBR = memoriaDeControle.get(CAR);
                        
                        // executa a segunda linha do microprograma
                        executaLinhaCBR();
                        CAR++;
                        CBR = memoriaDeControle.get(CAR);
                        
                        // abre a portinha de entrada registrador cujo codigo esta no IR(P1)
                        codigoRegistrador = IR.P1;
                        administraCBR(codigoRegistrador, "i");
                        executaLinhaCBR();
                        System.out.println("S1:                                  "+CPU.s1);
                        System.out.println("S2:                                  "+CPU.s2);
                        System.out.println("S3:                                  "+CPU.s3);
                        System.out.println("S4:                                  "+CPU.s4);
                        break;

                    case "00011": 
                    System.out.println("SUB ---------------------------------------------------------------------------------------------");                
                        //3
                        CAR = demuxCAR.get("SUB");
                        CBR = memoriaDeControle.get(CAR);

                        codigoRegistrador = IR.P2;
                        // abre a portinha de saida do registrador cujo codigo esta no IR(P2)
                        administraCBR(codigoRegistrador,"o");
                        
                        // executa a primeira linha do microprograma
                        executaLinhaCBR();
                        CAR++;
                        CBR = memoriaDeControle.get(CAR);

                        // abre a portinha de saida do registrador cujo codigo esta no IR(P3)
                        codigoRegistrador = IR.P3;
                        administraCBR(codigoRegistrador,"o");

                        // executa a segunda linha do microprograma
                        executaLinhaCBR();
                        CAR++;
                        CBR = memoriaDeControle.get(CAR);
                        
                        // abre a portinha de entrada registrador cujo codigo esta no IR(P1)
                        codigoRegistrador = IR.P1;
                        administraCBR(codigoRegistrador, "i");
                        executaLinhaCBR();
                        System.out.println("S1:                                  "+CPU.s1);
                        System.out.println("S2:                                  "+CPU.s2);
                        System.out.println("S3:                                  "+CPU.s3);
                        System.out.println("S4:                                  "+CPU.s4);
                        break;
                            
                    case "00100":                 
                        //3
                        System.out.println("SUB ---------------------------------------------------------------------------------------------");
                        CAR = demuxCAR.get("SUBI");
                        CBR = memoriaDeControle.get(CAR);

                        codigoRegistrador = IR.P2;
                        // abre a portinha de saida do registrador cujo codigo esta no IR(P2)
                        administraCBR(codigoRegistrador,"o");
                        
                        // executa a primeira linha do microprograma
                        executaLinhaCBR(); 
                        CAR++;
                        CBR = memoriaDeControle.get(CAR);
                        
                        // executa a segunda linha do microprograma
                        executaLinhaCBR();
                        CAR++;
                        CBR = memoriaDeControle.get(CAR);
                        
                        // abre a portinha de entrada registrador cujo codigo esta no IR(P1)
                        codigoRegistrador = IR.P1;
                        administraCBR(codigoRegistrador, "i");
                        executaLinhaCBR();
                        System.out.println("S1:                                  "+CPU.s1);
                        System.out.println("S2:                                  "+CPU.s2);
                        System.out.println("S3:                                  "+CPU.s3);
                        System.out.println("S4:                                  "+CPU.s4);
                        break;

                    case "00101":{                 
                        //1
                        System.out.println("LI ---------------------------------------------------------------------------------------------");
                        CAR = demuxCAR.get("LI");
                        CBR = memoriaDeControle.get(CAR);
                        codigoRegistrador = IR.P1;
                        administraCBR(codigoRegistrador, "i");
                        executaLinhaCBR();

                        System.out.println("S1                                   "+CPU.s1);
                        System.out.println("S2                                   "+CPU.s2);
                        System.out.println("S3                                   "+CPU.s3);
                        System.out.println("S4                                   "+CPU.s4);
                    }
                        break;

                    case "00110":
                    System.out.println("LW ---------------------------------------------------------------------------------------------");                 
                        //6
                        CAR = demuxCAR.get("LW");
                        CBR = memoriaDeControle.get(CAR);
                            
                        codigoRegistrador = IR.P3;
                        // abre a portinha de saida do registrador cujo codigo esta no IR(P2)
                        administraCBR(codigoRegistrador,"o");
                        executaLinhaCBR();
                        CAR++;
                        CBR = memoriaDeControle.get(CAR);

                        executaLinhaCBR();
                        CAR++;
                        CBR = memoriaDeControle.get(CAR);
                        
                        executaLinhaCBR();
                        CAR++;
                        CBR = memoriaDeControle.get(CAR);

                        executaLinhaCBR();
                        CAR++;
                        CBR = memoriaDeControle.get(CAR);
                        
                        executaLinhaCBR();
                        CAR++;
                        CBR = memoriaDeControle.get(CAR);

                        codigoRegistrador = IR.P1;
                        administraCBR(codigoRegistrador, "i");
                        executaLinhaCBR();
                        System.out.println("S1:                                  "+CPU.s1);
                        System.out.println("S2:                                  "+CPU.s2);
                        System.out.println("S3:                                  "+CPU.s3);
                        System.out.println("S4:                                  "+CPU.s4);
                        break;

                    case "00111":  
                    System.out.println("SW ---------------------------------------------------------------------------------------------");               
                        //5
                        CAR = demuxCAR.get("SW");
                        CBR = memoriaDeControle.get(CAR);

                        codigoRegistrador = IR.P3;
                        administraCBR(codigoRegistrador, "o");
                        executaLinhaCBR();
                        CAR++;
                        CBR = memoriaDeControle.get(CAR);
                        
                        executaLinhaCBR();
                        CAR++;
                        CBR = memoriaDeControle.get(CAR);
                        
                        executaLinhaCBR();
                        CAR++;
                        CBR = memoriaDeControle.get(CAR);

                        codigoRegistrador = IR.P1;
                        administraCBR(codigoRegistrador, "o");
                        executaLinhaCBR();
                        CAR++;
                        CBR = memoriaDeControle.get(CAR);

                        executaLinhaCBR();
                        System.out.println("S1:                                  "+CPU.s1);
                        System.out.println("S2:                                  "+CPU.s2);
                        System.out.println("S3:                                  "+CPU.s3);
                        System.out.println("S4:                                  "+CPU.s4);
                        break;
                        
                    case "01000":  
                    System.out.println("MOVE ---------------------------------------------------------------------------------------------");               
                        //1
                        CAR = demuxCAR.get("MOVE");
                        CBR = memoriaDeControle.get(CAR);

                        codigoRegistrador = IR.P1;
                        administraCBR(codigoRegistrador, "i");
                        codigoRegistrador = IR.P2;
                        administraCBR(codigoRegistrador, "o");
                        executaLinhaCBR();
                        System.out.println("S1:                                  "+CPU.s1);
                        System.out.println("S2:                                  "+CPU.s2);
                        System.out.println("S3:                                  "+CPU.s3);
                        System.out.println("S4:                                  "+CPU.s4); 
                        break;

                    case "01010": 
                    System.out.println("BEQRR ---------------------------------------------------------------------------------------------");                
                        //3
                        CAR = demuxCAR.get("BEQrr");
                        CBR = memoriaDeControle.get(CAR);

                        codigoRegistrador = IR.P1;
                        administraCBR(codigoRegistrador, "o");
                        executaLinhaCBR();
                        CAR++;
                        CBR = memoriaDeControle.get(CAR);

                        codigoRegistrador = IR.P2;
                        administraCBR(codigoRegistrador, "o");
                        executaLinhaCBR();
                        CAR++;
                        CBR = memoriaDeControle.get(CAR);

                        if(ULA.AC.equals("1")) {
                            
                            administraCBR(null, null);
                            executaLinhaCBR();
                            
                        }
                        System.out.println("S1:                                  "+CPU.s1);
                        System.out.println("S2:                                  "+CPU.s2);
                        System.out.println("S3:                                  "+CPU.s3);
                        System.out.println("S4:                                  "+CPU.s4); 
                        break;

                    case "01001": 
                    System.out.println("BEQRC ---------------------------------------------------------------------------------------------");                
                        //3
                        CAR = demuxCAR.get("BEQrc");
                        CBR = memoriaDeControle.get(CAR);

                        codigoRegistrador = IR.P1;
                        administraCBR(codigoRegistrador, "o");
                        executaLinhaCBR();
                        CAR++;
                        CBR = memoriaDeControle.get(CAR);

                        executaLinhaCBR();
                        CAR++;
                        CBR = memoriaDeControle.get(CAR);

                        if(ULA.AC.equals("1")) {
                            administraCBR(null, null);
                            executaLinhaCBR();
                            
                        }
                        System.out.println("S1:                                  "+CPU.s1);
                        System.out.println("S2:                                  "+CPU.s2);
                        System.out.println("S3:                                  "+CPU.s3);
                        System.out.println("S4:                                  "+CPU.s4);
                            
                        break;

                    case "01100": 
                    System.out.println("BNQRR ---------------------------------------------------------------------------------------------");                
                        //3
                        CAR = demuxCAR.get("BNQrr");
                        CBR = memoriaDeControle.get(CAR);

                        codigoRegistrador = IR.P1;
                        administraCBR(codigoRegistrador, "o");
                        executaLinhaCBR();
                        CAR++;
                        CBR = memoriaDeControle.get(CAR);

                        codigoRegistrador = IR.P2;
                        administraCBR(codigoRegistrador, "o");
                        executaLinhaCBR();
                        CAR++;
                        CBR = memoriaDeControle.get(CAR);

                        if(ULA.AC.equals("1")) {
                            administraCBR(null, null);
                            executaLinhaCBR();
                            
                        }
                        System.out.println("S1:                                  "+CPU.s1);
                        System.out.println("S2:                                  "+CPU.s2);
                        System.out.println("S3:                                  "+CPU.s3);
                        System.out.println("S4:                                  "+CPU.s4);
                        break;

                    case "01011":                 
                        //3
                        System.out.println("BNQrc ---------------------------------------------------------------------------------------------");
                        CAR = demuxCAR.get("BNQrc");
                        CBR = memoriaDeControle.get(CAR);

                        codigoRegistrador = IR.P1;
                        administraCBR(codigoRegistrador, "o");
                        executaLinhaCBR();
                        CAR++;
                        CBR = memoriaDeControle.get(CAR);

                        executaLinhaCBR();
                        CAR++;
                        CBR = memoriaDeControle.get(CAR);

                        if(ULA.AC.equals("1")) {
                            administraCBR(null, null);
                            executaLinhaCBR();                           
                        }
                        System.out.println("S1:                                  "+CPU.s1);
                        System.out.println("S2:                                  "+CPU.s2);
                        System.out.println("S3:                                  "+CPU.s3);
                        System.out.println("S4:                                  "+CPU.s4);
                        break;

                    case "01101":                 
                        //1
                        System.out.println("J ---------------------------------------------------------------------------------------------");
                        CAR = demuxCAR.get("J");
                        CBR = memoriaDeControle.get(CAR);
                        administraCBR(null, null);
                        executaLinhaCBR();
                        System.out.println("S1:                                  "+CPU.s1);
                        System.out.println("S2:                                  "+CPU.s2);
                        System.out.println("S3:                                  "+CPU.s3);
                        System.out.println("S4:                                  "+CPU.s4);
                        break;

                    case "01110":                 
                        //3
                        System.out.println("SLT ---------------------------------------------------------------------------------------------");
                        CAR = demuxCAR.get("SLT");
                        CBR = memoriaDeControle.get(CAR);

                        codigoRegistrador = IR.P2;
                        administraCBR(codigoRegistrador, "o");
                        executaLinhaCBR();
                        CAR++;
                        CBR = memoriaDeControle.get(CAR);

                        codigoRegistrador = IR.P3;
                        administraCBR(codigoRegistrador, "o");
                        executaLinhaCBR();
                        CAR++;
                        CBR = memoriaDeControle.get(CAR);

                        codigoRegistrador = IR.P1;
                        administraCBR(codigoRegistrador, "i");
                        executaLinhaCBR();
                        CAR++;
                        CBR = memoriaDeControle.get(CAR);
                        System.out.println("S1:                                  "+CPU.s1);
                        System.out.println("S2:                                  "+CPU.s2);
                        System.out.println("S3:                                  "+CPU.s3);
                        System.out.println("S4:                                  "+CPU.s4);    
                        break;

                    case "01111":                 
                        //4
                        System.out.println("LA ---------------------------------------------------------------------------------------------");
                        CAR = demuxCAR.get("LA");
                        CBR = memoriaDeControle.get(CAR);
                        executaLinhaCBR();
                        CAR++;
                        CBR = memoriaDeControle.get(CAR);

                        executaLinhaCBR();
                        CAR++;
                        CBR = memoriaDeControle.get(CAR);

                        executaLinhaCBR();
                        CAR++;
                        CBR = memoriaDeControle.get(CAR);

                        codigoRegistrador = IR.P1;
                        administraCBR(codigoRegistrador, "i");
                        executaLinhaCBR();
                        System.out.println("S1:                                  "+CPU.s1);
                        System.out.println("S2:                                  "+CPU.s2);
                        System.out.println("S3:                                  "+CPU.s3);
                        System.out.println("S4:                                  "+CPU.s4);    
                        break;
                        
                
                    default:
                        break;
                }
           } catch(Exception e){}
        }
    }

    private void executaLinhaCBR(){

        

        //barramento interno recebendo os dados dos registradores
        CPU.barramentoInterno = administraPortasDeSaidaInterna(CPU.barramentoInterno, CBR);

        //barramento externo recebendo os dados dos registradores 
        CPU.barramentoMemoria = administraPortasDeSaidaExterna(CPU.barramentoMemoria, CBR);

        //registradores recebendo os dados do barramento interno
        administraPortasDeEntradaInterna(CPU.barramentoInterno, CBR);

        //registradores recebendo os dados do barramento externo
        administraPortasDeEntradaExterna(CPU.barramentoMemoria, CBR);
        
        //sinais de controle ula
        String[] recebeCodigo = CBR.split(" ");
        ULA.executaSinalDeControle(recebeCodigo[1]);

        //sinais de controle memoria
        MemoriaPrincipal.execSinaldeControle(recebeCodigo[2]);  

        System.out.println("CBR: " + CBR);
        System.out.println("PC: " + CPU.PC);
        System.out.println("MAR: " + CPU.MAR);
        System.out.println("Memoria: " + MemoriaPrincipal.enderecoMar);
        System.out.println("barramento interno: " + CPU.barramentoInterno);
        System.out.println("barramento externo: " + CPU.barramentoMemoria);
 
    }

    private String administraPortasDeSaidaInterna(String barramento, String CBR){
        //System.out.println("barramento: " + barramento);
        for(int i = 1; i < 24; i++){
            if(CBR.charAt(i-1) == '1'){
                switch(i){
                    case 2:{
                        barramento = CPU.PC;
                    }break;

                    case 5:{
                        barramento = CPU.MBR;
                    }break;

                    case 7:{
                        barramento = CPU.s1;
                    }break;

                    case 9:{
                        barramento = CPU.s2;
                    }break;

                    case 11:{
                        barramento = CPU.s3;
                    }break;

                    case 13:{
                        barramento = CPU.s4;
                    }break;

                    case 16:{
                        barramento = IR.P1;
                    }break;

                    case 18:{
                        barramento = IR.P2;
                    }break;

                    case 20:{
                        barramento = IR.P3;
                    }break;

                    case 23:{
                        barramento = ULA.AC;
                    }break;

                }
            }
        }
        return barramento;
    }

    private String administraPortasDeSaidaExterna(String barramento, String CBR){
        //System.out.println("barramento: " + barramento);
        for(int i = 24; i < 29; i++){
            if(CBR.charAt(i-1) == '1'){
                switch(i){

                    case 24:{
                        barramento = CPU.MAR;
                    }break;

                    case 25:{
                        barramento = CPU.MBR;
                    }break;

                    case 28:{
                        barramento = MemoriaPrincipal.retornoMemoria;
                    }break;

                }
            }
        }
        return barramento;
    }

    private void administraPortasDeEntradaInterna(String barramento, String CBR){
        //System.out.println("barramento: " + barramento);
        for(int i = 1; i < 24; i++){
            if(CBR.charAt(i-1) == '1'){
                switch(i){
                    case 1:{
                        CPU.PC = barramento;
                    }break;

                    case 3:{
                        CPU.MAR = barramento;
                    }break;

                    case 4:{
                        CPU.MBR = barramento;
                    }break;

                    case 6:{
                        CPU.s1 = barramento;
                    }break;

                    case 8:{
                        CPU.s2 = barramento;
                    }break;

                    case 10:{
                        CPU.s3 = barramento;
                    }break;

                    case 12:{
                        CPU.s4 = barramento;
                    }break;

                    case 14:{
                        IR.opcode = barramento;
                    }break;

                    case 15:{
                        IR.P1 = barramento;
                    }break;

                    case 17:{
                        IR.P2 = barramento;
                    }break;

                    case 19:{
                        IR.P3 = barramento;
                    }break;

                    case 21:{
                        ULA.X = barramento;
                    }break;

                    case 22:{
                        ULA.valor = barramento;
                    }break;

                }
            }
        }

    }

    private void administraPortasDeEntradaExterna(String barramento, String CBR){
        //System.out.println("barramento: " + barramento);
        for(int i = 26; i < 29; i++){
            if(CBR.charAt(i-1) == '1'){
                switch(i){

                    case 26:{
                        CPU.MBR = barramento;
                    }break;

                    case 27:{
                        if(CBR.charAt(23) == '1'){  //Sabemos que ta passando enderecos
                            MemoriaPrincipal.enderecoMar = Integer.toString(Integer.parseInt(barramento, 2));
                        }
                        else if(CBR.charAt(24) == '1'){ //Sabemos que esta passando dados
                            MemoriaPrincipal.valorMBR = barramento;
                        }
                        
                    }break;

                }
            }
        }

    }

    private void relacionaPortas(){
       /* 
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
        
        portasSaida.put(2, "PC");
        portasSaida.put(5, "MBR");
        portasSaida.put(7, "s1");
        portasSaida.put(9, "s2");
        portasSaida.put(11, "s3");
        portasSaida.put(13, "s4");
        portasSaida.put(16, "P1");
        portasSaida.put(18, "P2");
        portasSaida.put(20, "P3");
        portasSaida.put(23, "AC");
        portasSaida.put(24, "MAR");
        portasSaida.put(25, "MBR");
        portasSaida.put(28, "MemoriaPrincipal");       
*/
    }

    private void preencheMemoriaDeControle(){
    //quais portas devo abrir | o que a ULA tem que fazer | sinais de controle da memória | condição de pulo | endereço de pulo
    //0000000000000000000000000000 0000 00 0 000000000
    
    //------------------------ciclo de busca
    memoriaDeControle.add("0110000000000000000001000000 0000 000 0 000000000");   //t1 0
    memoriaDeControle.add("0000000000000000000000010010 0001 001 0 000000000");   //t2
    memoriaDeControle.add("1000000000000000000000100101 0000 000 0 000000000");   //t3
    memoriaDeControle.add("0000100000000100000000000000 0000 000 0 000000000");   //t4 
    
    //------------------------ciclos de execucao
    //ADD                  000000001000000000001
    memoriaDeControle.add("0000000000000000000010000000 0000 000 0 000000000");   //t1 4
    memoriaDeControle.add("0000000000000000000001000000 0010 000 0 000000000");   //t2
    memoriaDeControle.add("0000000000000000000000100000 0000 000 0 000000000");   //t3 

    //ADDI
    memoriaDeControle.add("0000000000000000000010000000 0000 000 0 000000000");   //t1 7
    memoriaDeControle.add("0000000000000000000101000000 0011 000 0 000000000");   //t2
    memoriaDeControle.add("0000000000000000000000100000 0000 000 0 000000000");   //t3

    //SUB
    memoriaDeControle.add("0000000000000000000010000000 0000 000 0 000000000");   //t1 10
    memoriaDeControle.add("0000000000000000000001000000 0100 000 0 000000000");   //t2
    memoriaDeControle.add("0000000000000000000000100000 0000 000 0 000000000");   //t3

    //SUBI
    memoriaDeControle.add("0000000000000000000010000000 0000 000 0 000000000");   //t1 13
    memoriaDeControle.add("0000000000000000000101000000 0101 000 0 000000000");   //t2
    memoriaDeControle.add("0000000000000000000000100000 0000 000 0 000000000");   //t3
    
    //LI
    memoriaDeControle.add("0000000000000000010000000000 0000 000 0 000000000");   //t1 16

    //LW
    memoriaDeControle.add("0000000000000000000010000000 0000 000 0 000000000");   //t1 17
    memoriaDeControle.add("0000000000000000010001000000 0010 000 0 000000000");   //t2
    memoriaDeControle.add("0010000000000000000000100000 0000 000 0 000000000");   //t3 
    memoriaDeControle.add("0000000000000000000000010010 0000 001 0 000000000");   //t4
    memoriaDeControle.add("0000000000000000000000000101 0000 000 0 000000000");   //t5
    memoriaDeControle.add("0000100000000000000000000000 0000 000 0 000000000");   //t6

    //SW
    memoriaDeControle.add("0000000000000000000010000000 0000 000 0 000000000");   //t1 23
    memoriaDeControle.add("0000000000000000010001000000 0010 000 0 000000000");   //t2
    memoriaDeControle.add("0010000000000000000000100000 0000 000 0 000000000");   //t3 
    memoriaDeControle.add("0001000000000000000000010010 0000 000 0 000000000");   //t4
    memoriaDeControle.add("0000000000000000000000001010 0000 010 0 000000000");   //t5

    //MOVE
    memoriaDeControle.add("0000000000000000000000000000 0000 000 0 000000000");   //t1 28

    //BEQrr
    memoriaDeControle.add("0000000000000000000010000000 0000 000 0 000000000");   //t1 29
    memoriaDeControle.add("0000000000000000000001000000 0110 000 0 000000000");   //t2
    memoriaDeControle.add("1000000000000000000100000000 0000 000 1 000000000");   //t3

    //BEQrc
    memoriaDeControle.add("0000000000000000000010000000 0000 000 0 000000000");   //t1 32
    memoriaDeControle.add("0000000000000000010001000000 0111 000 0 000000000");   //t2
    memoriaDeControle.add("1000000000000000000100000000 0000 000 1 000000000");   //t3

    //BNErr
    memoriaDeControle.add("0000000000000000000010000000 0000 000 0 000000000"); //t1 35
    memoriaDeControle.add("0000000000000000000001000000 1000 000 0 000000000"); //t2
    memoriaDeControle.add("1000000000000000000100000000 0000 000 1 000000000"); //t3

    //BNErc
    memoriaDeControle.add("0000000000000000000010000000 0000 000 0 000000000"); //t1 38
    memoriaDeControle.add("0000000000000000010001000000 1001 000 0 000000000"); //t2
    memoriaDeControle.add("1000000000000000000100000000 0000 000 1 000000000"); //t3

    //j
    memoriaDeControle.add("1000000000000001000000000000 0000 000 1 000000000"); //t1 41

    //slt
    memoriaDeControle.add("0000000000000000000010000000 0000 000 0 000000000");   //t1 42
    memoriaDeControle.add("0000000000000000000001000000 1010 000 0 000000000");   //t2
    memoriaDeControle.add("0000000000000000000000100000 0000 000 0 000000000");   //t3

    //la
    memoriaDeControle.add("0010000000000000010000000000 0000 000 0 000000000");   //t1 45
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
    demuxCAR.put("SW", 23);
    demuxCAR.put("MOVE", 28);
    demuxCAR.put("BEQrr", 29);
    demuxCAR.put("BEQrc", 32);
    demuxCAR.put("BNErr", 35);
    demuxCAR.put("BNErc", 38);
    demuxCAR.put("J", 41);
    demuxCAR.put("SLT", 42);
    demuxCAR.put("LA", 45);
    demuxCAR.put("aux", memoriaDeControle.size()-1);
    }

}
