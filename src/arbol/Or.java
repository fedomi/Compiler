package arbol;

public class Or extends ExpresionLogica {

	public Or(Expresion izq, Expresion dcha) {
		super( izq, dcha );
	}

	@Override
	protected String getInst() {
		return "or";
	}
	
}
