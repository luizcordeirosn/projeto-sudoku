package br.com.poli.interfaces;

import br.com.poli.exceptions.*;
import br.com.poli.game.*;
import java.net.URL;
import java.util.Calendar;
import java.util.ResourceBundle;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.event.ActionEvent;
import javafx.fxml.*;
import javafx.geometry.Pos;

public class ControllerGameNormal implements Initializable {

	@FXML
	Button principalPage;

	@FXML
	private Button resolution;

	@FXML
	private Button verification;

	@FXML
	private Label txtName;

	@FXML
	private Label txtTime;

	@FXML
	private Label txtMessage;

	@FXML
	private Label txtErro;

	@FXML
	private TextField txtCoordX;

	@FXML
	private TextField txtCoordY;

	@FXML
	private TextField txtValue;

	@FXML
	private GridPane grid;

	SudokuNormal sudokuNormal = new SudokuNormal(ControllerPrincipalPage.player);
	AutonomousPlayer autonomousPlayer = new AutonomousPlayer(sudokuNormal);
	Cell cell = new Cell(0, false);

	// Método para voltar para a Página Inicial!!!
	public void clickPrincipalPage(ActionEvent principalPage) {

		try {
			new SecondView("ViewPrincipalPage.fxml").start(MainView.stage);
		} catch (Exception e) {
			System.err.println(e.getMessage());
		}
	}

	// Método para Iniciar a Grid do Sudoku!!!
	@FXML
	public void initilizeSudokuNormal() {

		// Condição para Inicializar o Sudoku caso o cell.isValid seja igual a false!!!
		if (sudokuNormal.getCell().isValid() == false) {

			sudokuNormal.startGame();
			sudokuNormal.getCell().setValid(true);

			txtName.setText(sudokuNormal.getPlayer().getName());
			txtTime.setText(String.valueOf(sudokuNormal.getStartTime().get(Calendar.HOUR_OF_DAY) + " h e "
					+ String.valueOf(sudokuNormal.getStartTime().get(Calendar.MINUTE)) + " min"));

			sudokuNormal.initilizeSudoku();
		}

		for (int i = 0; i < 9; i++) {
			for (int j = 0; j < 9; j++) {

				TextField txtixj = new TextField();
				txtixj.setPrefHeight(42.67);
				txtixj.setPrefWidth(42.78);
				txtixj.setAlignment(Pos.CENTER);
				txtixj.setEditable(false);

				if (sudokuNormal.getGridPlayer()[i][j].getValue() == 0) {

					txtixj.setText("");
					grid.add(txtixj, j, i);
				} else {

					if (sudokuNormal.getGridPlayer()[i][j].isFixed() == true) {

						txtixj.setStyle("-fx-font-weight: bold");
					}

					txtixj.setText(Integer.toString(sudokuNormal.getGridPlayer()[i][j].getValue()));
					grid.add(txtixj, j, i);
				}
			}
		}
	}

	// Método para Verificar se o Valor é Válido, Setar o Valor e caso tenha uma
	// Excessão, tratá-la!!!
	public void setValueSudokuNormal() {
		try {

			boolean complete = true;

			sudokuNormal.setValue(Integer.parseInt(txtCoordX.getText()), Integer.parseInt(txtCoordY.getText()),
					Integer.parseInt(txtValue.getText()));
			initilizeSudokuNormal();

			// Checar se algum Valor de GridPlayer seja igual 0!!!
			for (int i = 0; i < 9; i++) {
				for (int j = 0; j < 9; j++) {

					if (sudokuNormal.getGridPlayer()[i][j].getValue() == 0) {

						complete = false;
						i = 9;
						j = 9;
					}
				}
			}

			// Caso não tenha nenhum Valor de GridPlayer = 0, mandar a mensagem de Sudoku
			// completo e e mostrar o tempo decorrido
			if (complete == true) {

				sudokuNormal.endGame();

				txtMessage.setVisible(true);
				txtErro.setVisible(false);

				txtMessage.setText("Parabéns " + sudokuNormal.getPlayer().getName() + ", você completou o Sudoku em "
						+ sudokuNormal.getTotalTime() + " min decorridos. \n" + "Tempo Final: "
						+ String.valueOf(sudokuNormal.getEndTime().get(Calendar.HOUR_OF_DAY) + " h e "
								+ String.valueOf(sudokuNormal.getEndTime().get(Calendar.MINUTE)) + " min"));
			} else {

				txtMessage.setVisible(true);
				txtErro.setVisible(false);
				txtMessage.setText("Valor Válido");
			}

		} catch (CellIsFixedException | CellValueException e) {

			txtMessage.setVisible(false);
			txtErro.setVisible(true);
			txtErro.setText(e.getMessage());
		} catch (RuntimeException e) {

			txtMessage.setVisible(false);
			txtErro.setVisible(true);
			txtErro.setText(e.getMessage());
		}
	}

	// Método para resolver o Sudoku!!!
	public void resolutionSudokuNormal() {

		if (cell.isValid() == false) {
			sudokuNormal.endGame();
			autonomousPlayer.executeGame();
			initilizeSudokuNormal();

			txtMessage.setText("Tempo final: " + sudokuNormal.getEndTime().get(Calendar.HOUR_OF_DAY) + " h e "
					+ sudokuNormal.getEndTime().get(Calendar.MINUTE) + " min");
			
			cell.setValid(true);
		}
	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {

		initilizeSudokuNormal();
	}

}
