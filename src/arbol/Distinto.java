package arbol;

public class Distinto extends ExpresionComparacion {

	public Distinto(Expresion izq, Expresion dcha) {
		super( izq, dcha );
	}

	//Operación de comparación, distinto.
	@Override
	protected String getInst() {
		return "neq";
	}
}
