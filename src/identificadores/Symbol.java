package identificadores;

import java.util.ArrayList;

import arbol.Declaracion;

import tipos.Tipo;

public interface Symbol{

	//Devuelve el identificador
	
	public String getIdentificador();

	//Devuelve los elementos
	
	public ArrayList<Declaracion> elementos();
	
	//Genera el tipo del símbolo
	
	public Tipo compilaTipo ();
}
