package arbol;


//Nodo de  la multiplicaci�n.
public class Multiplicacion extends ExpresionAritmetica {

	public Multiplicacion(Expresion izq, Expresion dcha) {
		super(izq, dcha);
	}
	
	
	//Especifica la operaci�n, en este caso una multiplicaci�n (para m�quina P).
	@Override
	protected String getInst() {
		return "mul";
	}

}
