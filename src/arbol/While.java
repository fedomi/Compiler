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

public class While extends Sentencia {
	//Formado por una expresión booleana y un bloque, 
	// las operaciones se delegan en las clases de ambos.
	
	public While(Expresion condicion, Bloque bloque) {
		this.condicion = condicion;
		this.bloque = bloque;
	}

	//Aseguramos que sea booleana la condicion de parada del bucle.
	
	public void resuelveTipo(){
		if (!condicion.getTipo().equals(TipoBasico.BOOL))
			GestionErroresAnalisis.getInstance().errorTipos("La condición de un while debe ser de tipo booleano.", getFila());
		bloque.resuelveTipo();
	}
	@Override
	public void code(Entorno env, CodeWriter writer) throws ExcepcionWriter {
		CodeWriter.Etiqueta l1 = writer.reservaEtiqueta();
		CodeWriter.Etiqueta l2 = writer.reservaEtiqueta();
		writer.marca(l1);
		condicion.codeR(env, writer);
		writer.write(new InstructionWaiting("fjp",l2));
		bloque.code(env, writer);
		writer.write(new InstructionWaiting("ujp",l1));
		writer.marca(l2);
	}

	@Override
	public int evStackLength() {
		EnteroReferencia acc = new EnteroReferencia(0), max = new EnteroReferencia(0);
		condicion.maxStack(acc, max);
		max.i = Math.max(max.i, bloque.evStackLength());
		
		return max.i;
	}

	@Override
	public void aceptaVisitante(Visitor v) {
		condicion.aceptaVisitante(v);
		bloque.aceptaVisitante(v);
		
	}
	
	private Expresion condicion;
	private Bloque bloque;
}
