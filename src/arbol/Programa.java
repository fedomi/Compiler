package arbol;

import codigo.CodeWriter;
import codigo.Entorno;
import codigo.ExcepcionWriter;
import codigo.InstructionWaiting;

import identificadores.IdVisitor;

import java.io.Writer;
import java.util.ArrayList;

public class Programa {
	

	public Programa(ArrayList<Funcion> fun, Bloque b) {

		this.funciones = fun;
		this.b = b;
		
	}
	
	public void visitaSimbolos(){
		IdVisitor visitante = new IdVisitor();
		for(Funcion f: funciones) f.aceptaVisitante(visitante);
		b.aceptaVisitante(visitante);
	}
	
	public void resuelveTipo(){
		for (Funcion f: funciones) f.resuelveTipos();
		b.resuelveTipo();
	}
	
	
	
	//Calcula la zona est�tica que ocupar� el programa
	
	private int staticZoneLength(){
		int l = 5; // Inicializacion
		return l + b.staticZoneLength();
	}
	
	/* Es la funci�n que se encarga de calcular la longitud m�xima 
	 * que necesita la pila para la m�quina p. Cada instrucci�n implementa
	 * la suya
	 */
	private int evStackLength (){
		return b.evStackLength();
	}
	
	public void compila (Writer w) throws ExcepcionWriter{
		Entorno env = new Entorno();
		CodeWriter writer = new CodeWriter(w);
		
		//Entrada inicial para la m�quina p.
		writer.write("ssp " + staticZoneLength());
		writer.write("sep " + evStackLength());
		CodeWriter.Etiqueta dp = writer.reservaEtiqueta();
		writer.write(new InstructionWaiting("ujp",dp));
		
		//Funciones declaradas inicialmente
		for (Funcion f: funciones) f.code(env, writer);
		
		//Codificaci�n del bloque Main
		writer.marca(dp);
		b.code(env, writer);
		writer.write("stp");
	}
	
	private ArrayList<Funcion> funciones;
	private Bloque b;

}
