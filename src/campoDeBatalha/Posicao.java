package campoDeBatalha;

public class Posicao {
	
	private int linhasDaPeça;
	private int colunasDaPeça;

	public Posicao(int linhasDaPeça, int colunasDaPeça) {
		this.linhasDaPeça = linhasDaPeça;
		this.colunasDaPeça = colunasDaPeça;
	}

	public int getLinhasDaPeça() {
		return linhasDaPeça;
	}

	public int getColunasDaPeça() {
		return colunasDaPeça;
	}

	public void setValores(int linhasDaPeça, int colunasDaPeça) {
		this.linhasDaPeça = linhasDaPeça;
		this.colunasDaPeça = colunasDaPeça;
	}

	public void setLinhasDaPeça(int linhasDaPeça) {
		this.linhasDaPeça = linhasDaPeça;
	}

	public void setColunasDaPeça(int colunasDaPeça) {
		this.colunasDaPeça = colunasDaPeça;
	}

	@Override
	public String toString() {
		return linhasDaPeça + ", " + colunasDaPeça;
	}
}
