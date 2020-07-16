package ep2_ocd;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.HashMap;

class UC {
    private List<String> memoriaDeControle = new ArrayList<>();
    public static int CAR;
    public static String CBR;
    public static Map<String,Integer> demuxCAR = new HashMap<>();

    //ArrayList que armazena todas as palavras de controle do codigo recem executado em ordem
    public static List<String> codigoCompilado = new ArrayList<>();

    //ArrayList que armazena todas as micro-operacoes cujas palavras de controle estao sendo executadas
    public static List<String> microInstrucao = new ArrayList<>();

    //ArrayList que armazena todas as micro-operacoes na ordem que foram executadas
    public static List<String> microCompilado = new ArrayList<>();
    
    /*
     * Construtor que realiza a primeira
     * execucao completa do codigo inserido
     */
    public UC (){
        preencheMemoriaDeControle();
        execucao();
    }

    /*
     * Metodo que 'personaliza' a parte das portas logicas da palavra de controle armazenada no CBR
     * com os registradores especificados pelo programador do codigo em assembly
     * ("i" equivale ao input, "o" equivale ao output) e tambem a parte relacionada
     * aos jumps 
     */
    private void administraCBR(String codigoRegistrador, String io){
        
        if(io.equals("i")){
            if(codigoRegistrador.equals("000000001")) //s1
                CBR = CBR.substring(0, 5) + "1" + CBR.substring(6, CBR.length());
            else if(codigoRegistrador.equals("000000010")) //s2
                CBR = CBR.substring(0, 7) + "1" + CBR.substring(8, CBR.length());
            else if(codigoRegistrador.equals("000000011")) //s3
                CBR = CBR.substring(0, 9) + "1" + CBR.substring(10, CBR.length());
            else if(codigoRegistrador.equals("000000100")) //s4
                CBR = CBR.substring(0, 11) + "1" + CBR.substring(12, CBR.length());    

        }
        else {
            if(codigoRegistrador.equals("000000001")) //s1
                CBR = CBR.substring(0, 6) + "1" + CBR.substring(7, CBR.length());
            else if(codigoRegistrador.equals("000000010")) //s2
                CBR = CBR.substring(0, 8) + "1" + CBR.substring(9, CBR.length());
            else if(codigoRegistrador.equals("000000011")) //s3
                CBR = CBR.substring(0, 10) + "1" + CBR.substring(11, CBR.length());
            else if(codigoRegistrador.equals("000000100")) //s4
                CBR = CBR.substring(0, 12) + "1" + CBR.substring(13, CBR.length()); 
        }

        String[] aux = CBR.split(" ");
        
        //aqui lidamos com a parte de jump da palavra de controle
        if(aux[3].equals("1")){
            
            //se o opcode for um j
            if(IR.opcode.equals("01101")){  
                CBR = CBR.substring(0, 40)+IR.P1;
            }

            //se for um BEQ ou um BNE
            else{    
                CBR = CBR.substring(0, 40)+ IR.P3;
                System.out.println("CBR: "+CBR);
            }

            
        }
    }

