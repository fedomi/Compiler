package identificadores;

import java.util.*;

/*
 * FERNANDO DOMINGUEZ ESTEVEZ - ENRIQUE BALLESTEROS HORCAJO
 * 4º CURSO - Doble Grado Informatica y Matematicas - Procesadores del lenguaje (PLEG)
 * 
 * Esta clase implementa la table de símbolos necesaria para las fases de identificación de identificadores y tipado, 
 * y para la de generación de código. Dicha table consta de una pila de bloques, en la que cada bloque tiene a su vez
 * una table hash que relaciona los identificadores y su símbolo correspondiente. Un símbolo es una estructura que almacena
 * el id, el numero de bloque, y el nodo del arbol abstracto que le corresponde.
 * 
 * */

public class Symbol_table {


	public Symbol_table(){
		list = new Vector<Block>();
		table = new HashMap<String, Stack<Block>>();
		enterBlock();
	}
	
	//Entrada a un nuevo bloque
	public void enterBlock(){
		Block Block = new Block();
		list.add(Block);
	}
	
	//Salida del bloque actual.
	public void exitBlock(){
		Block Block = list.lastElement();
		Block aux = new Block();
		list.remove(list.size()-1);
		while(Block.getPuntero() != null){	
			aux = table.get(Block.getPuntero()).pop();
			Block = aux;
		}
	}

	//Añadir un nuevo identificador a la tabla.
	public void insert(String id, Symbol Symbol){
		Block Block = new Block();
		if (table.containsKey(id)){
			Block aux = list.lastElement();
			while(aux.getPuntero()!=null){
				aux = table.get(aux.getPuntero()).peek();
			}
			Block.setSymbol(Symbol);
			aux.setPuntero(id);
			table.get(id).push(Block);
		}
		else{
			Stack<Block> st = new Stack<Block>();
			Block.setSymbol(Symbol);
			list.lastElement().setPuntero(id);
			st.add(Block);
			table.put(id, st);
		}
	}
	
	//Devuelve el símbolo que tiene la id buscada.
	Symbol searchId(String id) throws ExcepcionIdentificador{
		Symbol symbol = null;
		if(table.containsKey(id))
			symbol = table.get(id).peek().getSymbol();
		else throw new ExcepcionIdentificador("No existe el identificador");
		return symbol;
	}
	
	private HashMap<String, Stack<Block>> table;
	private Vector<Block> list;
}


 