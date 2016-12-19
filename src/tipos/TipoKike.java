package tipos;
//Tenemos o expresion tipo array o tipo básico, etc. Puede ponerse aparte.

public abstract class TipoKike {
	
	
	//Para las visitas del arbol.
	public abstract void visita();
	
	//Según el tipo tendrá un tamaño distinto
	public abstract int tam();
	
	//Devuelve el tipo concreto
	public abstract String tipo();
}
