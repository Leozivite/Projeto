package peçasDeChadres;

import campoDeBatalha.Posicao;
import campoDeBatalha.Tabuleiro;
import chadres.Cores;
import chadres.PeçaDeChadres;

public class Torre extends PeçaDeChadres {

	public Torre(Tabuleiro tabuleiro, Cores cores) {
		super(tabuleiro, cores);

	}

	@Override
	public String toString() {
		return "T";
	}

	@Override
	public boolean[][] movimentosPossiveis() {
		boolean[][] mat = new boolean[getTabuleiro().getLinhasDoTabuleiro()][getTabuleiro().getColunasDoTabuleiro()];
		
		Posicao p = new Posicao(0, 0);
		
		// para cima
		p.setValores(posicao.getLinhasDaPeça() - 1, posicao.getColunasDaPeça());
		while (getTabuleiro().existeHaPosicao(p) && !getTabuleiro().essaPeçaExiste(p)) {
			mat[p.getLinhasDaPeça()][p.getColunasDaPeça()] = true;
			p.setLinhasDaPeça(p.getLinhasDaPeça() - 1);
		}
		if (getTabuleiro().existeHaPosicao(p) && essaPeçaEDoOponente(p)) {
			mat[p.getLinhasDaPeça()][p.getColunasDaPeça()] = true;
		}
		// para a esquerda
		p.setValores(posicao.getLinhasDaPeça(),posicao.getColunasDaPeça() - 1);
		while (getTabuleiro().existeHaPosicao(p) && !getTabuleiro().essaPeçaExiste(p)) {
			mat[p.getLinhasDaPeça()][p.getColunasDaPeça()] = true;
			p.setColunasDaPeça(p.getColunasDaPeça() - 1);
		}
		if (getTabuleiro().existeHaPosicao(p) && essaPeçaEDoOponente(p)) {
			mat[p.getLinhasDaPeça()][p.getColunasDaPeça()] = true;
		}
		// para a direita
		p.setValores(posicao.getLinhasDaPeça(),posicao.getColunasDaPeça() + 1);
		while (getTabuleiro().existeHaPosicao(p) && !getTabuleiro().essaPeçaExiste(p)) {
			mat[p.getLinhasDaPeça()][p.getColunasDaPeça()] = true;
			p.setColunasDaPeça(p.getColunasDaPeça() + 1);
		}
		if (getTabuleiro().existeHaPosicao(p) && essaPeçaEDoOponente(p)) {
			mat[p.getLinhasDaPeça()][p.getColunasDaPeça()] = true;
		}
		// para baixo
		p.setValores(posicao.getLinhasDaPeça() + 1, posicao.getColunasDaPeça());
		while (getTabuleiro().existeHaPosicao(p) && !getTabuleiro().essaPeçaExiste(p)) {
			mat[p.getLinhasDaPeça()][p.getColunasDaPeça()] = true;
			p.setLinhasDaPeça(p.getLinhasDaPeça() + 1);
		}
		if (getTabuleiro().existeHaPosicao(p) && essaPeçaEDoOponente(p)) {
			mat[p.getLinhasDaPeça()][p.getColunasDaPeça()] = true;
		}
		return mat;
	}

}
