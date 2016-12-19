package arbol;

import identificadores.Visitor;
import tipos.Tipo;
import tipos.TipoArray;
import codigo.CodeWriter;
import codigo.Entorno;
import codigo.ExcepcionWriter;
import codigo.EnteroReferencia;

public class Array extends Variable {
	
	//Representa un array, no su declaración. El acceso al array 
	// puede ser mediante una expresión que se calcule en ejecución.
	
	public Array(String id, Expresion exp) {
		this.id = new Identificador(id);
		this.exp = exp;
	}
	
	//Devuelve el tipo de los elementos del array.
	@Override
	public Tipo calculaTipo(){
		return tipo;
	}
	
	@Override
	public void codeL(Entorno env, CodeWriter writer) throws ExcepcionWriter {
		//Resuelves el code del identificador y a continuación el de la expresión.
		id.codeL(env, writer);
		exp.codeR(env, writer);
		writer.write("ixa " + id.getTipo().desempaquetar(null).tam());
		writer.write("dec 0");
	}

	
	@Override
	protected void maxStack(EnteroReferencia acc, EnteroReferencia max) {
		id.maxStack(acc, max);
		exp.maxStack(acc, max);
		acc.i--;
		
	}

	@Override
	public void aceptaVisitante(Visitor v) {
		id.aceptaVisitante(v);
		exp.aceptaVisitante(v);
	}
	
	// Attributes
	private TipoArray tipo;
	private Identificador id;
	private Expresion exp;
}
