package ep2_ocd;

class ULA {
	String X;
	String valor;
	String AC;

	public void executaSinalDeControle(String codigoOperacao){
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

	private void incremento () {
		AC = Integer.toBinaryString(Integer.parseInt(valor, 2) + 1);
	}
	
	private void add () {
		AC = Integer.toBinaryString(Integer.parseInt(X, 2) + Integer.parseInt(valor, 2));
	}
	
	private void addi () {
		AC = Integer.toBinaryString(Integer.parseInt(X, 2) + Integer.parseInt(valor, 2));
	}	
	
	private void sub () {
		AC = Integer.toBinaryString(Integer.parseInt(X, 2) - Integer.parseInt(valor, 2));
	}	
	
	private void subi () {
		AC = Integer.toBinaryString(Integer.parseInt(X, 2) - Integer.parseInt(valor, 2));
	}	
	
	private void BEQrr () {
		if (Math.abs(Integer.parseInt(X, 2)) - Math.abs(Integer.parseInt(valor, 2)) == 0) AC = "1";
		else AC = "0";
	}	
	
	private void BEQrc () {
		if (Math.abs(Integer.parseInt(X, 2)) - Math.abs(Integer.parseInt(valor, 2)) == 0) AC = "1";
		else AC = "0";
	}		
	
	private void BNErr () {
		if (Math.abs(Integer.parseInt(X, 2)) - Math.abs(Integer.parseInt(valor, 2)) != 0) AC = "1";
		else AC = "0";
	}	
	
	private void BNErc () {
		if (Math.abs(Integer.parseInt(X, 2)) - Math.abs(Integer.parseInt(valor, 2)) != 0) AC = "1";
		else AC = "0";
	}	
	
	private void SLT () {
		if (Integer.parseInt(X, 2) - Integer.parseInt(valor, 2) < 0) AC = "1";
		else AC = "0";
	}		
	
}
