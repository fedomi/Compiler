package arbol;

public class Suma extends ExpresionAritmetica {

	public Suma(Expresion izda, Expresion dcha) {
		super(izda, dcha);
	}

	@Override
	protected String getInst() {
		return "add";
	}
	
}
