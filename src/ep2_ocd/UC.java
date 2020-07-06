package ep2_ocd;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.HashMap;

class UC {
    private List<String> memoriaDeControle = new ArrayList<>();
    private Object CAR;     //coloquei como object porque ainda não sei que tipo específico vai ter 
    private Object CBR;
    private Map<String,Integer> linhaMemoriaDeControle = new HashMap<>();
    
    public UC (){
        preencheMemoriaDeControle();
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

    linhaMemoriaDeControle.put("CicloDeBusca", 0);
    linhaMemoriaDeControle.put("ADD", 4);
    linhaMemoriaDeControle.put("ADDI", 7);
    linhaMemoriaDeControle.put("SUB", 10);
    linhaMemoriaDeControle.put("SUBI", 13);
    linhaMemoriaDeControle.put("LI", 16);
    linhaMemoriaDeControle.put("LW", 17);
    linhaMemoriaDeControle.put("SW", 21);
    linhaMemoriaDeControle.put("MOVE", 24);
    linhaMemoriaDeControle.put("BEQrr", 25);
    linhaMemoriaDeControle.put("BEQrc", 31);
    linhaMemoriaDeControle.put("BNErr", 37);
    linhaMemoriaDeControle.put("BNErc", 43);
    linhaMemoriaDeControle.put("J", 49);
    linhaMemoriaDeControle.put("SLT", 53);
    linhaMemoriaDeControle.put("LA", 56);

    }

}
