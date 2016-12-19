package arbol;

import errores.ErrorDispatcher;
import errores.GestionErroresAnalisis;
import tipos.Tipo;
import tipos.TipoBasico;


public abstract class ExpresionAritmetica extends ExpresionBinaria {
	//Podrá ser una suma, resta, mult o división.
	public ExpresionAritmetica(Expresion hijoIzq, Expresion hijoDcha) {
		super( hijoIzq, hijoDcha );
	}
		
	//Deben hacerse sobre enteros.

	@Override
	public Tipo calculaTipo() {
		if(!(expIzq.getTipo().equals(TipoBasico.INT) &&
				expDcha.getTipo().equals(TipoBasico.INT)))
			GestionErroresAnalisis.getInstance().errorTipos("Los operandos de las expresiones aritmeticas deben ser enteros.", getFila());
		
		return TipoBasico.INT;
	}

}
