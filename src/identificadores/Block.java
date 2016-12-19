package identificadores;

import java.util.*;

public class Block {

	public Block(){
		puntero = null;
		Symbol = null;
	}

	public Symbol getSymbol() {
		return Symbol;
	}

	public void setSymbol(Symbol Symbol) {
		this.Symbol = Symbol;
	}

	public String getPuntero() {
		return puntero;
	}
	public void setPuntero(String puntero) {
		this.puntero = puntero;
	}

	private String puntero;
	private Symbol Symbol;
}
