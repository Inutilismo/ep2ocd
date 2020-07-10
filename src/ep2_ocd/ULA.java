package ep2_ocd;

class ULA {
	static String X = "";
	static String valor = "";
	static String AC = "";

	public static void executaSinalDeControle(String codigoOperacao){
		System.out.println(codigoOperacao);
		switch (codigoOperacao) {
			case "0000":
				//nao faz nada
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

	private static void incremento () {
		System.out.println("valor em binario: "+ valor);
		System.out.println("valor em decimal: "+Integer.parseInt(valor, 2));
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

