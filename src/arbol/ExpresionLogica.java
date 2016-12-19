package arbol;

import errores.ErrorDispatcher;
import errores.GestionErroresAnalisis;
import tipos.Tipo;
import tipos.TipoBasico;


public abstract class ExpresionLogica extends ExpresionBinaria {

	public ExpresionLogica(Expresion izqda, Expresion dcha) {
		super(izqda, dcha);
	}
	
	//Corresponde a las operaciones AND y OR entre booleanos.
	public Tipo calculaTipo(){
		if(!(get_izq().getTipo().equals(TipoBasico.BOOL) &&
				get_dcha().getTipo().equals(TipoBasico.BOOL)))
			GestionErroresAnalisis.getInstance().errorTipos("Los operandos de las expresiones lógicas deben ser booleanos.", getFila());
		
		return TipoBasico.BOOL;
	}
}
