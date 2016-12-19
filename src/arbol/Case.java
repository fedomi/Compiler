package arbol;
import codigo.CodeWriter;
import codigo.Entorno;
import codigo.ExcepcionWriter;
import identificadores.Visitor;

//Es uno de los bloques que aparecen en los case. Encapsula un bloque com�n.
//Por tanto no tiene funcionalidad, solo facilitar la lectura del cup.
//En todas las operacines se delega en el bloque com�n.
public abstract class Case extends Nodo{
	public Case(Bloque b){
		this.bloque = b;
	}
	
	
	@Override
	public void aceptaVisitante(Visitor v) {
		bloque.aceptaVisitante(v);
	}

	public void compruebaTipos (){
		bloque.resuelveTipo();
	}
	
	public void code (Entorno env, CodeWriter writer) throws ExcepcionWriter{
		bloque.code(env, writer);
	}
	

	public int evStackLength(){
		return bloque.evStackLength();
	}
	
	private Bloque bloque;
}
