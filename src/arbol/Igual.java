package arbol;

public class Igual extends ExpresionLogica {

	public Igual(Expresion izq, Expresion dcha) {
		super( izq, dcha );
	}

	//Comparaci�n de igualdad.
	@Override
	protected String getInst() {
		return "equ";
	}
	
}
