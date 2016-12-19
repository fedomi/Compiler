package identificadores;

import errores.ExcepcionCode;


//Excepción para los identificadores.
public class ExcepcionIdentificador extends ExcepcionCode {

	public ExcepcionIdentificador(String msg) {
		super(msg);
	}
	
	public ExcepcionIdentificador(String msg, int fila) {
		super(msg,fila);
	}
	
	private static final long serialVersionUID = 1L;

}
