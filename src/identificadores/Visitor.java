package identificadores;

public abstract class Visitor {
	//Implementación del patrón visitante.
	
	//Entrada en un nuevo bloque
	public abstract void enterBlock ();
	
	//Salida del bloque actual
	public abstract void exitBlock ();
	
	//Se visita un símbolo 
	public abstract void visitSymbol (Symbol e);
	
	//Se visita un símbolo mediante un id
	public abstract Symbol visitId (String id) throws ExcepcionIdentificador;
}
