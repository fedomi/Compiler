package arbol;

import java.util.ArrayList;
import java.util.Stack;

import codigo.*;

import errores.ErrorDispatcher;
import errores.GestionErroresAnalisis;

import tipos.TipoBasico;

import identificadores.Visitor;

/* Es el típico switch de java y c, solo que sin los break que se 
 * se entienden implícitos en el case. Incluye el defaul 
 */
public class Switch extends Sentencia {

	public Switch(Variable v, ArrayList<CaseBlock> casos, Default def) {
		this.variable = v;
		this.casos = casos;
		this.def = def;
	}


	
	@Override
	public void aceptaVisitante(Visitor v) {
		variable.aceptaVisitante(v);
		for (Case c: casos)
			c.aceptaVisitante(v);
	}
	//Debemos asegurar que se usa un entero en el switch. Además se llama recursivamente para cada bloque
	@Override
	public void resuelveTipo() {
		if (!variable.getTipo().equals(TipoBasico.INT))
			GestionErroresAnalisis.getInstance().errorTipos("La variable en el switch debe ser de tipo entero.", getFila());
		for (Case c: casos)
			c.compruebaTipos();
	}

	@Override
	public void code(Entorno env, CodeWriter writer) throws ExcepcionWriter {
		CodeWriter.Etiqueta l,s = writer.reservaEtiqueta();
		CodeWriter.Etiqueta d = writer.reservaEtiqueta();
		CodeWriter.Etiqueta q = writer.reservaEtiqueta();
		CodeWriter.Etiqueta qq = writer.reservaEtiqueta();
		variable.codeR(env, writer);
		//Gestión inicial de switch 
		for (int i=0;i<casos.size();i++){
			l = writer.reservaEtiqueta();
			writer.write("dpl");
			writer.write("ldc " + casos.get(i).getValor());
			writer.write("equ");
			writer.write(new InstructionWaiting("fjp", l));
			writer.write("sli");
			writer.write("ldc " + (i+1));
			writer.write(new InstructionWaiting("ujp", s));
			writer.marca(l);
		}
		//Inicialización del default
		writer.write("sli");
		writer.write("ldc 0");
		//Cargamos el código inicial del switch de la máquina p
		writer.marca(s);
		writer.write("neg");
		writer.write(new InstructionWaiting("ixj", q));
		
		//Marcamos el default
		writer.marca(d);
		if (def != null) def.code(env, writer);
		writer.write(new InstructionWaiting("ujp", qq));
		
		//Resolvemos todos los bloques y marcamos para cada uno
		Stack<CodeWriter.Etiqueta> etiquetas = new Stack<>();
		for (Case c : casos) {
			etiquetas.push(writer.reservaEtiqueta());
			writer.marca(etiquetas.peek());
			c.code(env,writer);
			writer.write(new InstructionWaiting("ujp", qq));
		}
		//Rellenamos todos los saltos con el código de la máquina p.
		while (!etiquetas.empty())
			writer.write(new InstructionWaiting("ujp",etiquetas.pop()));
		//Terminamos con el salto del default.
		writer.marca(q);
		writer.write(new InstructionWaiting("ujp",d));
		writer.marca(qq);
	}

	@Override
	public int evStackLength() {
		EnteroReferencia acc = new EnteroReferencia(0), max = new EnteroReferencia(0);
		variable.maxStack(acc, max);
		for (Case c: casos)
			max.i = Math.max(max.i, c.evStackLength());
		
		return max.i;
	}
	
	private Variable variable;
	private ArrayList<CaseBlock> casos;
	private Default def;
	
}
