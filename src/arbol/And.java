package arbol;

public class And extends ExpresionLogica {
	//And l�gico
	public And(Expresion izq, Expresion dcha) {
		super(izq, dcha);
	}

	@Override
	protected String getInst() {
		return "and";
	}
	
}
