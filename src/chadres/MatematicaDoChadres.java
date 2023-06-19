package chadres;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import campoDeBatalha.Peça;
import campoDeBatalha.Posicao;
import campoDeBatalha.Tabuleiro;
import peçasDeChadres.Bispo;
import peçasDeChadres.Cavalo;
import peçasDeChadres.Peao;
import peçasDeChadres.Rainha;
import peçasDeChadres.Rei;
import peçasDeChadres.Torre;

public class MatematicaDoChadres {
	private int turno;
	private Cores jogoDeCorrente;
	private Tabuleiro tabuleiro;
	private boolean check;
	private boolean checkMate;
	private PeçaDeChadres emPassagemVulneravel;
	private PeçaDeChadres promocao;

	private List<Peça> peçaNoTabuleiro = new ArrayList<>();
	private List<Peça> peçaCapturadas = new ArrayList<>();

	public MatematicaDoChadres() {
		tabuleiro = new Tabuleiro(8, 8);
		turno = 1;
		jogoDeCorrente = Cores.BRANCAS;
		InicioDeJogo();
	}

	public int getTurno() {
		return turno;
	}

	public Cores getJogoDeCorrente() {
		return jogoDeCorrente;
	}

	public boolean getCheck() {
		return check;
	}

	public boolean getCheckMate() {
		return checkMate;
	}

	public PeçaDeChadres getemPassagemVulneravel() {
		return emPassagemVulneravel;
	}

	public PeçaDeChadres getpromocao() {
		return promocao;
	}

	public PeçaDeChadres[][] getPeças() {
		PeçaDeChadres[][] PEÇA = new PeçaDeChadres[tabuleiro.getLinhasDoTabuleiro()][tabuleiro.getColunasDoTabuleiro()];
		for (int i = 0; i < tabuleiro.getLinhasDoTabuleiro(); i++) {
			for (int j = 0; j < tabuleiro.getColunasDoTabuleiro(); j++) {
				PEÇA[i][j] = (PeçaDeChadres) tabuleiro.peça(i, j);
			}
		}
		return PEÇA;
	}

	public boolean[][] movimentosPossiveisBoolean(PosicaoDoChadres posicaoDeOrigem) {
		Posicao posicao = posicaoDeOrigem.paraHaPosicao();
		validandoPosicaoDeOrigem(posicao);
		return tabuleiro.peça(posicao).movimentosPossiveis();
	}

	public PeçaDeChadres performandoMovimentoDoChadres(PosicaoDoChadres posicaoDeOrigem,
			PosicaoDoChadres posicaoDeDestino) {
		Posicao Origem = posicaoDeOrigem.paraHaPosicao();
		Posicao Destino = posicaoDeDestino.paraHaPosicao();
		validandoPosicaoDeOrigem(Origem);
		validandoPosicaoDeDestino(Origem, Destino);
		Peça peçaDeCaptura = fazerMover(Origem, Destino);

		if (testCheck(jogoDeCorrente)) {
			desfazerMovimento(Origem, Destino, peçaDeCaptura);
			throw new ExcecoesDoChadres("Voce nao Pode Mover Pois Seu Rei esta em check");
		}

		 PeçaDeChadres MoverPeça= (PeçaDeChadres) tabuleiro.peça(Destino);
		check = (testCheck(oponente(jogoDeCorrente))) ? true : false;

		if (testCheckMate(oponente(jogoDeCorrente))) {
			checkMate = true;
		} else {
			ProximoTurno();
		}

		
		// #specialmove en passant
				if (MoverPeça instanceof Peao && (Destino.getLinhasDaPeça() == Origem.getLinhasDaPeça() - 2 || Destino.getLinhasDaPeça() == Origem.getLinhasDaPeça() + 2)) {
					emPassagemVulneravel = MoverPeça;
				}
				else {
					emPassagemVulneravel = null;
				}
				
				return (PeçaDeChadres)peçaDeCaptura;
			
		 
	}

