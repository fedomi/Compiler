package tipos;
//Tenemos o expresion tipo array o tipo básico, etc. Puede ponerse aparte.

import arbol.Entero;
import identificadores.ExcepcionIdentificador;
import identificadores.Visitor;

/**
 * Represents the array type.
 *
 */
public class TipoArray extends Tipo{
	
	public TipoArray (Tipo subtipo, Entero dimension){
		this.subtipo = subtipo;
		this.dimension = dimension;
	}
	/**
	 * The class of the type.
	 */
	@Override
	public Clases clase() {
		return Clases.Array;
	}
	/**
	 * The size of the type.
	 */
	@Override
	public int tam() {
		return dimension.getEntero()*subtipo.tam();
	}
	/**
	 * Returns the subtype.
	 */
	@Override
	public Tipo desempaquetar(String context) {
		return subtipo;
	}
	/**
	 * Visits the subtype.
	 */
	@Override
	public void aceptaVisitante(Visitor v) throws ExcepcionIdentificador {
		subtipo.aceptaVisitante(v);
	}
	
	@Override
	public boolean equals(Object obj) {
		if (obj instanceof TipoArray){
			TipoArray tmp = (TipoArray) obj;
			return tmp.subtipo.equals(subtipo);
		}
		return false;
	}
	/**
	 * Components of the type.
	 */
	private Entero dimension;
	private Tipo subtipo;
}
