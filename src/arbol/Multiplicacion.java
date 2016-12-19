package arbol;


//Nodo de  la multiplicación.
public class Multiplicacion extends ExpresionAritmetica {

	public Multiplicacion(Expresion izq, Expresion dcha) {
		super(izq, dcha);
	}
	
	
	//Especifica la operación, en este caso una multiplicación (para máquina P).
	@Override
	protected String getInst() {
		return "mul";
	}

}
