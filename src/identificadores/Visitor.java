package identificadores;

public abstract class Visitor {
	//Implementaci�n del patr�n visitante.
	
	//Entrada en un nuevo bloque
	public abstract void enterBlock ();
	
	//Salida del bloque actual
	public abstract void exitBlock ();
	
	//Se visita un s�mbolo 
	public abstract void visitSymbol (Symbol e);
	
	//Se visita un s�mbolo mediante un id
	public abstract Symbol visitId (String id) throws ExcepcionIdentificador;
}
