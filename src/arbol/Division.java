package arbol;


public class Division extends ExpresionAritmetica {

	public Division(Expresion izda, Expresion dcha) {
		super(izda, dcha);
	}
	
	@Override
	protected String getInst() {
		return "div";
	}
	
}
