package arbol;

import identificadores.Visitor;
import codigo.CodeWriter;
import codigo.Entorno;
import codigo.ExcepcionWriter;
import codigo.EnteroReferencia;
import errores.ErrorDispatcher;
import errores.GestionErroresAnalisis;

public class Asignacion extends Sentencia {
	//Corresponde con la modificación del valor de una variable
	public Asignacion(Variable izqAsignacion, Expresion dchaAsignacion) {
		this.izqAsignacion = izqAsignacion;
		this.dchaAsignacion = dchaAsignacion;
	}

	

	@Override
	public void resuelveTipo() {
		izqAsignacion.calculaTipo();
		dchaAsignacion.calculaTipo();
		if(!izqAsignacion.calculaTipo().equals(dchaAsignacion.calculaTipo()))
			GestionErroresAnalisis.getInstance().errorTipos("La asignacion debe tener en ambos lados el mismo tipo", getFila());
		
	}
	@Override
	public void code(Entorno env, CodeWriter writer) throws ExcepcionWriter {
		//Añade el store que guarda el valor de la expresion en la variable
		izqAsignacion.codeL(env, writer);
		dchaAsignacion.codeR(env, writer);
		writer.write("sto");
	}
	@Override
	public int evStackLength() {
		EnteroReferencia acc = new EnteroReferencia(0), max = new EnteroReferencia(0);
		izqAsignacion.maxStack(acc, max);
		dchaAsignacion.maxStack(acc, max);
		
		return max.i;
	}
	
	
	@Override
	public void aceptaVisitante(Visitor v) {
		izqAsignacion.aceptaVisitante(v);
		dchaAsignacion.aceptaVisitante(v);
		
	}
	
	
	private Variable izqAsignacion;
	private Expresion dchaAsignacion;
	
}