    //metodo que controla a execucao das micro-operacoes
    private void execucao(){

        /* 
         * o laco for eh utilizado para limitar a execucao de comandos
         * à quantidade de linhas de codigo armazenadas na memoria principal
         */
        
        for (int i = 0; i < MemoriaPrincipal.MemoriaPrincipalBinario.size() || Integer.parseInt(CPU.PC,2) < MemoriaPrincipal.MemoriaPrincipalBinario.size(); i = Integer.parseInt(CPU.PC,2)){
            try{
               Instrucao memo = (Instrucao) MemoriaPrincipal.MemoriaPrincipalBinario.get(i);
            
                CAR = 0;
                CBR = memoriaDeControle.get(CAR);
                executaLinhaCBR();
                codigoCompilado.add(CBR);
                microCompilado.add(microInstrucao.get(CAR));
                for (int h = 0; h < 3; h++){
					CAR++;
					CBR = memoriaDeControle.get(CAR);
                    executaLinhaCBR();
                    codigoCompilado.add(CBR);
                    microCompilado.add(microInstrucao.get(CAR));

                }

                IR.separaInstrucao();

                //Swtich que executa as instrucoes de acordo com seus ciclos de execucao
                String codigoRegistrador;
                switch (IR.opcode) {

                    case "00001":

                        CAR = demuxCAR.get("ADD");
                        CBR = memoriaDeControle.get(CAR);
                        codigoRegistrador = IR.P2;
                        administraCBR(codigoRegistrador,"o");   
                        executaLinhaCBR();
                        codigoCompilado.add(CBR);
                        microCompilado.add(microInstrucao.get(CAR));

                        CAR++;
                        CBR = memoriaDeControle.get(CAR);
                        codigoRegistrador = IR.P3;
                        administraCBR(codigoRegistrador,"o");
                        executaLinhaCBR();
                        codigoCompilado.add(CBR);
                        microCompilado.add(microInstrucao.get(CAR));

                        CAR++;
                        CBR = memoriaDeControle.get(CAR);                        
                        codigoRegistrador = IR.P1;
                        administraCBR(codigoRegistrador, "i");
                        executaLinhaCBR();
                        codigoCompilado.add(CBR);
                        microCompilado.add(microInstrucao.get(CAR));

                        break;

                    case "00010":

                        CAR = demuxCAR.get("ADDI");
                        CBR = memoriaDeControle.get(CAR);
                        codigoRegistrador = IR.P2;
                        administraCBR(codigoRegistrador,"o");
                        executaLinhaCBR();
                        codigoCompilado.add(CBR);
                        microCompilado.add(microInstrucao.get(CAR));

                        CAR++;
                        CBR = memoriaDeControle.get(CAR);
                        executaLinhaCBR();
                        codigoCompilado.add(CBR);
                        microCompilado.add(microInstrucao.get(CAR));

                        CAR++;
                        CBR = memoriaDeControle.get(CAR);
                        codigoRegistrador = IR.P1;
                        administraCBR(codigoRegistrador, "i");
                        executaLinhaCBR();
                        codigoCompilado.add(CBR);
                        microCompilado.add(microInstrucao.get(CAR));

                        break;

                    case "00011": 
              
                        CAR = demuxCAR.get("SUB");
                        CBR = memoriaDeControle.get(CAR);
                        codigoRegistrador = IR.P2;
                        administraCBR(codigoRegistrador,"o");
                        executaLinhaCBR();
                        codigoCompilado.add(CBR);
                        microCompilado.add(microInstrucao.get(CAR));

                        CAR++;
                        CBR = memoriaDeControle.get(CAR);
                        codigoRegistrador = IR.P3;
                        administraCBR(codigoRegistrador,"o");
                        executaLinhaCBR();
                        codigoCompilado.add(CBR);
                        microCompilado.add(microInstrucao.get(CAR));

                        CAR++;
                        CBR = memoriaDeControle.get(CAR);
                        codigoRegistrador = IR.P1;
                        administraCBR(codigoRegistrador, "i");
                        executaLinhaCBR();
                        codigoCompilado.add(CBR);
                        microCompilado.add(microInstrucao.get(CAR));

                        break;
                            
                    case "00100":                 

                        CAR = demuxCAR.get("SUBI");
                        CBR = memoriaDeControle.get(CAR);
                        codigoRegistrador = IR.P2;
                        administraCBR(codigoRegistrador,"o");
                        executaLinhaCBR(); 
                        codigoCompilado.add(CBR);
                        microCompilado.add(microInstrucao.get(CAR));

                        CAR++;
                        CBR = memoriaDeControle.get(CAR);
                        executaLinhaCBR();
                        codigoCompilado.add(CBR);
                        microCompilado.add(microInstrucao.get(CAR));

                        CAR++;
                        CBR = memoriaDeControle.get(CAR);
                        codigoRegistrador = IR.P1;
                        administraCBR(codigoRegistrador, "i");
                        executaLinhaCBR();
                        codigoCompilado.add(CBR);
                        microCompilado.add(microInstrucao.get(CAR));

                        break;

                    case "00101":                

                        CAR = demuxCAR.get("LI");
                        CBR = memoriaDeControle.get(CAR);
                        codigoRegistrador = IR.P1;
                        administraCBR(codigoRegistrador, "i");
                        executaLinhaCBR();
                        codigoCompilado.add(CBR);
                        microCompilado.add(microInstrucao.get(CAR));

                        break;

                    case "00110":
                        
                        CAR = demuxCAR.get("LW");
                        CBR = memoriaDeControle.get(CAR);
                        codigoRegistrador = IR.P3;
                        administraCBR(codigoRegistrador,"o");
                        executaLinhaCBR();
                        codigoCompilado.add(CBR);
                        microCompilado.add(microInstrucao.get(CAR));

                        CAR++;
                        CBR = memoriaDeControle.get(CAR);
                        executaLinhaCBR();
                        codigoCompilado.add(CBR);
                        microCompilado.add(microInstrucao.get(CAR));

                        CAR++;
                        CBR = memoriaDeControle.get(CAR);
                        executaLinhaCBR();
                        codigoCompilado.add(CBR);
                        microCompilado.add(microInstrucao.get(CAR));

                        CAR++;
                        CBR = memoriaDeControle.get(CAR);
                        executaLinhaCBR();
                        codigoCompilado.add(CBR);
                        microCompilado.add(microInstrucao.get(CAR));

                        CAR++;
                        CBR = memoriaDeControle.get(CAR);
                        executaLinhaCBR();
                        codigoCompilado.add(CBR);
                        microCompilado.add(microInstrucao.get(CAR));

                        CAR++;
                        CBR = memoriaDeControle.get(CAR);
                        codigoRegistrador = IR.P1;
                        administraCBR(codigoRegistrador, "i");
                        executaLinhaCBR();
                        codigoCompilado.add(CBR);
                        microCompilado.add(microInstrucao.get(CAR));

                        break;

                    case "00111":  
              
                        CAR = demuxCAR.get("SW");
                        CBR = memoriaDeControle.get(CAR);
                        codigoRegistrador = IR.P3;
                        administraCBR(codigoRegistrador, "o");
                        executaLinhaCBR();
                        codigoCompilado.add(CBR);
                        microCompilado.add(microInstrucao.get(CAR));

                        CAR++;
                        CBR = memoriaDeControle.get(CAR);
                        executaLinhaCBR();
                        codigoCompilado.add(CBR);
                        microCompilado.add(microInstrucao.get(CAR));

                        CAR++;
                        CBR = memoriaDeControle.get(CAR);
                        executaLinhaCBR();
                        codigoCompilado.add(CBR);
                        microCompilado.add(microInstrucao.get(CAR));

                        CAR++;
                        CBR = memoriaDeControle.get(CAR);
                        codigoRegistrador = IR.P1;
                        administraCBR(codigoRegistrador, "o");
                        executaLinhaCBR();
                        codigoCompilado.add(CBR);
                        microCompilado.add(microInstrucao.get(CAR));

                        CAR++;
                        CBR = memoriaDeControle.get(CAR);
                        executaLinhaCBR();
                        codigoCompilado.add(CBR);
                        microCompilado.add(microInstrucao.get(CAR));

                        break;
                        
                    case "01000":  
              
                        CAR = demuxCAR.get("MOVE");
                        CBR = memoriaDeControle.get(CAR);
                        codigoRegistrador = IR.P1;
                        administraCBR(codigoRegistrador, "i");
                        codigoRegistrador = IR.P2;
                        administraCBR(codigoRegistrador, "o");
                        executaLinhaCBR();
                        codigoCompilado.add(CBR);
                        microCompilado.add(microInstrucao.get(CAR));

                        break;

                    case "01010": 
               
                        CAR = demuxCAR.get("BEQrr");
                        CBR = memoriaDeControle.get(CAR);
                        codigoRegistrador = IR.P1;
                        administraCBR(codigoRegistrador, "o");
                        executaLinhaCBR();
                        codigoCompilado.add(CBR);
                        microCompilado.add(microInstrucao.get(CAR));

                        CAR++;
                        CBR = memoriaDeControle.get(CAR);
                        codigoRegistrador = IR.P2;
                        administraCBR(codigoRegistrador, "o");
                        executaLinhaCBR();
                        codigoCompilado.add(CBR);
                        microCompilado.add(microInstrucao.get(CAR));

                        
                        if(CPU.ULA.AC.equals("1")) {
                            CAR++;
                            CBR = memoriaDeControle.get(CAR);
                            administraCBR("", "");
                            executaLinhaCBR();
                            codigoCompilado.add(CBR);
                            microCompilado.add(microInstrucao.get(CAR));
                            
                        }

                        break;

                    case "01001": 
                
                        CAR = demuxCAR.get("BEQrc");
                        CBR = memoriaDeControle.get(CAR);
                        codigoRegistrador = IR.P1;
                        administraCBR(codigoRegistrador, "o");
                        executaLinhaCBR();
                        codigoCompilado.add(CBR);
                        microCompilado.add(microInstrucao.get(CAR));

                        CAR++;
                        CBR = memoriaDeControle.get(CAR);
                        executaLinhaCBR();
                        codigoCompilado.add(CBR);
                        microCompilado.add(microInstrucao.get(CAR));


                        if(CPU.ULA.AC.equals("1")) {
                            CAR++;
                            CBR = memoriaDeControle.get(CAR);
                            administraCBR("", "");
                            executaLinhaCBR();
                            codigoCompilado.add(CBR);
                            microCompilado.add(microInstrucao.get(CAR));
                            
                        }
                            
                        break;

                    case "01100":                 
                        CAR = demuxCAR.get("BNErr");
                        CBR = memoriaDeControle.get(CAR);
                        codigoRegistrador = IR.P1;
                        administraCBR(codigoRegistrador, "o");
                        executaLinhaCBR();
                        codigoCompilado.add(CBR);
                        microCompilado.add(microInstrucao.get(CAR));

                        CAR++;
                        CBR = memoriaDeControle.get(CAR);
                        codigoRegistrador = IR.P2;
                        administraCBR(codigoRegistrador, "o");
                        executaLinhaCBR();
                        codigoCompilado.add(CBR);
                        microCompilado.add(microInstrucao.get(CAR));

                       
                        if(CPU.ULA.AC.equals("1")) {
                            CAR++;
                            CBR = memoriaDeControle.get(CAR);
                            administraCBR("", "");
                            executaLinhaCBR();
                            codigoCompilado.add(CBR);
                            microCompilado.add(microInstrucao.get(CAR));
                        }

                        break;

                    case "01011":                 
                        CAR = demuxCAR.get("BNErc");
                        CBR = memoriaDeControle.get(CAR);
                        codigoRegistrador = IR.P1;
                        administraCBR(codigoRegistrador, "o");
                        executaLinhaCBR();
                        codigoCompilado.add(CBR);
                        microCompilado.add(microInstrucao.get(CAR));
                        CAR++;
                        CBR = memoriaDeControle.get(CAR);
                        executaLinhaCBR();
                        codigoCompilado.add(CBR);
                        microCompilado.add(microInstrucao.get(CAR));

                        
                        if(CPU.ULA.AC.equals("1")) {
                            CAR++;
                            CBR = memoriaDeControle.get(CAR);
                            administraCBR("", "");
                            executaLinhaCBR(); 
                            codigoCompilado.add(CBR);
                            microCompilado.add(microInstrucao.get(CAR));                          
                        }

                        break;

                    case "01101":                 
                        CAR = demuxCAR.get("J");
                        CBR = memoriaDeControle.get(CAR);
                        administraCBR("", "");
                        executaLinhaCBR();
                        codigoCompilado.add(CBR);
                        microCompilado.add(microInstrucao.get(CAR));

                        break;

                    case "01110":                 
                        CAR = demuxCAR.get("SLT");
                        CBR = memoriaDeControle.get(CAR);
                        codigoRegistrador = IR.P2;
                        administraCBR(codigoRegistrador, "o");
                        executaLinhaCBR();
                        codigoCompilado.add(CBR);
                        microCompilado.add(microInstrucao.get(CAR));

                        CAR++;
                        CBR = memoriaDeControle.get(CAR);
                        codigoRegistrador = IR.P3;
                        administraCBR(codigoRegistrador, "o");
                        executaLinhaCBR();
                        codigoCompilado.add(CBR);
                        microCompilado.add(microInstrucao.get(CAR));

                        CAR++;
                        CBR = memoriaDeControle.get(CAR);
                        codigoRegistrador = IR.P1;
                        administraCBR(codigoRegistrador, "i");
                        executaLinhaCBR();
                        codigoCompilado.add(CBR);
                        microCompilado.add(microInstrucao.get(CAR));
                        
                        break;

                    case "01111":                 

                        CAR = demuxCAR.get("LA");
                        CBR = memoriaDeControle.get(CAR);
                        executaLinhaCBR();
                        codigoCompilado.add(CBR);
                        microCompilado.add(microInstrucao.get(CAR));

                        CAR++;
                        CBR = memoriaDeControle.get(CAR);
                        executaLinhaCBR();
                        codigoCompilado.add(CBR);
                        microCompilado.add(microInstrucao.get(CAR));

                        CAR++;
                        CBR = memoriaDeControle.get(CAR);
                        executaLinhaCBR();
                        codigoCompilado.add(CBR);
                        microCompilado.add(microInstrucao.get(CAR));

                        CAR++;
                        CBR = memoriaDeControle.get(CAR);
                        codigoRegistrador = IR.P1;
                        administraCBR(codigoRegistrador, "i");
                        executaLinhaCBR();
                        codigoCompilado.add(CBR);
                        microCompilado.add(microInstrucao.get(CAR));
 
                        break;
                        
                    default:
                    
                        break;
                }
            } catch(Exception e){
              
            }
        }
    }

