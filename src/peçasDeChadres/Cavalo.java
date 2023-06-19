package peçasDeChadres;

import campoDeBatalha.Posicao;
import campoDeBatalha.Tabuleiro;
import chadres.Cores;
import chadres.PeçaDeChadres;

public class Cavalo extends PeçaDeChadres {

	public Cavalo(Tabuleiro tabuleiro, Cores cores) {
		super(tabuleiro, cores);
	}

	private boolean PodeMover(Posicao posicao) {
		PeçaDeChadres p = (PeçaDeChadres) getTabuleiro().peça(posicao);
		return p == null || p.getCores() != getCores();
	}

	@Override
	public boolean[][] movimentosPossiveis() {
		boolean[][] mat = new boolean[getTabuleiro().getLinhasDoTabuleiro()][getTabuleiro().getColunasDoTabuleiro()];

		Posicao p = new Posicao(0, 0);

		p.setValores(posicao.getLinhasDaPeça() - 1, posicao.getColunasDaPeça() - 2);
		if (getTabuleiro().existeHaPosicao(p) && PodeMover(p)) {
			mat[p.getLinhasDaPeça()][p.getColunasDaPeça()] = true;
		}

		p.setValores(posicao.getLinhasDaPeça() - 2, posicao.getColunasDaPeça() - 1);
		if (getTabuleiro().existeHaPosicao(p) && PodeMover(p)) {
			mat[p.getLinhasDaPeça()][p.getColunasDaPeça()] = true;
		}

		p.setValores(posicao.getLinhasDaPeça() - 2, posicao.getColunasDaPeça() + 1);
		if (getTabuleiro().existeHaPosicao(p) && PodeMover(p)) {
			mat[p.getLinhasDaPeça()][p.getColunasDaPeça()] = true;
		}

		p.setValores(posicao.getLinhasDaPeça() - 1, posicao.getColunasDaPeça() + 2);
		if (getTabuleiro().existeHaPosicao(p) && PodeMover(p)) {
			mat[p.getLinhasDaPeça()][p.getColunasDaPeça()] = true;
		}

		p.setValores(posicao.getLinhasDaPeça() + 1, posicao.getColunasDaPeça() + 2);
		if (getTabuleiro().existeHaPosicao(p) && PodeMover(p)) {
			mat[p.getLinhasDaPeça()][p.getColunasDaPeça()] = true;
		}

		p.setValores(posicao.getLinhasDaPeça() + 2, posicao.getColunasDaPeça() + 1);
		if (getTabuleiro().existeHaPosicao(p) && PodeMover(p)) {
			mat[p.getLinhasDaPeça()][p.getColunasDaPeça()] = true;
		}

		p.setValores(posicao.getLinhasDaPeça() + 2, posicao.getColunasDaPeça() - 1);
		if (getTabuleiro().existeHaPosicao(p) && PodeMover(p)) {
			mat[p.getLinhasDaPeça()][p.getColunasDaPeça()] = true;
		}

		p.setValores(posicao.getLinhasDaPeça() + 1, posicao.getColunasDaPeça() - 2);
		if (getTabuleiro().existeHaPosicao(p) && PodeMover(p)) {
			mat[p.getLinhasDaPeça()][p.getColunasDaPeça()] = true;
		}

		return mat;
	}

	@Override
	public String toString() {
		return "C";
	}
}
