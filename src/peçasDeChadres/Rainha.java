package peçasDeChadres;

import campoDeBatalha.Posicao;
import campoDeBatalha.Tabuleiro;
import chadres.Cores;
import chadres.PeçaDeChadres;

public class Rainha extends PeçaDeChadres {

	public Rainha(Tabuleiro tabuleiro, Cores cores) {
		super(tabuleiro, cores);
		// TODO Auto-generated constructor stub
	}
	@Override
	public boolean[][] movimentosPossiveis() {
		boolean[][] mat = new boolean[getTabuleiro().getLinhasDoTabuleiro()][getTabuleiro().getColunasDoTabuleiro()];
		
		Posicao p = new Posicao(0, 0);
		
		// above
		p.setValores(posicao.getLinhasDaPeça() - 1, posicao.getColunasDaPeça());
		while (getTabuleiro().existeHaPosicao(p) && !getTabuleiro().essaPeçaExiste(p)) {
			mat[p.getLinhasDaPeça()][p.getColunasDaPeça()] = true;
			p.setLinhasDaPeça(p.getLinhasDaPeça()-1);
		}
		if (getTabuleiro().existeHaPosicao(p) && essaPeçaEDoOponente(p)) {
			mat[p.getLinhasDaPeça()][p.getColunasDaPeça()] = true;
		}
		
		// left
		p.setValores(posicao.getLinhasDaPeça(), posicao.getColunasDaPeça() - 1);
		while (getTabuleiro().existeHaPosicao(p) && !getTabuleiro().essaPeçaExiste(p)) {
			mat[p.getLinhasDaPeça()][p.getColunasDaPeça()] = true;
			p.setColunasDaPeça(p.getColunasDaPeça() - 1);
		}
		if (getTabuleiro().existeHaPosicao(p) && essaPeçaEDoOponente(p)) {
			mat[p.getLinhasDaPeça()][p.getColunasDaPeça()] = true;
		}
		
		// right
		p.setValores(posicao.getLinhasDaPeça(), posicao.getColunasDaPeça() + 1);
		while (getTabuleiro().existeHaPosicao(p) && !getTabuleiro().essaPeçaExiste(p)) {
			mat[p.getLinhasDaPeça()][p.getColunasDaPeça()] = true;
			p.setColunasDaPeça(p.getColunasDaPeça() + 1);
		}
		if (getTabuleiro().existeHaPosicao(p) && essaPeçaEDoOponente(p)) {
			mat[p.getLinhasDaPeça()][p.getColunasDaPeça()] = true;
		}
		
		// below
		p.setValores(posicao.getLinhasDaPeça() + 1, posicao.getColunasDaPeça());
		while (getTabuleiro().existeHaPosicao(p) && !getTabuleiro().essaPeçaExiste(p)) {
			mat[p.getLinhasDaPeça()][p.getColunasDaPeça()] = true;
			p.setLinhasDaPeça(p.getLinhasDaPeça() + 1);
		}
		if (getTabuleiro().existeHaPosicao(p) && essaPeçaEDoOponente(p)) {
			mat[p.getLinhasDaPeça()][p.getColunasDaPeça()] = true;
		}
		
		// nw
		p.setValores(posicao.getLinhasDaPeça() - 1, posicao.getColunasDaPeça() - 1);
		while (getTabuleiro().existeHaPosicao(p) && !getTabuleiro().essaPeçaExiste(p)) {
			mat[p.getLinhasDaPeça()][p.getColunasDaPeça()] = true;
			p.setValores(p.getLinhasDaPeça() - 1, p.getColunasDaPeça() - 1);
		}
		if (getTabuleiro().existeHaPosicao(p) && essaPeçaEDoOponente(p)) {
			mat[p.getLinhasDaPeça()][p.getColunasDaPeça()] = true;
		}
		
		// ne
		p.setValores(posicao.getLinhasDaPeça() - 1, posicao.getColunasDaPeça() + 1);
		while (getTabuleiro().existeHaPosicao(p) && !getTabuleiro().essaPeçaExiste(p)) {
			mat[p.getLinhasDaPeça()][p.getColunasDaPeça()] = true;
			p.setValores(p.getLinhasDaPeça() - 1, p.getColunasDaPeça() + 1);
		}
		if (getTabuleiro().existeHaPosicao(p) && essaPeçaEDoOponente(p)) {
			mat[p.getLinhasDaPeça()][p.getColunasDaPeça()] = true;
		}
		
		// se
		p.setValores(posicao.getLinhasDaPeça() + 1, posicao.getColunasDaPeça() + 1);
		while (getTabuleiro().existeHaPosicao(p) && !getTabuleiro().essaPeçaExiste(p)) {
			mat[p.getLinhasDaPeça()][p.getColunasDaPeça()] = true;
			p.setValores(p.getLinhasDaPeça() + 1, p.getColunasDaPeça() + 1);
		}
		if (getTabuleiro().existeHaPosicao(p) && essaPeçaEDoOponente(p)) {
			mat[p.getLinhasDaPeça()][p.getColunasDaPeça()] = true;
		}
		
		// sw
		p.setValores(posicao.getLinhasDaPeça() + 1, posicao.getColunasDaPeça() - 1);
		while (getTabuleiro().existeHaPosicao(p) && !getTabuleiro().essaPeçaExiste(p)) {
			mat[p.getLinhasDaPeça()][p.getColunasDaPeça()] = true;
			p.setValores(p.getLinhasDaPeça() + 1, p.getColunasDaPeça() - 1);
		}
		if (getTabuleiro().existeHaPosicao(p) && essaPeçaEDoOponente(p)) {
			mat[p.getLinhasDaPeça()][p.getColunasDaPeça()] = true;
		}
		
		return mat;
	}
	
	@Override
	public String toString() {
		return "r";
	}
}
