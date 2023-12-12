package br.com.poli.game;

import br.com.poli.exceptions.*;
import java.util.Calendar;
import java.util.Random;

public abstract class Sudoku extends Game {

	protected Cell[][] gridPlayer;
	private Cell cell;
	private Calendar totalTime;
	static int accounter;
	
	Random generator = new Random();

	public Sudoku(Player player) {

		super(player);
		cell = new Cell(0, false);
		gridPlayer = new Cell[9][9];

		for (int i = 0; i < 9; i++) {
			for (int j = 0; j < 9; j++) {

				gridPlayer[i][j] = new Cell(0, false);
			}
		}
	}

	public Cell[][] getGridPlayer() {
		return gridPlayer;
	}

	public void setGridPlayer(Cell[][] gridPlayer) {
		this.gridPlayer = gridPlayer;
	}

	public Cell getCell() {
		return cell;
	}

	public void setCell(Cell cell) {
		this.cell = cell;
	}

	public void setTotalTime(Calendar totalTime) {
		this.totalTime = totalTime;
	}

	// Método para atribuir as coordenadas da Cell!!!
	public void setValue(int coordX, int coordY, int value) throws CellIsFixedException, CellValueException {

		accounter++;
		cell.setValue(value);

		// checkValidation(cell, coordX, coordY);

		// Caso o Método checkValidation seja válido, atribuir o valor para a Matriz
		// GridPlayer!!!
		if (checkValidation(cell, coordX, coordY) == true) {

			gridPlayer[coordX][coordY].setValue(cell.getValue());
		}
	}

	// Método para checar se o valor da Cell será válida!!!
	public boolean checkValidation(Cell cell, int coordX, int coordY) throws CellIsFixedException, CellValueException {

		// Verificar as Coordenadas do Arrays!!!
		if (coordX < 0 || coordX > 8 || coordY < 0 || coordY > 8) {

			throw new CellValueException("Error ... Please try again");
		}

		// Verificar se o Valor de Cell está entre 1 e 9!!!
		else if (cell.getValue() <= 0 || cell.getValue() >= 10) {

			throw new CellValueException(this.getPlayer().getName() + ", Value must be> 1 or <9 !!!");
		}

		// Verificar se naquela coordenada o GridPlayer[i]![j].isFixed() == true!!!
		else if (gridPlayer[coordX][coordY].isFixed() == true) {

			throw new CellIsFixedException(this.getPlayer().getName() + ", you can not replace a fixed number !!!");
		}

		else if (gridPlayer[coordX][coordY].getValue() == cell.getValue()) {

			throw new CellValueException(
					this.getPlayer().getName() + ", you tried to replace with the same Grid value !!!");
		}

		for (int i = 0; i < 9; i++) {

			// Fixar a Coluna e checar se as Linhas tem o cell.value == GridPlayer.value!!!
			if (gridPlayer[i][coordY].getValue() == cell.getValue()) {

				throw new CellValueException(this.getPlayer().getName() + ", Invalid value for Column !!!");
			}
		}

		for (int j = 0; j < 9; j++) {

			// Fixar a Linha e checar se as Colunas tem o cell.value == GridPlayer.value!!!
			if (gridPlayer[coordX][j].getValue() == cell.getValue()) {

				throw new CellValueException(this.getPlayer().getName() + ", Invalid value for the Line !!!");
			}
		}

		int startX = 0;
		int endX = 0;
		int startY = 0;
		int endY = 0;

		// 1º Grid Interno!!!
		if ((coordX >= 0 && coordX <= 2) && (coordY >= 0 && coordY <= 2)) {

			startX = 0;
			endX = 3;
			startY = 0;
			endY = 3;
		}

		// 2º Grid Interno!!!
		else if ((coordX >= 3 && coordX <= 5) && (coordY >= 0 && coordY <= 2)) {

			startX = 3;
			endX = 6;
			startY = 0;
			endY = 3;
		}

		// 3º Grid Interno!!!
		else if ((coordX >= 6 && coordX <= 8) && (coordY >= 0 && coordY <= 2)) {

			startX = 6;
			endX = 9;
			startY = 0;
			endY = 3;
		}

		// 4º Grid Interno!!!
		else if ((coordX >= 0 && coordX <= 2) && (coordY >= 3 && coordY <= 5)) {

			startX = 0;
			endX = 3;
			startY = 3;
			endY = 6;
		}

		// 5º Grid Interno!!!
		else if ((coordX >= 3 && coordX <= 5) && (coordY >= 3 && coordY <= 5)) {

			startX = 3;
			endX = 6;
			startY = 3;
			endY = 6;
		}

		// 6º Grid Interno!!!
		else if ((coordX >= 6 && coordX <= 8) && (coordY >= 3 && coordY <= 5)) {

			startX = 6;
			endX = 9;
			startY = 3;
			endY = 6;
		}

		// 7º Grid Interno!!!
		else if ((coordX >= 0 && coordX <= 2) && (coordY >= 6 && coordY <= 8)) {

			startX = 0;
			endX = 3;
			startY = 6;
			endY = 9;
		}

		// 8º Grid Interno!!!
		else if ((coordX >= 3 && coordX <= 5) && (coordY >= 6 && coordY <= 8)) {

			startX = 3;
			endX = 6;
			startY = 6;
			endY = 9;
		}

		// 9º Grid Interno!!!
		else if ((coordX >= 6 && coordX <= 8) && (coordY >= 6 && coordY <= 8)) {

			startX = 6;
			endX = 9;
			startY = 6;
			endY = 9;
		}

		// Verificar se já existe o cell.getValue() no Grid!!!
		for (int i = startX; i < endX; i++) {
			for (int j = startY; j < endY; j++) {

				if (gridPlayer[i][j].getValue() == cell.getValue()) {

					throw new CellValueException(this.getPlayer().getName() + ", Invalid value for the Grid !!!");
				}
			}
		}

		// Caso nem as Colunas, nem as Linhas tenham, nem o Grid Interno tem o, retornar
		// verdadeira!!!
		return true;
	}

