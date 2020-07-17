public class IR {
    public static String opcode;
    public static String P1;
    public static String P2;
    public static String P3;

    public IR(String opcode, String parametro1, String parametro2, String parametro3) {
        IR.opcode = opcode;
        IR.P1 = parametro1;
        IR.P2 = parametro2;
        IR.P3 = parametro3;
    }

    //metodo que separa a linha de codigo recebida do barramento durante o ciclo de busca
    public static void separaInstrucao(){
        String[] auxSplit = opcode.split(" ");
        IR.opcode = auxSplit[0];
        IR.P1 = auxSplit[1];
        IR.P2 = auxSplit[2];
        IR.P3 = auxSplit[3];
    }
}