    /*
     * Metodo que percorre a linha armazenada no CBR e executa ela em sua totalidade
     */
    public void executaLinhaCBR(){

        CPU.barramentoInterno = administraPortasDeSaidaInterna(CPU.barramentoInterno, CBR);

        CPU.barramentoMemoria = administraPortasDeSaidaExterna(CPU.barramentoMemoria, CBR);

        administraPortasDeEntradaInterna(CPU.barramentoInterno, CBR);

        administraPortasDeEntradaExterna(CPU.barramentoMemoria, CBR);
        
        //Envio do sinal de controle (se houver) para a ULA
        String[] recebeCodigo = CBR.split(" ");
        ULA.executaSinalDeControle(recebeCodigo[1]);

        //Envio do sinal de controle (se houver) para a memoria principal
        MemoriaPrincipal.execSinaldeControle(recebeCodigo[2]);  
 
    }

    /*
     * Metodo que percorre a parte especifica das portas logicas de saida
     * na palavra de controle do CBR e atribui o valor daquelas indicadas ao barramento interno
     */
    private String administraPortasDeSaidaInterna(String barramento, String CBR){
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

    /*
     * Metodo que percorre a parte especifica das portas logicas de saida
     * na palavra de controle do CBR e atribui o valor daquelas indicadas ao barramento externo
     */
    private String administraPortasDeSaidaExterna(String barramento, String CBR){
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

    /*
     * Metodo que percorre a parte especifica das portas logicas de entrada
     * na palavra de controle do CBR e atribui o valor do barramento interno às portas indicadas
     */
    private void administraPortasDeEntradaInterna(String barramento, String CBR){
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

    /*
     * Metodo que percorre a parte especifica das portas logicas de entrada
     * na palavra de controle do CBR e atribui o valor do barramento externo às portas indicadas
     */
    private void administraPortasDeEntradaExterna(String barramento, String CBR){
        for(int i = 26; i < 29; i++){
            if(CBR.charAt(i-1) == '1'){
                switch(i){

                    case 26:{
                        CPU.MBR = barramento;
                    }break;

                    case 27:{
                        if(CBR.charAt(23) == '1'){  //Sabemos que os dados vem do MAR
                            MemoriaPrincipal.enderecoMar = Integer.toString(Integer.parseInt(barramento, 2));
                        }
                        else if(CBR.charAt(24) == '1'){ //Sabemos que os dados vem do MBR
                            MemoriaPrincipal.valorMBR = barramento;
                        }
                        
                    }break;

                }
            }
        }

    }

    /* Metodo que preenche a memoria de controle
     * com todos os ciclos de instrucao
     * que podem ser realizados pela cpu do trabalho
     * Preenche tambem um ArrayList auxiliar para a exibicao
     * das micro instrucoes no display
     */
    private void preencheMemoriaDeControle(){

        /* Referencia para leitura das palavras de controle
         *
         * quais portas devo abrir | o que a ULA tem que fazer | sinais de controle da memória | condição de pulo | endereço de pulo
         * 0000000000000000000000000000 0000 000 0 000000000
         */
        
        //------------------------ciclo de busca
        memoriaDeControle.add("0110000000000000000001000000 0000 000 0 000000000");   //t1 0
        memoriaDeControle.add("0000000000000000000000010010 0001 001 0 000000000");   //t2
        memoriaDeControle.add("1000000000000000000000100101 0000 000 0 000000000");   //t3
        memoriaDeControle.add("0000100000000100000000000000 0000 000 0 000000000");   //t4 
        
        //------------------------ciclos de execucao
        //ADD                  
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

        /* map que simula um DEMUX, armazenando o index
         * da primeira palavra de controle de cada ciclo
         */
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

        //ciclo de busca
        microInstrucao.add("t1: MAR,ULA <- PC");   //t1 0
        microInstrucao.add("t2: (memória) <- MAR, UC('read') | ULA <- UC('soma')");   //t2
        microInstrucao.add("t3: MBR <- (memória) | PC <- AC");   //t3
        microInstrucao.add("t4: IR <- MBR");   //t4 
        
        //------------------------ciclos de execucao
        //ADD
        microInstrucao.add("t1: X <- $regFonte1");   //t1 7
        microInstrucao.add("t2: ULA <- $regFonte2, UC('soma')");   //t2
        microInstrucao.add("t3: $regDestino <- AC");   //t3

        //ADDI              
        microInstrucao.add("t1: X <- $regFonte");   //t1 4
        microInstrucao.add("t2: ULA <- IR(P3), UC('soma')");   //t2
        microInstrucao.add("t3: $regDestino <- AC");   //t3 
        
        //SUB
        microInstrucao.add("t1: X <- $regFonte1");   //t1 7
        microInstrucao.add("t2: ULA <- $regFonte2, UC('subtracao')");   //t2
        microInstrucao.add("t3: $regDestino <- AC");   //t3

        //SUBI                
        microInstrucao.add("t1: X <- $regFonte");   //t1 4
        microInstrucao.add("t2: ULA <- IR(P3), UC('subtracao')");   //t2
        microInstrucao.add("t3: $regDestino <- AC");   //t3 
        
        //LI
        microInstrucao.add("t1: $regDestino <- IR(P2)");   //t1 16

        //LW
        microInstrucao.add("t1: X <- IR(P3)");   //t1 17
        microInstrucao.add("t2: ULA <- IR(P2) | ULA<- UC('soma')");   //t2
        microInstrucao.add("t3: MAR <- AC");   //t3 
        microInstrucao.add("t4: (memória) <- MAR | (memória) <- UC(‘read’)");   //t4
        microInstrucao.add("t5: MBR <- (memória)");   //t5
        microInstrucao.add("t6: $regDestino <- MBR");   //t6

        //SW
        microInstrucao.add("t1: X <- IR(P3)");   //t1 23
        microInstrucao.add("t2: ULA <- IR(P2) | ULA<- UC('soma')");   //t2
        microInstrucao.add("t3: MAR <- AC");   //t3 
        microInstrucao.add("t4: MBR <- $regFonte | (memória) <- MAR | (memória) <- UC(‘write’)");   //t4
        microInstrucao.add("t5: (memória) <- MBR");   //t5

        //MOVE
        microInstrucao.add("t1: $regDestino <- $regFonte");   //t1 28

        //BEQrr
        microInstrucao.add("t1: X <- $regFonte1");   //t1 29
        microInstrucao.add("t2: ULA <- $regFonte2 | ULA <- UC('comparação de igualdade')");   //t2
        microInstrucao.add("t3: PC <- IR(P3)");   //t3

        //BEQrc
        microInstrucao.add("t1: X <- $regFonte");   //t1 32
        microInstrucao.add("t2: ULA <- IR(P2) | ULA <- UC('comparação de igualdade')");   //t2
        microInstrucao.add("t3: PC <- IR(P3)");   //t3

        //BNErr
        microInstrucao.add("t1: X <- $regFonte1"); //t1 35
        microInstrucao.add("t2: ULA <- $regFonte2 | ULA <- UC('comparação de desigualdade')"); //t2
        microInstrucao.add("t3: PC <- IR(P3)"); //t3

        //BNErc
        microInstrucao.add("t1: X <- $regFonte1"); //t1 38
        microInstrucao.add("t2: ULA <- PC(P2) | ULA <- UC('comparação de desigualdade')"); //t2
        microInstrucao.add("t3: PC <- IR(P3)"); //t3

        //j
        microInstrucao.add("t1:PC <- IR(P1)"); //t1 41

        //slt
        microInstrucao.add("t1:X <- $regFonte1");   //t1 42
        microInstrucao.add("t2: ULA <- $regFonte2  | ULA <- UC('comparação de menor que')");   //t2
        microInstrucao.add("t3: $regDestino <- AC");   //t3

        //la
        microInstrucao.add("t1: MAR <- IR(P2)");   //t1 45
        microInstrucao.add("t2: (memoria) <- MAR, UC('comando de devolver endereco')");   //t2
        microInstrucao.add("t3: MBR <- (memoria)");   //t3
        microInstrucao.add("t4: $regDestino <- MBR");   //t4

    }

}
