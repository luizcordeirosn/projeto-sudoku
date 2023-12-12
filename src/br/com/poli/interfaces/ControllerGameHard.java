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

public class ControllerGameHard implements Initializable {

	@FXML
	private Button principalPage;

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

	SudokuHard sudokuHard = new SudokuHard(ControllerPrincipalPage.player);
	AutonomousPlayer autonomousPlayer = new AutonomousPlayer(sudokuHard);
	Cell cell = new Cell(0, false);

	// M�todo para voltar para a P�gina Inicial!!!
	public void clickPrincipalPage(ActionEvent principalPage) {

		try {
			new SecondView("ViewPrincipalPage.fxml").start(MainView.stage);
		} catch (Exception e) {
			System.err.println(e.getMessage());
		}
	}

	// M�todo para Iniciar a Grid do Sudoku!!!
	public void initilizeSudokuHard() {

		// Condi��o para Inicializar o Sudoku caso o cell.isValid seja igual a false!!!
		if (sudokuHard.getCell().isValid() == false) {

			sudokuHard.startGame();
			sudokuHard.getCell().setValid(true);

			txtName.setText(sudokuHard.getPlayer().getName());
			txtTime.setText(String.valueOf(sudokuHard.getStartTime().get(Calendar.HOUR_OF_DAY) + " h e "
					+ String.valueOf(sudokuHard.getStartTime().get(Calendar.MINUTE)) + " min"));

			sudokuHard.initilizeSudoku();
		}

		for (int i = 0; i < 9; i++) {
			for (int j = 0; j < 9; j++) {

				TextField txtixj = new TextField();
				txtixj.setPrefHeight(42.67);
				txtixj.setPrefWidth(42.78);
				txtixj.setAlignment(Pos.CENTER);
				txtixj.setEditable(false);

				if (sudokuHard.getGridPlayer()[i][j].getValue() == 0) {

					txtixj.setText("");
					grid.add(txtixj, j, i);
				} else {

					if (sudokuHard.getGridPlayer()[i][j].isFixed() == true) {

						txtixj.setStyle("-fx-font-weight: bold");
					}

					txtixj.setText(Integer.toString(sudokuHard.getGridPlayer()[i][j].getValue()));
					grid.add(txtixj, j, i);
				}
			}
		}
	}

	// M�todo para Verificar se o Valor � V�lido, Setar o Valor e caso tenha uma
	// Excess�o, trat�-la!!!
	public void setValueSudokuHard() {

		try {

			boolean complete = true;

			sudokuHard.setValue(Integer.parseInt(txtCoordX.getText()), Integer.parseInt(txtCoordY.getText()),
					Integer.parseInt(txtValue.getText()));
			initilizeSudokuHard();

			// Checar se algum Valor de GridPlayer seja igual 0!!!
			for (int i = 0; i < 9; i++) {
				for (int j = 0; j < 9; j++) {

					if (sudokuHard.getGridPlayer()[i][j].getValue() == 0) {

						complete = false;
						i = 9;
						j = 9;
					}
				}
			}

			// Caso n�o tenha nenhum Valor de GridPlayer = 0, mandar a mensagem de Sudoku
			// completo e e mostrar o tempo decorrido
			if (complete == true) {

				sudokuHard.endGame();

				txtMessage.setVisible(true);
				txtErro.setVisible(false);
				txtMessage.setText("Parab�ns " + sudokuHard.getPlayer().getName() + ", voc� completou o Sudoku em "
						+ sudokuHard.getTotalTime() + " min decorridos. \n" + "Tempo Final: "
						+ String.valueOf(sudokuHard.getEndTime().get(Calendar.HOUR_OF_DAY) + " h e "
								+ String.valueOf(sudokuHard.getEndTime().get(Calendar.MINUTE)) + " min"));
			} else {

				txtMessage.setVisible(true);
				txtErro.setVisible(false);
				txtMessage.setText("Valor V�lido");
			}

		}

		catch (CellIsFixedException | CellValueException e) {

			txtMessage.setVisible(false);
			txtErro.setVisible(true);
			txtErro.setText(e.getMessage());

		} catch (RuntimeException e) {

			txtMessage.setVisible(false);
			txtErro.setVisible(true);
			txtErro.setText(e.getMessage());
		}

	}

	// M�todo para resolver o Sudoku!!!
	public void resolutionSudokuHard() {

		if (cell.isValid() == false) {
			sudokuHard.endGame();
			autonomousPlayer.executeGame();
			initilizeSudokuHard();

			txtMessage.setText("Tempo final: " + sudokuHard.getEndTime().get(Calendar.HOUR_OF_DAY) + " h e "
					+ sudokuHard.getEndTime().get(Calendar.MINUTE) + " min");
			
			cell.setValid(true);
		}
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {

		initilizeSudokuHard();
	}

}
