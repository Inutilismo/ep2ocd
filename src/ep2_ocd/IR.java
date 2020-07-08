package ep2_ocd;

public class IR {
    public static String opcode;
    public static String P1;
    public static String P2;
    public static String P3;

    public IR(String opcode, String parametro1, String parametro2, String parametro3) {
        this.opcode = opcode;
        this.P1 = parametro1;
        this.P2 = parametro2;
        this.P3 = parametro3;
    }
}
