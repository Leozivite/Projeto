package campoDeBatalha;

public abstract class Peça {
	protected Posicao posicao;
	private Tabuleiro tabuleiro;

	public Peça(Tabuleiro tabuleiro) {

		this.tabuleiro = tabuleiro;
		posicao = null;
	}
    public Tabuleiro getTabuleiro() {
    	return tabuleiro;
    }
	public abstract boolean[][] movimentosPossiveis();

	public boolean movimentosPossivel(Posicao posicao) {
		return movimentosPossiveis()[posicao.getLinhasDaPeça()][posicao.getColunasDaPeça()];
	}

	public boolean esseMovimentoEPossivel() {
		boolean[][] mat = movimentosPossiveis();
		for (int i = 0; i < mat.length; i++) {
			for (int j = 0; j < mat.length; j++) {
				if (mat[i][j]) {
					return true;
				}
			}
		}
	return false;
	}
}