	public PeçaDeChadres peçaDeReposicaoPromovida(String type) {
		if (promocao == null) {
			throw new IllegalStateException("There is no piece to be promoted");
		}
		if (!type.equals("B") && !type.equals("N") && !type.equals("R") & !type.equals("Q")) {
			return promocao;
		}
		
		Posicao pos = promocao.getPosicaoDoChadres().paraHaPosicao();
		Peça p = tabuleiro.removePeça(pos);
		peçaNoTabuleiro.remove(p);
		
		PeçaDeChadres novaPeça = NovaPeça(type, promocao.getCores());
		tabuleiro.peçaDeReposicao(novaPeça, pos);
		peçaNoTabuleiro.add(novaPeça);
		
		return novaPeça;
	}
	private PeçaDeChadres NovaPeça (String tipo, Cores cores) {
		if (tipo.equals("B")) return new Bispo(tabuleiro, cores);
		if (tipo.equals("C")) return new Cavalo(tabuleiro, cores);
		if (tipo.equals("r")) return new Rainha(tabuleiro, cores);
		return new Torre(tabuleiro, cores);
	}
	private Peça fazerMover(Posicao Origem, Posicao Destino) {
		PeçaDeChadres p = (PeçaDeChadres) tabuleiro.removePeça(Origem);
		p.aumenteOsMovimentos();
		Peça peçaCapturada = tabuleiro.removePeça(Destino);
		tabuleiro.peçaDeReposicao(p, Destino);

		if (peçaCapturada != null) {
			peçaCapturadas.remove(peçaCapturada);
			peçaNoTabuleiro.add(peçaCapturada);
		}

		// #specialmove castling kingside rook
		if (p instanceof Rei && Destino.getColunasDaPeça() == Origem.getColunasDaPeça() + 2) {
			Posicao OrigemT = new Posicao(Origem.getLinhasDaPeça(), Origem.getColunasDaPeça() + 3);
			Posicao DestinoT = new Posicao(Origem.getLinhasDaPeça(), Origem.getColunasDaPeça() + 1);
			PeçaDeChadres Torre = (PeçaDeChadres) tabuleiro.removePeça(OrigemT);
			tabuleiro.peçaDeReposicao(Torre, DestinoT);
			Torre.getContMovimento();
		}

		// #specialmove castling queenside rook
		if (p instanceof Rei && Destino.getColunasDaPeça() == Origem.getColunasDaPeça() - 2) {
			Posicao OrigemT = new Posicao(Origem.getLinhasDaPeça(), Origem.getColunasDaPeça() - 4);
			Posicao DestinoT = new Posicao(Origem.getLinhasDaPeça(), Origem.getColunasDaPeça() - 1);
			PeçaDeChadres Torre = (PeçaDeChadres) tabuleiro.removePeça(OrigemT);
			tabuleiro.peçaDeReposicao(Torre, DestinoT);
			Torre.getContMovimento();
		}

		// #specialmove en passant
		if (p instanceof Peao) {
			if (Origem.getColunasDaPeça() != Destino.getColunasDaPeça() && peçaCapturada == null) {
				Posicao posicaoDoPeao;
				if (p.getCores() == Cores.BRANCAS) {
					posicaoDoPeao = new Posicao(Destino.getLinhasDaPeça() + 1, Destino.getColunasDaPeça());
				} else {
					posicaoDoPeao = new Posicao(Destino.getLinhasDaPeça() - 1, Destino.getColunasDaPeça());
				}
				peçaCapturada = tabuleiro.removePeça(posicaoDoPeao);
				peçaCapturadas.add(peçaCapturada);
				peçaNoTabuleiro.remove(peçaCapturada);
			}
		}
		return peçaCapturada;
	}

