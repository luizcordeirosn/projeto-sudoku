package br.com.poli.game;

import java.util.Random;
import br.com.poli.exceptions.*;

public class AutonomousPlayer {

	private Sudoku sudoku;

	Random generator = new Random();

	public AutonomousPlayer(Sudoku sudoku) {

		this.sudoku = sudoku;
	}

	public Sudoku getSudoku() {
		return sudoku;
	}

	public void setSudoku(Sudoku sudoku) {
		this.sudoku = sudoku;
	}

	// Método para colocar uma respota válida no Sudoku!!!
	public int executeMove(int plus) {

		int number = 1;
		return number + plus;
	}

	// Método para resolver o Sudoku!!!
	public void executeGame() {
		executeGameAux(0, 0);
	}

	// Método auxiliar para resolver o Sudoku!!!
	public boolean executeGameAux(int line, int column) {

		if (line == 9) {
			return true;
		} else {
			boolean returns = false;

			// Checar caso a posição tenha Fixed == true!!!
			if (sudoku.getGridPlayer()[line][column].isFixed() == true) {
				if (column == 8) {
					return executeGameAux(line + 1, 0);
				} else {
					return executeGameAux(line, column + 1);
				}
			}

			// Looping para a quantidade de testes de valores do Number!!!
			for (int i = 0; i < 10; i++) {

				if (returns == false) {

					try {
						// Caso não seja, setar no método setValue!!!
						sudoku.setValue(line, column, executeMove(i));

						if (column == 8) {
							returns = executeGameAux(line + 1, 0);
						} else {
							returns = executeGameAux(line, column + 1);
						}
					} catch (CellIsFixedException | CellValueException e) {
						sudoku.getGridPlayer()[line][column].setValue(0);
					}
				}
			}
			return returns;
		}
	}
}
