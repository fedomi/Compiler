package arbol;

import tipos.TipoBasico;
import identificadores.Visitor;
import codigo.CodeWriter;
import codigo.Entorno;
import codigo.ExcepcionWriter;
import codigo.InstructionWaiting;
import codigo.EnteroReferencia;
import errores.ErrorDispatcher;
import errores.GestionErroresAnalisis;


public class IfElse extends Sentencia {
	
	public IfElse(Expresion expresion, Bloque bloque) {
		this.expresion = expresion;
		this.bloqueT = bloque;
	}

	public IfElse(Expresion expresion, Bloque bloqueIf, Bloque bloqueElse) {
		this.expresion = expresion;
		this.bloqueT = bloqueIf;
		this.bloqueF = bloqueElse;
		
	}
	
	@Override
	public void resuelveTipo() {
		//Solo nos aseguramos de que sea un tipo bool al expresión.
		if (!expresion.getTipo().equals(TipoBasico.BOOL))
			GestionErroresAnalisis.getInstance().errorTipos("La expresión en un if debe ser de tipo booleano", getFila());
		
		bloqueT.resuelveTipo();
		if (bloqueF != null) bloqueF.resuelveTipo();
		
	}

	@Override
	public int evStackLength() {
		EnteroReferencia acc = new EnteroReferencia(0), max = new EnteroReferencia(0);
		expresion.maxStack(acc, max);
		max.i = Math.max(max.i, bloqueT.evStackLength());
		if (bloqueF != null) max.i = Math.max(max.i, bloqueF.evStackLength());
		
		return max.i;
	}
	
	//Se delega como siempre
	@Override
	public void aceptaVisitante(Visitor v) {
		expresion.aceptaVisitante(v);
		bloqueT.aceptaVisitante(v);
		if (bloqueF != null) bloqueF.aceptaVisitante(v);
	}
	
	//Vamos marcando líneas y actualizando como siempre
	//En función de si hay else o no, repetimos el proceso dos veces
	public void code(Entorno env, CodeWriter writer) throws ExcepcionWriter {
		CodeWriter.Etiqueta l = writer.reservaEtiqueta();
		expresion.codeR(env, writer);
		writer.write(new InstructionWaiting("fjp",l));
		bloqueT.code(env, writer);
		if (bloqueF == null) writer.marca(l);
		else {
			CodeWriter.Etiqueta m = writer.reservaEtiqueta();
			writer.write(new InstructionWaiting("ujp",m));
			writer.marca(l);
			bloqueF.code(env, writer);
			writer.marca(m);
		}
		
	}
	
	private Expresion expresion;
	private Bloque bloqueT;
	private Bloque bloqueF;
	
	
}
