package ep2_ocd;

public class CPU {
    public static String PC; //converter para int sempre que for usar
    public static String MAR; //converter para int sempre que for usar
    public static String MBR;
    public static String s1,s2,s3,s4;
    public static String barramentoInterno;
    public static String barramentoMemoria;
    
    public IR IR;
    public static ULA ULA;
    public static UC UC;

    public static void inicio() {
        UC = new UC();
    }

    /*public CPU(){
        UC = new UC();
    }*/

    
}
