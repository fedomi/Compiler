package arbol;

import identificadores.Visitor;

import java.util.ArrayList;
import codigo.CodeWriter;
import codigo.Entorno;
import codigo.ExcepcionWriter;


public class Bloque extends Nodo{

	public Bloque(ArrayList<Sentencia> sentencias) {
		this.sentencias = sentencias;
	}
	
	//Resolvemos el tipo individualmente para todas las sentencias del bloque.
	public void resuelveTipo(){
		for( Sentencia s: sentencias)
			s.resuelveTipo();
	}
	//Cada sentencia resuelve su propio c�digo
	public void code(Entorno env, CodeWriter writer) throws ExcepcionWriter {
		for (Sentencia i: sentencias)
			i.code(env, writer);
	}
	
	@Override
	public void aceptaVisitante(Visitor v) {
		for (Sentencia i: sentencias)
			i.aceptaVisitante(v);
	}	
	
	//Calcula el tama�o m�ximo que pueda necesitar la pila, cada instrucci�n
	// resuelve la suya y se queda con el m�ximo.

	public int evStackLength() {
		int m = 0;
		for (Sentencia i: sentencias)
			m = Math.max(m, i.evStackLength());
		return m;
	}
	
	//Permite obtener expl�citamente el array de sentencias
	public ArrayList<Sentencia> getSentencias(){
		return sentencias;
	}
	
	//Calcula las declaraciones que se van a realizar y el tama�o que ocupar�n.
	public int staticZoneLength(){
		int l = 0;
		
		for (Sentencia d: sentencias)
			if (d.isDeclaracion)
				l += ((Declaracion) d).getTipo().tam();
		
		return l;
	}
	
	
	// Un bloque sencillamente encapsula una lista de sentencias
	private ArrayList<Sentencia> sentencias;



}