	private void validandoPosicaoDeOrigem(Posicao posicao) {
		if (!tabuleiro.essaPeçaExiste(posicao)) {
			throw new ExcecoesDoChadres("There is no piece on source position");
		}
		if (jogoDeCorrente != ((PeçaDeChadres) tabuleiro.peça(posicao)).getCores()) {
			throw new ExcecoesDoChadres("The chosen piece is not yours");
		}
		if (!tabuleiro.peça(posicao).esseMovimentoEPossivel()) {
			throw new ExcecoesDoChadres("There is no possible moves for the chosen piece");
		}
	}
//
	private Peça desfazerMovimento(Posicao Origem, Posicao Destino, Peça peçaCapturada) {
		PeçaDeChadres p = (PeçaDeChadres) tabuleiro.removePeça(Destino);
		p.diminuaOsMovimentos();
		Peça PeçasCapturadas = tabuleiro.removePeça(Destino);
		tabuleiro.peçaDeReposicao(p, Origem);
		
		peçaNoTabuleiro.remove(PeçasCapturadas);
		peçaCapturadas.add(PeçasCapturadas);

		// #specialmove castling kingside rook
		if (p instanceof Rei && Destino.getColunasDaPeça() == Origem.getColunasDaPeça() + 2) {
			Posicao OrigemT = new Posicao(Origem.getLinhasDaPeça(), Origem.getColunasDaPeça() + 3);
			Posicao DestinoT = new Posicao(Origem.getLinhasDaPeça(), Origem.getColunasDaPeça() + 1);
			PeçaDeChadres torre = (PeçaDeChadres) tabuleiro.removePeça(OrigemT);
			tabuleiro.peçaDeReposicao(torre, DestinoT);
			torre.diminuaOsMovimentos();
		}

		// #specialmove castling queenside rook
		if (p instanceof Rei && Destino.getColunasDaPeça() == Origem.getColunasDaPeça() - 2) {
			Posicao OrigemT = new Posicao(Origem.getLinhasDaPeça(), Origem.getColunasDaPeça() - 4);
			Posicao DestinoT = new Posicao(Origem.getLinhasDaPeça(), Origem.getColunasDaPeça() - 1);
			PeçaDeChadres torre = (PeçaDeChadres) tabuleiro.removePeça(OrigemT);
			tabuleiro.peçaDeReposicao(torre, DestinoT);
			torre.diminuaOsMovimentos();
		}

		// #specialmove en passant
		if (p instanceof Peao) {
			if (Origem.getColunasDaPeça() != Destino.getColunasDaPeça() && peçaCapturada == null) {
				Posicao posicaoDoPeao;
				if (p.getCores() == Cores.BRANCAS) {
					posicaoDoPeao = new Posicao(Destino.getLinhasDaPeça() + 1, Destino.getColunasDaPeça());
				} else {
					posicaoDoPeao = new Posicao(Destino.getLinhasDaPeça() - 1, Destino.getColunasDaPeça());
				}
				peçaCapturada = tabuleiro.removePeça(posicaoDoPeao);
				peçaCapturadas.add(PeçasCapturadas);
				peçaNoTabuleiro.remove(PeçasCapturadas);
			}
		}
		return peçaCapturada;

	}

	private void validandoPosicaoDeDestino(Posicao Origem, Posicao Destino) {
		if (!tabuleiro.peça(Origem).movimentosPossivel(Destino)) {
			throw new ExcecoesDoChadres("The chosen piece can't move to target position");
		}
	}

	private void ProximoTurno() {
		turno++;
		jogoDeCorrente = (jogoDeCorrente == Cores.BRANCAS) ? Cores.PRETAS : Cores.BRANCAS;
	}

	private Cores oponente(Cores color) {
		return (color == Cores.BRANCAS) ? Cores.PRETAS : Cores.BRANCAS;
	}

	private PeçaDeChadres Rei(Cores cores) {
		List<Peça> list = peçaNoTabuleiro.stream().filter(x -> ((PeçaDeChadres) x).getCores() == cores)
				.collect(Collectors.toList());
		for (Peça p : list) {
			if (p instanceof Rei) {
				return (PeçaDeChadres) p;
			}
		}
		throw new IllegalStateException("There is no " + cores + " king on the board");
	}

	private boolean testCheck(Cores cores) {
		Posicao posicaoDoRei = Rei(cores).getPosicaoDoChadres().paraHaPosicao();
		List<Peça> peçaDoOponente = peçaNoTabuleiro.stream()
				.filter(x -> ((PeçaDeChadres) x).getCores() == oponente(cores)).collect(Collectors.toList());
		for (Peça p : peçaDoOponente) {
			boolean[][] mat = p.movimentosPossiveis();
			if (mat[posicaoDoRei.getLinhasDaPeça()][posicaoDoRei.getColunasDaPeça()]) {
				return true;
			}
		}
		return false;
	}

	private boolean testCheckMate(Cores cores) {
		if (!testCheck(cores)) {
			return false;
		}
		List<Peça> list = peçaNoTabuleiro.stream().filter(x -> ((PeçaDeChadres) x).getCores() == cores)
				.collect(Collectors.toList());
		for (Peça p : list) {
			boolean[][] mat = p.movimentosPossiveis();
			for (int i = 0; i < tabuleiro.getLinhasDoTabuleiro(); i++) {
				for (int j = 0; j < tabuleiro.getColunasDoTabuleiro(); j++) {
					if (mat[i][j]) {
						Posicao Origem = ((PeçaDeChadres) p).getPosicaoDoChadres().paraHaPosicao();
						Posicao Destino = new Posicao(i, j);
						Peça capturedPiece = fazerMover(Origem, Destino);
						boolean testCheck = testCheck(cores);
						desfazerMovimento(Origem, Destino, capturedPiece);
						if (!testCheck) {
							return false;
						}
					}
				}
			}
		}
		return true;
	}

