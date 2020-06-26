package ep2_ocd;

class Instrucao {
    private int opcode;
    private int parametro1;
    private int parametro2;
    private int parametro3;

    public Instrucao(int opcode, int parametro1, int parametro2, int parametro3) {
        this.opcode = opcode;
        this.parametro1 = parametro1;
        this.parametro2 = parametro2;
        this.parametro3 = parametro3;
    }
    
}
