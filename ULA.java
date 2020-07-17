class ULA {

	//Representa o registrador X do diagrama
	static String X = "";
	//Armazena os valores atribuidos diretamente para a ULA
	static String valor = "";
	static String AC = "";

	/* Metodo que simula o funcionamento de um
	 * DEMUX das operacoes da ula baseado no 
	 * sinal de controle recebido pelo barramento
	 */
	public static void executaSinalDeControle(String codigoOperacao){
		switch (codigoOperacao) {
			case "0000":
				
				break;

			case "0001":
				incremento();
				break;

			case "0010":
				add();
				break;

			case "0011":
				addi();
				break;

			case "0100":
				sub();
				break;

			case "0101":
				subi();
				break;

			case "0110":
				BEQrr();
				break;

			case "0111":
				BEQrc();
				break;

			case "1000":
				BNErr();
				break;

			case "1001":
				BNErc();
				break;

			case "1010":
				SLT();
				break;		
		}
	}

	/*
	 * Os metodos abaixo realizam as operacoes que a ULA
	 * disponibiliza e armazenam o resultado na variavel AC
	 */

	private static void incremento () {

		AC = Integer.toBinaryString(Integer.parseInt(valor, 2) + 1);
	}
	
	private static void add () {
		AC = Integer.toBinaryString(Integer.parseInt(X, 2) + Integer.parseInt(valor, 2));
	}
	
	private static void addi () {
		AC = Integer.toBinaryString(Integer.parseInt(X, 2) + Integer.parseInt(valor, 2));
	}	
	
	private static void sub () {
		AC = Integer.toBinaryString(Integer.parseInt(X, 2) - Integer.parseInt(valor, 2));
	}	
	
	private static void subi () {
		AC = Integer.toBinaryString(Integer.parseInt(X, 2) - Integer.parseInt(valor, 2));
	}	
	
	private static void BEQrr () {
		if (Math.abs(Integer.parseInt(X, 2)) - Math.abs(Integer.parseInt(valor, 2)) == 0) AC = "1";
		else AC = "0";
	}	
	
	private static void BEQrc () {
		if (Math.abs(Integer.parseInt(X, 2)) - Math.abs(Integer.parseInt(valor, 2)) == 0) AC = "1";
		else AC = "0";
	}		
	
	private static void BNErr () {
		if (Math.abs(Integer.parseInt(X, 2)) - Math.abs(Integer.parseInt(valor, 2)) != 0) AC = "1";
		else AC = "0";
	}	
	
	private static void BNErc () {
		if (Math.abs(Integer.parseInt(X, 2)) - Math.abs(Integer.parseInt(valor, 2)) != 0) AC = "1";
		else AC = "0";
	}	
	
	private static void SLT () {
		if (Integer.parseInt(X, 2) - Integer.parseInt(valor, 2) < 0) AC = "1";
		else AC = "0";
	}		
	
}

