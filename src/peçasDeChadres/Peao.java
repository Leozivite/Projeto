package peçasDeChadres;

import campoDeBatalha.Posicao;
import campoDeBatalha.Tabuleiro;
import chadres.Cores;
import chadres.MatematicaDoChadres;
import chadres.PeçaDeChadres;

public class Peao extends PeçaDeChadres{
	
private MatematicaDoChadres matematicaDoChadres;
	
	public Peao(Tabuleiro tabuleiro, Cores cores, MatematicaDoChadres matematicaDoChadres) {
		super(tabuleiro, cores);
		this.matematicaDoChadres = matematicaDoChadres;
	}

	@Override
	public boolean[][] movimentosPossiveis() {
		boolean[][] mat = new boolean[getTabuleiro().getLinhasDoTabuleiro()][getTabuleiro().getColunasDoTabuleiro()];
		
		Posicao p = new Posicao(0, 0);

		if (getCores() == Cores.BRANCAS) {
			p.setValores(posicao.getLinhasDaPeça() - 1, posicao.getColunasDaPeça());
			if (getTabuleiro().existeHaPosicao(p) && !getTabuleiro().essaPeçaExiste(p)) {
				mat[p.getLinhasDaPeça()][p.getColunasDaPeça()] = true;
			}
			p.setValores(posicao.getLinhasDaPeça() - 2, posicao.getColunasDaPeça());
			Posicao p2 = new Posicao(posicao.getLinhasDaPeça() - 1, posicao.getColunasDaPeça());
			if (getTabuleiro().existeHaPosicao(p) && !getTabuleiro().essaPeçaExiste(p) && getTabuleiro().existeHaPosicao(p2) && !getTabuleiro().essaPeçaExiste(p2) && getContMovimento() == 0) {
				mat[p.getLinhasDaPeça()][p.getColunasDaPeça()] = true;
			}
			p.setValores(posicao.getLinhasDaPeça() - 1, posicao.getColunasDaPeça()-1);
			if (getTabuleiro().existeHaPosicao(p) && essaPeçaEDoOponente(p)) {
				mat[p.getLinhasDaPeça()][p.getColunasDaPeça()] = true;
			}			
			p.setValores(posicao.getLinhasDaPeça() - 1, posicao.getColunasDaPeça()+1);
			if (getTabuleiro().existeHaPosicao(p) && essaPeçaEDoOponente(p)) {
				mat[p.getLinhasDaPeça()][p.getColunasDaPeça()] = true;
			}	
			
			// #specialmove en passant white
			if (posicao.getLinhasDaPeça() == 3) {
				Posicao esquerda = new Posicao(posicao.getLinhasDaPeça(), posicao.getColunasDaPeça() - 1);
				if (getTabuleiro().existeHaPosicao(esquerda) && essaPeçaEDoOponente(esquerda) && getTabuleiro().peça(esquerda) == matematicaDoChadres.getemPassagemVulneravel()) {
					mat[esquerda.getLinhasDaPeça() - 1][esquerda.getColunasDaPeça()] = true;
				}
				Posicao direita = new Posicao(posicao.getLinhasDaPeça(), posicao.getColunasDaPeça() + 1);
				if (getTabuleiro().existeHaPosicao(direita) && essaPeçaEDoOponente(direita) && getTabuleiro().peça(direita) == matematicaDoChadres.getemPassagemVulneravel()) {
					mat[direita.getLinhasDaPeça() - 1][direita.getColunasDaPeça()] = true;
				}
			}
		}
		else {
			p.setValores(posicao.getLinhasDaPeça() + 1, posicao.getColunasDaPeça());
			if (getTabuleiro().existeHaPosicao(p) && !getTabuleiro().essaPeçaExiste(p)) {
				mat[p.getLinhasDaPeça()][p.getColunasDaPeça()] = true;
			}
			p.setValores(posicao.getLinhasDaPeça() + 2, posicao.getColunasDaPeça());
			Posicao p2 = new Posicao(posicao.getLinhasDaPeça() + 1, posicao.getColunasDaPeça());
			if (getTabuleiro().existeHaPosicao(p) && !getTabuleiro().essaPeçaExiste(p) && getTabuleiro().existeHaPosicao(p2) && !getTabuleiro().essaPeçaExiste(p2) && getContMovimento() == 0) {
				mat[p.getLinhasDaPeça()][p.getColunasDaPeça()] = true;
			}
			p.setValores(posicao.getLinhasDaPeça() + 1, posicao.getColunasDaPeça() - 1);
			if (getTabuleiro().existeHaPosicao(p) && essaPeçaEDoOponente(p)) {
				mat[p.getLinhasDaPeça()][p.getColunasDaPeça()] = true;
			}			
			p.setValores(posicao.getLinhasDaPeça() + 1, posicao.getColunasDaPeça() + 1);
			if (getTabuleiro().existeHaPosicao(p) && essaPeçaEDoOponente(p)) {
				mat[p.getLinhasDaPeça()][p.getColunasDaPeça()] = true;
			}
			
			// #specialmove en passant black
			if (posicao.getLinhasDaPeça() == 4) {
				Posicao esquerda  = new Posicao(posicao.getLinhasDaPeça(), posicao.getColunasDaPeça() - 1);
				if (getTabuleiro().existeHaPosicao(esquerda) && essaPeçaEDoOponente(esquerda) && getTabuleiro().peça(esquerda) == matematicaDoChadres.getemPassagemVulneravel()) {
					mat[esquerda.getLinhasDaPeça() + 1][esquerda.getColunasDaPeça()] = true;
				}
				Posicao direita = new Posicao(posicao.getLinhasDaPeça(), posicao.getColunasDaPeça() + 1);
				if (getTabuleiro().existeHaPosicao(direita) && essaPeçaEDoOponente(direita) && getTabuleiro().peça(direita) == matematicaDoChadres.getemPassagemVulneravel()) {
					mat[direita.getLinhasDaPeça() + 1][direita.getColunasDaPeça()] = true;
				}
			}			
		}
		return mat;
	}
	
	@Override
	public String toString() {
		return "P";
	}

}
