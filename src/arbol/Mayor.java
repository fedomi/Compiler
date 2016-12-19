package arbol;



public class Mayor extends ExpresionComparacion {
	//Comparación de mayor.
	public Mayor(Expresion izqda, Expresion dcha) {
		super(izqda, dcha);
	}

	
	@Override
	protected String getInst() {
		return "grt";
	}
	
	
}
