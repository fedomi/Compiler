package arbol;

import identificadores.Visitor;
import codigo.CodeWriter;
import codigo.ContextType;
import codigo.Entorno;
import codigo.ExcepcionWriter;
import codigo.EnteroReferencia;

public class Return extends Sentencia{
	// Es la instrucci�n de devoluci�n de una funci�n
	
	public Return(Expresion exp) {
		this.expresion = exp;
	}
	
	@Override
	public void resuelveTipo() {
		expresion.getTipo();
	}

	/* Primero resolvemos el valor a devolver y luego inclu�mos las instrucciones 
	 * de la m�quina p para la devoluci�n de valores en las funciones.
	 */
	@Override
	public void code(Entorno env, CodeWriter writer) throws ExcepcionWriter {
		expresion.codeR(env, writer);
		writer.write("str 0 0");
		if (env.contextType().equals(ContextType.Procedure))
			writer.write("retp");
		else if (env.contextType().equals(ContextType.Function))
			writer.write("retf");
	}

	@Override
	public int evStackLength() {
		EnteroReferencia acc = new EnteroReferencia(0), max = new EnteroReferencia(0);
		expresion.maxStack(acc, max);
		
		return max.i;
	}

	@Override
	public void aceptaVisitante(Visitor v) {
		expresion.aceptaVisitante(v);
	}

	
	private Expresion expresion;
}
