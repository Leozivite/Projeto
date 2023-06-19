package application;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

import chadres.ExcecoesDoChadres;
import chadres.MatematicaDoChadres;
import chadres.PeçaDeChadres;
import chadres.PosicaoDoChadres;

public class Program {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		MatematicaDoChadres matematicaDoChadres = new MatematicaDoChadres();
        List<PeçaDeChadres> capituradas = new ArrayList<>();
        
		while (!matematicaDoChadres.getCheckMate()) {
			try {
				UI.clearScreen();
				UI.printMatematicao(matematicaDoChadres,capituradas);
				System.out.println();
				System.out.print("Origem: ");
				PosicaoDoChadres Origem = UI.leiaHaPosicaoDoUsuario(sc);
                boolean[][] movimentosPossiveis2 = matematicaDoChadres.movimentosPossiveisBoolean(Origem);
                UI.clearScreen();
                UI.printTabuleiro(matematicaDoChadres.getPeças(),movimentosPossiveis2);
				System.out.println();
				System.out.print("Destino ");
				PosicaoDoChadres destino = UI.leiaHaPosicaoDoUsuario(sc);
				
				PeçaDeChadres peçaDeCaptura = matematicaDoChadres.performandoMovimentoDoChadres(Origem, destino);
	            if(peçaDeCaptura!=null) {
	            	capituradas.add(peçaDeCaptura);
	            }		
			}
			catch (ExcecoesDoChadres e) {
				System.out.println(e.getMessage());
				sc.nextLine();

			}
			catch (InputMismatchException e) {
				System.out.println(e.getMessage());
				sc.nextLine();

			}
		}
		UI.clearScreen();
		UI.printMatematicao(matematicaDoChadres, capituradas);
	}
}
