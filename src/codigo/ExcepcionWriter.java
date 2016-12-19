package codigo;

import errores.ExcepcionCode;

//La excepción para gestionar el codewriter

public class ExcepcionWriter extends ExcepcionCode{
	public ExcepcionWriter(String msg) {
		super(msg);
	}
	
	public ExcepcionWriter (String msg, Throwable cause){
		super(msg,cause);
	}
	
	private static final long serialVersionUID = 6636049675637340104L;
}
