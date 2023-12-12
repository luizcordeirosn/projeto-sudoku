package br.com.poli.interfaces;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.event.ActionEvent;
import javafx.fxml.*;

public class ControllerHowToPlay implements Initializable {

	@FXML
	Button principalPage;

	public void clickPrincipalPage(ActionEvent principalPage) {

		try {
			new SecondView("ViewPrincipalPage.fxml").start(MainView.stage);
		} catch (Exception e) {
			System.err.println(e.getMessage());
		}
	}

	@Override
	public void initialize(URL location, ResourceBundle resource) {
		// TODO Auto-generated method stub

	}

}
