package chadres;

import campoDeBatalha.Peça;
import campoDeBatalha.Posicao;
import campoDeBatalha.Tabuleiro;

public abstract class PeçaDeChadres extends Peça {
	
	private Cores cores;
    private int contMovimento;
	
	public PeçaDeChadres(Tabuleiro tabuleiro, Cores cores) {
		super(tabuleiro);
		this.cores=cores;
	}

	public Cores getCores() {
		return cores;
	}
	public int getContMovimento() {
		return contMovimento;
	}
	protected void aumenteOsMovimentos() {
		contMovimento++;
	}
	protected void diminuaOsMovimentos() {
		contMovimento--;
	}

    public PosicaoDoChadres getPosicaoDoChadres() {
    	return PosicaoDoChadres.daPosicaoPara(posicao);
    }

	protected boolean essaPeçaEDoOponente(Posicao posicao) {
		PeçaDeChadres p = (PeçaDeChadres) getTabuleiro().peça(posicao);
		return p != null && p.getCores() != cores;
	}
}
