package arbol;

import tipos.TipoBasico;
import errores.ErrorDispatcher;
import errores.GestionErroresAnalisis;

public class CaseBlock extends Case{

	// Es el auténtico bloque del case. Incluye el propio entero que le corresponde.
	public CaseBlock(Entero ent, Bloque b) {
		super(b);
		this.caso = ent;
	}
	

	public int getValor (){
		return caso.getEntero();
	}
	//Resuelve los tipos delegando en el bloque
	@Override
	public void compruebaTipos() {
		super.compruebaTipos();
		if (!caso.getTipo().equals(TipoBasico.INT))
			GestionErroresAnalisis.getInstance().errorTipos("Solo permitimos enteros en las expresiones de un switch", getFila());
	}
	
	private Entero caso;
}
