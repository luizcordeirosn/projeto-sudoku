package br.com.poli.game;

public class SudokuHard extends Sudoku {

	public SudokuHard(Player player) {
		super(player);
	}

	@Override
	public void initilizeSudoku() {

		generatorSudokuRandom(0, 0);

		for (int i = 0; i < 9; i++) {
			for (int j = 0; j < 9; j++) {

				gridPlayer[i][j].setFixed(true);
			}
		}
		removeCellRandom(7);
	}
}