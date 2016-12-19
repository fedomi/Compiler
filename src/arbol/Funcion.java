package arbol;

import identificadores.Visitor;

import java.util.ArrayList;
import tipos.Tipo;
import tipos.TipoBasico;
import tipos.TipoFuncion;

import codigo.CodeWriter;
import codigo.ContextType;
import codigo.Entorno;
import codigo.ExcepcionWriter;
import codigo.InstructionWaiting;


public class Funcion extends Declaracion {

	public Funcion(String nombre, Declaraciones argumentos, Tipo tipo, Bloque bloque) {
		super( nombre, new TipoFuncion(argumentos, tipo));
		this.tipoFuncion = tipo;
		this.argumentos = argumentos;
		this.bloque = bloque;
	}
	
	public void resuelveTipos() {
		bloque.resuelveTipo();
	}
	
	public Tipo getTipoDevuelto(){
		return this.tipoFuncion;
	}
	
	/**
	 * Computes the static zone length.
	 */
	private int staticZoneLength(){
		int l = 5; //Parte organizativa
		ArrayList<Declaracion> declaraciones = argumentos.getDeclaraciones();
		for (Declaracion d: declaraciones) l += d.getTipo().tam();
		
		return l;
	}

	public int evStackLength (){
		return bloque.evStackLength();
	}
	
	public void code (Entorno env, CodeWriter writer) throws ExcepcionWriter{
		env.asignarFuncion(id, writer.pos());
		env = env.newEnv();
		// Se realiza la identificación de la función
		if (tipo.equals(TipoBasico.VOID)) env.contextType(ContextType.Procedure);
		else env.contextType(ContextType.Function);
		//Secuencia de entrada a la función
		writer.write("ssp " + staticZoneLength());
		writer.write("sep " + evStackLength());
		CodeWriter.Etiqueta dp = writer.reservaEtiqueta();
		writer.write(new InstructionWaiting("ujp",dp));
		//Declaración de los argumentos
		for (Declaracion d: argumentos.getDeclaraciones()) d.code(env, writer);
		argumentos.code(env, writer);
		//Bloque de la función
		writer.marca(dp);
		bloque.code(env, writer);
		writer.write("retp");
	}
	
	public void aceptaVisitante(Visitor v) {
		v.visitSymbol(this);
		v.enterBlock();
		
		argumentos.aceptaVisitante(v);
		bloque.aceptaVisitante(v);
		
		v.exitBlock();
	}
	
	@Override
	public ArrayList<Declaracion> elementos(){
		return this.argumentos.getDeclaraciones();
	}
	
	
	private Declaraciones argumentos;
	private Bloque bloque;
	private Tipo tipoFuncion;
	
	
}
