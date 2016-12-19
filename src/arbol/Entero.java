
package arbol;


import codigo.CodeWriter;
import identificadores.Visitor;
import codigo.Entorno;
import codigo.ExcepcionWriter;
import codigo.EnteroReferencia;
import tipos.Tipo;
import tipos.TipoBasico;

//Es un entero, en una hoja del árbol abstracto. 
public class Entero extends Expresion {

	public Entero(int ent) {
		this.ent = ent;
	}

	
	public int getEntero(){
		return ent;
	}
	
	@Override
	public void aceptaVisitante(Visitor v) {
		//Como siempre, en los niveles bajos sin declaración no hacemos nada.
	}
	//Devolvemos el tipo, en este caso es entero. Permite la comprobación de tipos.
	@Override
	protected Tipo calculaTipo() {
		return TipoBasico.INT;
	}
	
	//Sencillamente el tratamiento para enteros: cargamos el valor.
	@Override
	public void codeR(Entorno env, CodeWriter writer) throws ExcepcionWriter {
		writer.write("ldc " + ent);
	}
	
	// Si aumentamos el máximo lo actualizamos, si no nada.
	@Override
	protected void maxStack(EnteroReferencia acc, EnteroReferencia max) {
		acc.i++;
		max.i = Math.max(acc.i, max.i);
	}
	
	private int ent;

}
