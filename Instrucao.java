class Instrucao {
    public String opcode;
    public String parametro1;
    public String parametro2;
    public String parametro3;
    
    
    public Instrucao(){}
    
    public Instrucao(String opcode, String parametro1, String parametro2, String parametro3) {
        this.opcode = opcode;
        this.parametro1 = parametro1;
        this.parametro2 = parametro2;
        this.parametro3 = parametro3;
    }
    
    @Override
	public String toString() {
		return opcode + " " + parametro1 + " " + parametro2 + " " + parametro3;
	}

}