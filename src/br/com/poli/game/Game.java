package br.com.poli.game;

import java.util.Calendar;

public class Game {

	private Player player;
	private Calendar startTime;
	private Calendar endTime;

	public Game(Player player) {

		this.player = player;
	}

	public Player getPlayer() {
		return player;
	}

	public void setPlayer(Player player) {
		this.player = player;
	}

	public Calendar getStartTime() {
		return startTime;
	}

	public void setStartTime(Calendar startTime) {
		this.startTime = startTime;
	}

	public Calendar getEndTime() {
		return endTime;
	}

	public void setEndTime(Calendar endTime) {
		this.endTime = endTime;
	}

	// Método para armazenar o Tempo Inicial do Game, junto com o Nome do Player!!!
	public void startGame() {

		this.startTime = Calendar.getInstance();
	}

	// Método para armazenar o Tempo Final do Game!!!
	public void endGame() {

		this.endTime = Calendar.getInstance();
	}

}
