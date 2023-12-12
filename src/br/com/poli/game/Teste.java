package br.com.poli.game;

import main.TestSudoku;

public class Teste {

	public static void main(String[] args) {

		Player p = new Player("Luiz");
		SudokuHard s = new SudokuHard(p);
		AutonomousPlayer aP = new AutonomousPlayer(s);
		String retorno = TestSudoku.execute(aP);
		
		s.initilizeSudoku();	
		System.out.println(retorno);
		System.out.println(Sudoku.accounter);
	}

}
