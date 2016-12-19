package arbol;

import errores.ErrorDispatcher;
import errores.GestionErroresAnalisis;
import tipos.Tipo;
import tipos.TipoBasico;


public abstract class ExpresionComparacion extends ExpresionBinaria {

	public ExpresionComparacion(Expresion izqda, Expresion dcha) {
		super(izqda, dcha);
	}

	public Tipo calculaTipo(){
		
		if(!(get_izq().getTipo().equals(TipoBasico.INT) &&
				get_dcha().getTipo().equals(TipoBasico.INT)))
			GestionErroresAnalisis.getInstance().errorTipos("Los operandos de las expresiones comparativas deben ser enteros.", getFila());
		
		return TipoBasico.BOOL;
		//Una comparaci�n siempre tendr� tipo bool, y se har� entre enteros.
		
	}
}
