package chadres;

import campoDeBatalha.Posicao;

public class PosicaoDoChadres {
	private char colunabc;
	private int linha123;

	public PosicaoDoChadres(char colunabc, int linha123) {
		if (colunabc < 'a' || colunabc > 'h' || linha123 < 1 || linha123 > 8) {
			throw new ExcecoesDoChadres("Erro instanciando posicao do chadres.valores validos de a1 ate h8");
		}
		this.colunabc = colunabc;
		this.linha123 = linha123;
	}

	protected Posicao paraHaPosicao() {
		return new Posicao(8 - linha123, colunabc - 'a');
	}

	protected static PosicaoDoChadres daPosicaoPara(Posicao posicao) {
		return new PosicaoDoChadres((char) ('a' + posicao.getColunasDaPeça()), 8 - posicao.getLinhasDaPeça());
	}

	@Override
	public String toString() {
		return "" +colunabc + linha123  ;
	}
	
}
