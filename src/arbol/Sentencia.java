
package arbol;

import codigo.CodeWriter;
import codigo.Entorno;
import codigo.ExcepcionWriter;

public abstract class Sentencia extends Nodo {
	//Métodos que debe implementar toda sentencia.

	public abstract void resuelveTipo();

	public abstract void code(Entorno env, CodeWriter writer) throws ExcepcionWriter;

	public abstract int evStackLength ();

	//Permite distinguir las declaraciones del resto de instrucciones
	// de cara a calcular el espacio estático que se reserva
	protected boolean isDeclaracion = false;
	
}
