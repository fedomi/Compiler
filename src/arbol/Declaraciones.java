package arbol;

import identificadores.Visitor;

import java.util.*;

import codigo.CodeWriter;
import codigo.Entorno;
import codigo.ExcepcionWriter;

public class Declaraciones extends Nodo {
		// Una lista de declaraciones. Delega todo individualmente
	
	
	public Declaraciones( ArrayList<Declaracion> declaraciones ){
		this.declaraciones = declaraciones;
	
	}
	
	public ArrayList<Declaracion> getDeclaraciones(){
		return declaraciones;
	}
	
	@Override
	public void aceptaVisitante(Visitor v) {
		for(Declaracion d : declaraciones)
			d.aceptaVisitante(v);
		
	}
	
	public void code(Entorno env, CodeWriter writer) throws ExcepcionWriter {
		for(Declaracion d: declaraciones) d.code(env, writer);
	}
	
	private ArrayList<Declaracion> declaraciones;

	
}