	private void novaPeçaDeReposicao(char coluna, int linha, PeçaDeChadres peça) {
		tabuleiro.peçaDeReposicao(peça, new PosicaoDoChadres(coluna, linha).paraHaPosicao());
		peçaNoTabuleiro.add(peça);
	}

	private void InicioDeJogo() {
		novaPeçaDeReposicao('a', 1, new Torre(tabuleiro, Cores.BRANCAS));
		novaPeçaDeReposicao('e', 1, new Rei(tabuleiro, Cores.BRANCAS,this));
		novaPeçaDeReposicao('h', 1, new Torre(tabuleiro, Cores.BRANCAS));
		novaPeçaDeReposicao('a', 2, new Peao(tabuleiro, Cores.BRANCAS, this));
		novaPeçaDeReposicao('b', 2, new Peao(tabuleiro, Cores.BRANCAS, this));
		novaPeçaDeReposicao('c', 2, new Peao(tabuleiro, Cores.BRANCAS, this));
		novaPeçaDeReposicao('d', 2, new Peao(tabuleiro, Cores.BRANCAS, this));
		novaPeçaDeReposicao('e', 2, new Peao(tabuleiro, Cores.BRANCAS, this));
		novaPeçaDeReposicao('f', 2, new Peao(tabuleiro, Cores.BRANCAS, this));
		novaPeçaDeReposicao('g', 2, new Peao(tabuleiro, Cores.BRANCAS, this));
		novaPeçaDeReposicao('h', 2, new Peao(tabuleiro, Cores.BRANCAS, this));
		novaPeçaDeReposicao('c', 1, new Bispo(tabuleiro, Cores.BRANCAS));
		novaPeçaDeReposicao('f', 1, new Bispo(tabuleiro, Cores.BRANCAS));
		novaPeçaDeReposicao('b', 1, new Cavalo(tabuleiro, Cores.BRANCAS));
		novaPeçaDeReposicao('g', 1, new Cavalo(tabuleiro, Cores.BRANCAS));
		novaPeçaDeReposicao('d', 1, new Rainha(tabuleiro, Cores.BRANCAS));

		novaPeçaDeReposicao('a', 8, new Torre(tabuleiro, Cores.PRETAS));
		novaPeçaDeReposicao('e', 8, new Rei(tabuleiro, Cores.PRETAS,this ));
		novaPeçaDeReposicao('h', 8, new Torre(tabuleiro, Cores.PRETAS));
		novaPeçaDeReposicao('a', 7, new Peao(tabuleiro, Cores.PRETAS, this));
		novaPeçaDeReposicao('b', 7, new Peao(tabuleiro, Cores.PRETAS, this));
		novaPeçaDeReposicao('c', 7, new Peao(tabuleiro, Cores.PRETAS, this));
		novaPeçaDeReposicao('d', 7, new Peao(tabuleiro, Cores.PRETAS, this));
		novaPeçaDeReposicao('e', 7, new Peao(tabuleiro, Cores.PRETAS, this));
		novaPeçaDeReposicao('f', 7, new Peao(tabuleiro, Cores.PRETAS, this));
		novaPeçaDeReposicao('g', 7, new Peao(tabuleiro, Cores.PRETAS, this));
		novaPeçaDeReposicao('h', 7, new Peao(tabuleiro, Cores.PRETAS, this));
		novaPeçaDeReposicao('c', 8, new Bispo(tabuleiro, Cores.PRETAS));
		novaPeçaDeReposicao('f', 8, new Bispo(tabuleiro, Cores.PRETAS));
		novaPeçaDeReposicao('b', 8, new Cavalo(tabuleiro, Cores.PRETAS));
		novaPeçaDeReposicao('g', 8, new Cavalo(tabuleiro, Cores.PRETAS));
		novaPeçaDeReposicao('d', 8, new Rainha(tabuleiro, Cores.PRETAS));
	}

}