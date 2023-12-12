package br.com.poli.game;

import br.com.poli.exceptions.*;

public class Player {

	private String name;

	public Player(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) throws GenericException {

		if (name.isEmpty()) {

			throw new GenericException("Player Must Have a Name !!!");
		} else
			this.name = name;
	}

}
