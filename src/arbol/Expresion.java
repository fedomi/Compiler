package arbol;

import tipos.Tipo;
import codigo.CodeWriter;
import codigo.Entorno;
import codigo.EnteroReferencia;
//Puede ser de varios tipos: Unaria, binaria, etc.
import codigo.ExcepcionWriter;

//Clase general para una expresi�n, cada expresi�n implementar� las operaciones.
public abstract class Expresion extends Nodo {
	
	//Si el tipo a�n no est� calculado, lo calculamos. Por seguridad.
	public Tipo getTipo (){
		if (tipo == null)
			tipo = calculaTipo();
		
		return tipo;
	}

	protected abstract Tipo calculaTipo();
	

	public abstract void codeR(Entorno env, CodeWriter writer) throws ExcepcionWriter;

	public void codeA (Entorno env, CodeWriter writer) throws ExcepcionWriter{
		//Nos sirve para los b�sicos, el resto lo sobreescriben.
		codeR(env, writer);
	}

	protected abstract void maxStack (EnteroReferencia acc, EnteroReferencia max);
	
	//A esta altura solo almacenamos el tipo.
	Tipo tipo;
}
