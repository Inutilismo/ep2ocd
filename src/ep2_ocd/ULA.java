package ep2_ocd;

class ULA {
	String X;
	String AC;
	
	private void incremento (String reg) {
		AC = Integer.toBinaryString(Integer.parseInt(reg, 2) + 1);
	}
	
	private void add (String reg2) {
		AC = Integer.toBinaryString(Integer.parseInt(X, 2) + Integer.parseInt(reg2, 2));
	}
	
	private void addi (String constante) {
		AC = Integer.toBinaryString(Integer.parseInt(X, 2) + Integer.parseInt(constante, 2));
	}	
	
	private void sub (String reg2) {
		AC = Integer.toBinaryString(Integer.parseInt(X, 2) - Integer.parseInt(reg2, 2));
	}	
	
	private void subi (String constante) {
		AC = Integer.toBinaryString(Integer.parseInt(X, 2) - Integer.parseInt(constante, 2));
	}	
	
	private void BEQrr (String reg2) {
		if (Math.abs(Integer.parseInt(X, 2)) - Math.abs(Integer.parseInt(reg2, 2)) == 0) AC = "1";
		else AC = "0";
	}	
	
	private void BEQrc (String constante) {
		if (Math.abs(Integer.parseInt(X, 2)) - Math.abs(Integer.parseInt(constante, 2)) == 0) AC = "1";
		else AC = "0";
	}		
	
	private void BNErr (String reg2) {
		if (Math.abs(Integer.parseInt(X, 2)) - Math.abs(Integer.parseInt(reg2, 2)) != 0) AC = "1";
		else AC = "0";
	}	
	
	private void BNErc (String constante) {
		if (Math.abs(Integer.parseInt(X, 2)) - Math.abs(Integer.parseInt(constante, 2)) != 0) AC = "1";
		else AC = "0";
	}	
	
	private void SLT (String reg2) {
		if (Integer.parseInt(X, 2) - Integer.parseInt(reg2, 2) < 0) AC = "1";
		else AC = "0";
	}		
	
}
