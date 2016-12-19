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
	//Representa la llamada a una funci�n o un procedimiento.
	
	public Llamada(String nombre, ArrayList<Expresion> argumentos) {
		this.nombre = nombre;
		this.argumentos = argumentos;
	}
	

	//Constructor para una funci�n de la que se quiere guardar el resultado.
	
	public Llamada(String nombre, ArrayList<Expresion> argumentos, Variable var) {
		this.nombre = nombre;
		this.argumentos = argumentos;
		this.var = var;
	}
	

	
	@Override
	public void resuelveTipo() {
		//comprobamos que sea una funci�n el s�mbolo correspondiente al id
		if (sim.compilaTipo().clase() != Clases.Funcion)
			GestionErroresAnalisis.getInstance().errorTipos("Se esperaba una funci�n.", getFila());
		
		//Nos aseguramos de que concuerden el n�mero de par�metros de la llamada y los esperados.
		ArrayList<Declaracion> parametros = sim.elementos();
		if (parametros.size() != argumentos.size())
			GestionErroresAnalisis.getInstance().errorTipos("Se esperaba un n�mero diferente de par�metros.", getFila());
		
		//Comprobamos que adem�s sean del tipo que deben.
		for (int i=0; i<parametros.size(); i++)
			if (!(parametros.get(i).compilaTipo().equals(argumentos.get(i).getTipo())))
				GestionErroresAnalisis.getInstance().errorTipos("Los tipos de los argumentos de la funci�n no coinciden.", getFila());
		//En caso de querer guardar en una variable el valor devuelto deben tener el mismo tipo.
		if (var != null && !var.getTipo().equals(((Funcion)sim).getTipoDevuelto()))
			GestionErroresAnalisis.getInstance().errorTipos("La asignaci�n en el call tiene que ser del mismo tipo que el que devuelve la funci�n", getFila());
		
	}

	@Override
	public void code(Entorno env, CodeWriter writer) throws ExcepcionWriter {
		//Cargamos la direcci�n de la variable si existe (luego haremos el store).
		if (var != null) var.codeL(env, writer);
		//C�digo de llamada en la m�quina p a una funci�n
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
