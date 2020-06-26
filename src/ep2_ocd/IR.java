package ep2_ocd;

public class IR {
    private int opcode;
    private int P1;
    private int P2;
    private int P3;

    public IR(int opcode, int parametro1, int parametro2, int parametro3) {
        this.opcode = opcode;
        this.P1 = parametro1;
        this.P2 = parametro2;
        this.P3 = parametro3;
    }
}
