package peçasDeChadres;

import campoDeBatalha.Posicao;
import campoDeBatalha.Tabuleiro;
import chadres.Cores;
import chadres.MatematicaDoChadres;
import chadres.PeçaDeChadres;

public class Rei extends PeçaDeChadres {

	private MatematicaDoChadres matematicaDoChadres;

	public Rei(Tabuleiro tabuleiro, Cores cores, MatematicaDoChadres matematicaDoChadres) {
		super(tabuleiro, cores);
		this.matematicaDoChadres = matematicaDoChadres;
	}

	@Override
	public String toString() {
		return "R";
	}

	private boolean podeMover(Posicao posicao) {
		PeçaDeChadres p = (PeçaDeChadres) getTabuleiro().peça(posicao);
		return p == null || p.getCores() != getCores();
	}

	private boolean TestRook(Posicao posicao) {
		PeçaDeChadres p = (PeçaDeChadres) getTabuleiro().peça(posicao);
		return p != null && p instanceof Torre && p.getCores() == getCores() && p.getContMovimento() == 0;
	}

	@Override
	public boolean[][] movimentosPossiveis() {
		boolean[][] mat = new boolean[getTabuleiro().getLinhasDoTabuleiro()][getTabuleiro().getColunasDoTabuleiro()];

		Posicao p = new Posicao(0, 0);
		// acima
		p.setValores(posicao.getLinhasDaPeça() - 1, posicao.getColunasDaPeça());
		if (getTabuleiro().existeHaPosicao(p) && podeMover(p)) {
			mat[p.getLinhasDaPeça()][p.getColunasDaPeça()] = true;
		}
		// abaixo
		p.setValores(posicao.getLinhasDaPeça() + 1, posicao.getColunasDaPeça());
		if (getTabuleiro().existeHaPosicao(p) && podeMover(p)) {
			mat[p.getLinhasDaPeça()][p.getColunasDaPeça()] = true;
		}
		// esquerda
		p.setValores(posicao.getLinhasDaPeça(), posicao.getColunasDaPeça() - 1);
		if (getTabuleiro().existeHaPosicao(p) && podeMover(p)) {
			mat[p.getLinhasDaPeça()][p.getColunasDaPeça()] = true;
		}
		// direita
		p.setValores(posicao.getLinhasDaPeça(), posicao.getColunasDaPeça() + 1);
		if (getTabuleiro().existeHaPosicao(p) && podeMover(p)) {
			mat[p.getLinhasDaPeça()][p.getColunasDaPeça()] = true;
		}
		// noroeste
		p.setValores(posicao.getLinhasDaPeça() - 1, posicao.getColunasDaPeça() - 1);
		if (getTabuleiro().existeHaPosicao(p) && podeMover(p)) {
			mat[p.getLinhasDaPeça()][p.getColunasDaPeça()] = true;
		}
		// nordeste
		p.setValores(posicao.getLinhasDaPeça() - 1, posicao.getColunasDaPeça() + 1);
		if (getTabuleiro().existeHaPosicao(p) && podeMover(p)) {
			mat[p.getLinhasDaPeça()][p.getColunasDaPeça()] = true;
		}
		// sudoeste
		p.setValores(posicao.getLinhasDaPeça() + 1, posicao.getColunasDaPeça() - 1);
		if (getTabuleiro().existeHaPosicao(p) && podeMover(p)) {
			mat[p.getLinhasDaPeça()][p.getColunasDaPeça()] = true;
		}
		// suldeste
		p.setValores(posicao.getLinhasDaPeça() + 1, posicao.getColunasDaPeça() + 1);
		if (getTabuleiro().existeHaPosicao(p) && podeMover(p)) {
			mat[p.getLinhasDaPeça()][p.getColunasDaPeça()] = true;
		}
		// #specialmove castling
		if (getContMovimento() == 0 && !matematicaDoChadres.getCheck()) {
			// #specialmove castling kingside rook
			Posicao posT1 = new Posicao(posicao.getLinhasDaPeça(), posicao.getColunasDaPeça() + 3);
			if (TestRook(posT1)) {
				Posicao p1 = new Posicao(posicao.getLinhasDaPeça(), posicao.getColunasDaPeça() + 1);
				Posicao p2 = new Posicao(posicao.getLinhasDaPeça(), posicao.getColunasDaPeça() + 2);
				if (getTabuleiro().peça(p1) == null && getTabuleiro().peça(p2) == null) {
					mat[posicao.getLinhasDaPeça()][posicao.getColunasDaPeça() + 2] = true;
				}
			}
			// #specialmove castling queenside rook
			Posicao posT2 = new Posicao(posicao.getLinhasDaPeça(), posicao.getColunasDaPeça() - 4);
			if (TestRook(posT2)) {
				Posicao p1 = new Posicao(posicao.getLinhasDaPeça(), posicao.getColunasDaPeça() - 1);
				Posicao p2 = new Posicao(posicao.getLinhasDaPeça(), posicao.getColunasDaPeça() - 2);
				Posicao p3 = new Posicao(posicao.getLinhasDaPeça(), posicao.getColunasDaPeça() - 3);
				if (getTabuleiro().peça(p1) == null && getTabuleiro().peça(p2) == null
						&& getTabuleiro().peça(p3) == null) {
					mat[posicao.getLinhasDaPeça()][posicao.getColunasDaPeça() - 2] = true;
				}
			}
		}

		return mat;
	}

}
