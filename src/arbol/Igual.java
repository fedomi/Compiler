package arbol;

public class Igual extends ExpresionLogica {

	public Igual(Expresion izq, Expresion dcha) {
		super( izq, dcha );
	}

	//Comparación de igualdad.
	@Override
	protected String getInst() {
		return "equ";
	}
	
}
