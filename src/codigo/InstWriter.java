package codigo;


//Es una instrucci�n a la espera de escribirse.

public class InstWriter {
	public InstWriter(String instruccion) {
		this.instruccion = instruccion;
	}
	
	//Indica si se han resuelto las referencias.
	
	public boolean ready(){
		return true;
	}

	//Devuelve la instrucci�n que debe escribirse
	
	public String compilaInstruccion (){
		return instruccion;
	}
	String instruccion;
}
