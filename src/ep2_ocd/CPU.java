package ep2_ocd;

public class CPU {
    public static String PC;
    public static String MAR;
    public static String MBR;
    public static String s1,s2,s3,s4;
    public static String barramentoInterno;
    public static String barramentoMemoria;
    
    public IR IR;
    public static ULA ULA;
    public static UC UC;

    //metodo que instancia a UC para dar inicio a execucao das micro operacoes
    public static void inicio() {
        UC = new UC();
    }
}
