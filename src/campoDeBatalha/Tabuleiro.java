package campoDeBatalha;

public class Tabuleiro {
	
	private int linhasDoTabuleiro;
	private int colunasDoTabuleiro;
	private Peça[][] peças;

	public Tabuleiro(int linhasDoTabuleiro, int colunasDoTabuleiro) {
		if (linhasDoTabuleiro < 1 || colunasDoTabuleiro < 1) {
			throw new ExecoesDoTabuleiro("Erro criando tabuleiro e nesseçario que haja " + "pelomenos uma linha e uma coluna ");
		}
		this.linhasDoTabuleiro = linhasDoTabuleiro;
		this.colunasDoTabuleiro = colunasDoTabuleiro;
		peças = new Peça[linhasDoTabuleiro][colunasDoTabuleiro];
	}

	public int getLinhasDoTabuleiro() {
		return linhasDoTabuleiro;
	}

	public int getColunasDoTabuleiro() {
		return colunasDoTabuleiro;
	}

	//
	//
	//
	// comando para as peças
	public Peça peça(int linhasDaPeça, int colunasDaPeça) {
		if (!existeHaPosicao(linhasDaPeça, colunasDaPeça)) {
		  throw new ExecoesDoTabuleiro("a posicao nao existe");
		}
			return peças[linhasDaPeça][colunasDaPeça];
		
	}

	public Peça peça(Posicao posicao) {
		if (!existeHaPosicao(posicao)) {
			throw new ExecoesDoTabuleiro("posicao nao existe ");
		}
		return peças[posicao.getLinhasDaPeça()][posicao.getColunasDaPeça()];
	}

	public void peçaDeReposicao(Peça peça, Posicao posicao) {
if(essaPeçaExiste(posicao)) {
	throw new ExecoesDoTabuleiro("ja existe uma peça ma posicao");
	}
 peças[posicao.getLinhasDaPeça()][posicao.getColunasDaPeça()]=peça;
 peça.posicao=posicao;
	}
public Peça removePeça(Posicao posicao) {
	if(!existeHaPosicao(posicao)) {
		throw new ExecoesDoTabuleiro("Position not on the board");
	}
	if(peça(posicao)==null){
		return null;
	}
		Peça aux = peça(posicao);
		aux.posicao=null;
		peças[posicao.getLinhasDaPeça()][posicao.getColunasDaPeça()]=null;
		return aux;
	
	}

	private boolean existeHaPosicao(int linha, int coluna) {
		return linha >= 0 && linha < linhasDoTabuleiro && coluna >= 0 && coluna < colunasDoTabuleiro;
	}

	public boolean existeHaPosicao(Posicao posicao) {
		return existeHaPosicao(posicao.getLinhasDaPeça(), posicao.getColunasDaPeça());
	}

	public boolean essaPeçaExiste(Posicao posicao) {
		if (!existeHaPosicao(posicao)) {
			throw new ExecoesDoTabuleiro("posicao nao existe ");
		}
		return peça(posicao) != null;
	}
	
}
