package arbol;

import identificadores.Visitor;
import tipos.Tipo;
import tipos.TipoBasico;
import codigo.CodeWriter;
import codigo.Entorno;
import codigo.ExcepcionWriter;
import codigo.EnteroReferencia;

public class Booleano extends Expresion{

	
	public Booleano (boolean val){
		this.valor = val;
	}
	
	public boolean getValor(){
		return valor;
	}
	
	
	@Override
	protected Tipo calculaTipo() {
		//Sencillamente devolvemos el tipo bool.
		return TipoBasico.BOOL;
	}

	@Override
	public void codeR(Entorno env, CodeWriter writer) throws ExcepcionWriter {
		//En funcion del valor que tenga debe escribir uno u otro.
		//Nos aprovechamos de la funcionalidad de la operación "?" para usarlo.
		String val = (valor) ? "true" : "false";
		writer.write("ldc " + val);
		
	}

	@Override
	protected void maxStack(EnteroReferencia acc, EnteroReferencia max) {
		acc.i++;
		max.i = Math.max(acc.i, max.i);
	}

	@Override
	public void aceptaVisitante(Visitor v) {
		//El tipo no tiene que hacer nada.
	}

	
	private boolean valor;
}
