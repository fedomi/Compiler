package arbol;

public class Distinto extends ExpresionComparacion {

	public Distinto(Expresion izq, Expresion dcha) {
		super( izq, dcha );
	}

	//Operaci�n de comparaci�n, distinto.
	@Override
	protected String getInst() {
		return "neq";
	}
}
