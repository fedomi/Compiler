package arbol;

import java.util.ArrayList;

import tipos.Clases;
import tipos.TipoBasico;

import identificadores.ExcepcionIdentificador;
import identificadores.Symbol;
import identificadores.Visitor;
import codigo.CodeWriter;
import codigo.Entorno;
import codigo.ExcepcionWriter;
import codigo.EnteroReferencia;
import errores.ErrorDispatcher;
import errores.GestionErroresAnalisis;


public class Llamada extends Sentencia {
	//Representa la llamada a una función o un procedimiento.
	
	public Llamada(String nombre, ArrayList<Expresion> argumentos) {
		this.nombre = nombre;
		this.argumentos = argumentos;
	}
	

	//Constructor para una función de la que se quiere guardar el resultado.
	
	public Llamada(String nombre, ArrayList<Expresion> argumentos, Variable var) {
		this.nombre = nombre;
		this.argumentos = argumentos;
		this.var = var;
	}
	

	
	@Override
	public void resuelveTipo() {
		//comprobamos que sea una función el símbolo correspondiente al id
		if (sim.compilaTipo().clase() != Clases.Funcion)
			GestionErroresAnalisis.getInstance().errorTipos("Se esperaba una función.", getFila());
		
		//Nos aseguramos de que concuerden el número de parámetros de la llamada y los esperados.
		ArrayList<Declaracion> parametros = sim.elementos();
		if (parametros.size() != argumentos.size())
			GestionErroresAnalisis.getInstance().errorTipos("Se esperaba un número diferente de parámetros.", getFila());
		
		//Comprobamos que además sean del tipo que deben.
		for (int i=0; i<parametros.size(); i++)
			if (!(parametros.get(i).compilaTipo().equals(argumentos.get(i).getTipo())))
				GestionErroresAnalisis.getInstance().errorTipos("Los tipos de los argumentos de la función no coinciden.", getFila());
		//En caso de querer guardar en una variable el valor devuelto deben tener el mismo tipo.
		if (var != null && !var.getTipo().equals(((Funcion)sim).getTipoDevuelto()))
			GestionErroresAnalisis.getInstance().errorTipos("La asignación en el call tiene que ser del mismo tipo que el que devuelve la función", getFila());
		
	}

	@Override
	public void code(Entorno env, CodeWriter writer) throws ExcepcionWriter {
		//Cargamos la dirección de la variable si existe (luego haremos el store).
		if (var != null) var.codeL(env, writer);
		//Código de llamada en la máquina p a una función
		writer.write("mst " + (env.level() - env.def(nombre)));
		for (Expresion e: argumentos)
			e.codeA(env, writer);
		writer.write("cup " + argumentos.size() + " " + env.pos(nombre));
		if (var != null) writer.write("sto");
		else if (!((Funcion)sim).getTipoDevuelto().equals(TipoBasico.VOID))
			writer.write("sli");
	}

	@Override
	public int evStackLength() {
		EnteroReferencia acc = new EnteroReferencia(0),max = new EnteroReferencia(0);
		if (var != null) var.maxStack(acc, max);
		acc.i += 5;
		max.i = Math.max(acc.i, max.i);
		for (Expresion e: argumentos)
			e.maxStack(acc, max);
		
		return max.i;
	}

	@Override
	public void aceptaVisitante(Visitor v) {
		try {
			sim = v.visitId(nombre);
		} catch (ExcepcionIdentificador e1) {
			GestionErroresAnalisis.getInstance().errorIdentificacion(e1.getMessage(), getFila());
		}
		for (Expresion e:argumentos)
			e.aceptaVisitante(v);
		if (var != null) var.aceptaVisitante(v);
		
	}
	
	
	private String nombre;
	private ArrayList<Expresion> argumentos;
	private Variable var;
	private Symbol sim;
	
}