	// Método para retornar o Tempo decorrido!!!
	public int getTotalTime() {

		if ((super.getEndTime().get(Calendar.HOUR_OF_DAY) - super.getStartTime().get(Calendar.HOUR_OF_DAY)) >= 1) {

			return (super.getEndTime().get(Calendar.HOUR_OF_DAY) - super.getStartTime().get(Calendar.HOUR_OF_DAY)) * 60
					+ (super.getEndTime().get(Calendar.MINUTE) - super.getStartTime().get(Calendar.MINUTE));
		}

		return (super.getEndTime().get(Calendar.MINUTE) - super.getStartTime().get(Calendar.MINUTE));
	}

	@Override
	public void endGame() {

		super.endGame();
		// Falta SETAR o valor do Tempo Total em TotalTime...
	}

	// Método para Inicializar o Sudoku dependendo da Dificuldade!!!
	public abstract void initilizeSudoku();

	// Método para que irá remover células do Sudoku aleatóriamentedependendo da
	// Dificuldade!!!
	public void removeCellRandom(int removeCell) {

		int positionX[] = new int[9];
		int positionY[] = new int[9];
		int n = 0;

		while (n < removeCell) {

			int plusX = 0;
			int plusY = 0;

			// Gerar um ponto de X e Y aleatório para cada Grid!!!
			for (int i = 0; i < 9; i++) {

				if (i == 0 || i == 3 || i == 6) {
					plusX = 0;
					plusY = i;
				} else if (i == 1 || i == 4 || i == 7) {
					plusX = 3;
				} else if (i == 2 || i == 5 || i == 8) {
					plusX = 6;
				}

				positionX[i] = generator.nextInt(3) + plusX;
				positionY[i] = generator.nextInt(3) + plusY;
			}

			boolean valid = true;

			// Checar se no GridPlayer a PositionX e PositionY tem como Fixed == True!!!
			for (int i = 0; i < 9; i++) {
				if (gridPlayer[positionX[i]][positionY[i]].isFixed() != true) {
					valid = false;
					break;
				}
			}

			// Caso não haja, setar PositionX e PositionY com Value = 0 && Fixed =
			// false!!!
			if (valid == false) {
				continue;
			} else {
				for (int i = 0; i < 9; i++) {
					gridPlayer[positionX[i]][positionY[i]].setValue(0);
					gridPlayer[positionX[i]][positionY[i]].setFixed(false);
				}
				n++;
			}
		}
	}

	// Método para gerar um Sudoku aleatório válido!!!
	public boolean generatorSudokuRandom(int line, int column) {

		if (line == 9) {
			return true;
		} else {
			boolean returns = false;

			// Looping para a quantidade de testes de valores do NumberRandom!!!
			for (int i = 0; i < 9; i++) {

				if (returns == false) {
					int numberRandom;
					numberRandom = generator.nextInt(9) + 1;

					try {
						if (checkValidation(new Cell(numberRandom, false), line, column)) {
							this.gridPlayer[line][column].setValue(numberRandom);

							if (column == 8) {
								returns = generatorSudokuRandom(line + 1, 0);
							} else {
								returns = generatorSudokuRandom(line, column + 1);
							}
						}
					} catch (CellIsFixedException | CellValueException e) {
						gridPlayer[line][column].setValue(0);
					}
				}
			}
			// Retornar dependendo da situação!!!
			return returns;
		}
	}

}
