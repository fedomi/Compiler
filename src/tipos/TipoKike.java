package tipos;
//Tenemos o expresion tipo array o tipo b�sico, etc. Puede ponerse aparte.

public abstract class TipoKike {
	
	
	//Para las visitas del arbol.
	public abstract void visita();
	
	//Seg�n el tipo tendr� un tama�o distinto
	public abstract int tam();
	
	//Devuelve el tipo concreto
	public abstract String tipo();
}
