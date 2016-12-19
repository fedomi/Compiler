package arbol;

public class Menor extends ExpresionComparacion {

	public Menor(Expresion izqda, Expresion dcha) {
		super(izqda, dcha);
	}

	@Override
	protected String getInst() {
		return "les";
	}
	
}
