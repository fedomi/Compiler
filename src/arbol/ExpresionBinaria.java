package arbol;

import identificadores.Visitor;
import codigo.CodeWriter;
import codigo.Entorno;
import codigo.ExcepcionWriter;
import codigo.EnteroReferencia;
import tipos.Tipo;

public abstract class ExpresionBinaria extends Expresion {

	public ExpresionBinaria(Expresion expIzq, Expresion expDcha) {	
		this.expIzq = expIzq;
		this.expDcha = expDcha;
	}
	
	//Según el tipo de expresión binaria llevará un resuelve tipo distinto
	//Poniendo la clase abstracta obligamos a que cada una la implemente
	public abstract Tipo calculaTipo();
		
	
	public Expresion get_izq() {
		return expIzq;
	}

	public Expresion get_dcha() {
		return this.expDcha;
	}


	@Override
	public void aceptaVisitante(Visitor v) {
		this.expIzq.aceptaVisitante(v);
		this.expDcha.aceptaVisitante(v);
	}

	@Override
	public void codeR(Entorno env, CodeWriter writer) throws ExcepcionWriter {
		get_izq().codeR(env, writer);
		get_dcha().codeR(env, writer);
		writer.write(getInst());
	}
	
	protected abstract String getInst();

	@Override
	protected void maxStack(EnteroReferencia acc, EnteroReferencia max) {
		get_izq().maxStack(acc, max);
		get_dcha().maxStack(acc, max);
		acc.i--;
	}
	
	
	protected Expresion expIzq;
	protected Expresion expDcha;
	
}
