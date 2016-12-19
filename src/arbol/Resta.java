package arbol;

public class Resta extends ExpresionAritmetica {

	public Resta(Expresion izda, Expresion dcha) {
		super(izda, dcha);
	}

	@Override
	protected String getInst() {
		return "sub";
	}
	
}
