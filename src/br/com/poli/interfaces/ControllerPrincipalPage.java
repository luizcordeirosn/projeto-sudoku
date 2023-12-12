package br.com.poli.interfaces;

import br.com.poli.exceptions.GenericException;
import br.com.poli.game.*;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextField;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;

public class ControllerPrincipalPage implements Initializable {

	// Luiz e Júlia!!!

	@FXML
	private TextField txtName;

	@FXML
	private Label txtErro;

	@FXML
	Button buttonHowToPlay;

	@FXML
	private MenuItem menuItemEasy;

	@FXML
	private MenuItem menuItemNormal;

	@FXML
	private MenuItem menuItemHard;

	static Player player = new Player("");

	// Ir para a Dificuldade Fácil!!!
	@FXML
	public void clickButtonEasy(ActionEvent game) {

		try {

			player.setName(txtName.getText());

			new SecondView("ViewGameEasy.fxml").start(MainView.stage);

		} catch (GenericException e) {

			txtErro.setText(e.getMessage());
		}

		catch (Exception e) {
			System.err.println(e.getMessage());
		}
	}

	// Ir para a Dificuldade Normal!!!
	@FXML
	public void clickButtonNormal(ActionEvent game) {

		try {

			player.setName(txtName.getText());

			new SecondView("ViewGameNormal.fxml").start(MainView.stage);

		} catch (GenericException e) {

			txtErro.setText(e.getMessage());
		}

		catch (Exception e) {
			System.err.println(e.getMessage());
		}
	}

	// Ir para a Dificuldade Difícil!!
	@FXML
	public void clickButtonHard(ActionEvent game) {

		try {

			player.setName(txtName.getText());

			new SecondView("ViewGameHard.fxml").start(MainView.stage);

		} catch (GenericException e) {

			txtErro.setText(e.getMessage());
		}

		catch (Exception e) {
			System.err.println(e.getMessage());
		}
	}

	@FXML
	public void clickButtonaf(ActionEvent howtoplay) {
		try {
			new SecondView("ViewHowToPlay.fxml").start(MainView.stage);
		} catch (Exception e) {
			System.err.println(e.getMessage());
		}
	}

	@Override
	public void initialize(URL location, ResourceBundle resource) {
		// TODO Auto-generated method stub
	}
}
