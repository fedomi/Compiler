package codigo;

import java.util.TreeMap;

// Guarda el identificador del bloque, su posición y su nivel de definición.
 
public class Entorno {
	
	//Crea un entorno nuevo
	
	@SuppressWarnings("unchecked")
	public Entorno newEnv (){
		Entorno env = new Entorno();
		env.n = n+1;
		env.map = (TreeMap<String, Info>) map.clone();
		
		return env;
	}
	
	//Nos da el nivel en el que está el entorno.
	
	public int level (){
		return this.n+1;
	}

	//Asigna memoria para el nuevo identificador.
	public void asignar (String identificador, int tam){
		map.put(identificador, new Info(cur, n+1,false));
		cur += tam;
	}
	
	//Función que permite además incluir si es un argumento de función 
	// pasado por referencia.
	public void asignar (String identificador, int tam, boolean ref){
		if (ref) tam = 1;
		map.put(identificador, new Info(cur, n+1,ref));
		cur += tam;
	}
	
	// Es un set en memoria del identificador
	public void asignarAbs (String identificador, int mem){
		map.put(identificador, new Info(mem, n+1,false));
	}
	
	//Le asigna memoria a una función.
	public void asignarFuncion (String identificador, int pos){
		map.put(identificador, new Info(pos, n+1,false));
	}
	
	//Nos da la referencia para un id
	
	public int pos (String identificador){
		return map.get(identificador).pos;
	}
	
	//Nos da el nivel al que se encuentra el id.
	
	public int def (String identificador){
		return map.get(identificador).def;
	}
	/**
	 * True if the identifier is an argument of a function provided
	 * by reference.
	 * @param identificador
	 * @return
	 */
	public boolean ref (String identificador){
		return map.get(identificador).ref;
	}
	/**
	 * Sets the context type of the block associated with this environment.
	 * @param type
	 */
	public void contextType (ContextType type){
		this.contextType = type;
	}
	/**
	 * Returns the context type of the block associated with this environment.
	 */
	public ContextType contextType (){
		return contextType;
	}
	
	private int n = 0, cur = 5;
	private ContextType contextType = ContextType.Procedure;
	private TreeMap<String, Info> map = new TreeMap<String,Info>();
	/**
	 * Info (position and definition level for an identifier)
	 *
	 */
	private class Info {
		public Info(int pos, int def, boolean ref) {
			this.pos = pos;
			this.def = def;
			this.ref = ref;
		}
		public int pos;
		public int def;
		public boolean ref;
	}
}
