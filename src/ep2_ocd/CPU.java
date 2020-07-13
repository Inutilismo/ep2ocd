package ep2_ocd;

public class CPU {
    public static String PC;
    public static String MAR;
    public static String MBR;
    public static String s1,s2,s3,s4;
    public static String barramentoInterno;
    public static String barramentoMemoria;
    
    public static IR IR;
    public static ULA ULA;
    public static UC UC;

    //metodo que instancia a UC para dar inicio a execucao das micro operacoes
    public static void inicio() { 
        UC = new UC();
    }

//    @Override
    public static String toSTR(){
        StringBuilder sb = new StringBuilder();
        sb.append("\n");
        sb.append("     PC: " + PC + "\n");
        sb.append("     MAR: " + MAR + "\n");
        sb.append("     MBR: " + MBR + "\n");
        sb.append("     S1: " + s1 + "\n");
        sb.append("     S2: " + s2 + "\n");
        sb.append("     S3: " + s3 + "\n");
        sb.append("     S4: " + s4 + "\n");
        sb.append("     Barramento Interno: " + barramentoInterno + "\n");
        sb.append("     Barramento da Memoria: " + barramentoMemoria + "\n");
        sb.append(
            "     IR(opcode): " + IR.opcode + "\n" +
            "     IR(P1): " + IR.P1  + "\n" +
            "     IR(P2): " + IR.P2 +"\n" + 
            "     IR(P3): " + IR.P3 + "\n");
        sb.append("     ULA: " + ULA.valor + "\n" + "     ULA(X): " + ULA.X + "\n" + "     ULA(AC): " + ULA.AC + "\n");
        sb.append("     UC(CAR): " + UC.CAR + "\n" + "     UC(CBR):" + "\n");
        sb.append("\n");
        
        return sb.toString();
    }
}
