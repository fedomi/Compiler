package tipos;



import identificadores.IdVisitor;

import java.util.ArrayList;

import arbol.Declaracion;
import arbol.Declaraciones;
/**
 * Represents the function type.
 *
 */
public class TipoFuncion extends Tipo{
	
	public TipoFuncion(Declaraciones argumentos, Tipo devuelto) {
		
		this.argumentos = argumentos;
		this.tdevuelto = devuelto;
				
		//Tenemos además una lista de los tipos de los argumentos
		ArrayList<Tipo> t = new ArrayList<Tipo>();
		ArrayList<Declaracion> dec = argumentos.getDeclaraciones();
		for( Declaracion d: dec)
				t.add(d.getTipo());
	}
	
	/**
	 * The size of the type.
	 */
	@Override
	public int tam() {
		return 1;
	}
	/**
	 * The class of the type.
	 */
	@Override
	public Clases clase() {
		return Clases.Funcion;
	}
	/**
	 * Returns the subtype: the returning type.
	 */
	@Override
	public Tipo desempaquetar(String s) {
		return tdevuelto;
	}
	
	@Override
	public boolean equals(Object obj) {
		//TODO mejorar poniendo un isFuncion en tipo
		boolean eq = true;
		if (obj instanceof TipoFuncion){
			TipoFuncion tmp = (TipoFuncion) obj;
			eq &= tmp.tdevuelto.equals(tdevuelto);
			if (tipoArgumentos.size() != tmp.tipoArgumentos.size()) eq = false;
			for (int i = 0; i<tipoArgumentos.size();i++)
				eq &= tipoArgumentos.get(i).equals(tmp.tipoArgumentos.get(i));
		}
		else eq = false;
		
		return eq;
	}

	public void aceptaVisitante(IdVisitor v) {}
	
	//Para comodidad, tenemos los tipo de cada uno de los argumentos.
	private ArrayList<Tipo> tipoArgumentos;
	private Declaraciones argumentos;
	private Tipo tdevuelto;

}
