package arbol;

import identificadores.ExcepcionIdentificador;
import identificadores.Symbol;
import identificadores.Visitor;
import tipos.Tipo;
import codigo.CodeWriter;
import codigo.Entorno;
import codigo.ExcepcionWriter;
import codigo.EnteroReferencia;
import errores.ErrorDispatcher;
import errores.GestionErroresAnalisis;

public class Identificador extends Variable{
	
	//Gestión de los identificadores del árbol abstracto.
	
	public Identificador(String id) {
		this.iden = id;
	}
	
	public String getId(){
		return iden;
	}

	
	@Override
	public void aceptaVisitante(Visitor v){
		try {
			ref = v.visitId(iden);
		} catch (ExcepcionIdentificador e) {
			GestionErroresAnalisis.getInstance().errorIdentificacion(e.getMessage(), getFila());
		}
	}

	@Override
	protected Tipo calculaTipo() {
		return ref.compilaTipo();
	}

	public void codeL (Entorno env, CodeWriter writer) throws ExcepcionWriter{
		if (!env.ref(iden))
			writer.write(("lda " + (env.level() - env.def(iden))) + " " + env.pos(iden));
		else
			writer.write(("lod " + (env.level() - env.def(iden))) + " " + env.pos(iden));
	}

	@Override
	protected void maxStack(EnteroReferencia acc, EnteroReferencia max) {
		acc.i++;
		max.i = Math.max(acc.i, max.i);
	}

	private String iden;
	private Symbol ref;


}
