package br.com.poli.game;

public class Cell {

	private int value;
	private boolean valid;
	private boolean fixed;

	public Cell(int value, boolean fixed) {

		this.value = value;
		this.fixed = fixed;
	}

	public int getValue() {
		return value;
	}

	public void setValue(int value) {
		this.value = value;
	}

	public boolean isValid() {
		return valid;
	}

	public void setValid(boolean valid) {
		this.valid = valid;
	}

	public boolean isFixed() {
		return fixed;
	}

	public void setFixed(boolean fixed) {
		this.fixed = fixed;
	}

}
