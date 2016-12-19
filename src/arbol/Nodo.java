
package arbol;

import identificadores.Visitor;

//Nodo general del árbol abstracto. Para especificar la fila del error,
// al leer desde el cup guardamos la fila. Luego donde generamos el error accedemos 
// a la fila y la especificamos.
public abstract class Nodo {

	public abstract void aceptaVisitante (Visitor v) ;
	
	public void setFila (int fila) {
		this.fila = fila;
	}
	public int getFila (){
		return this.fila;
	}
	private int fila;
}
