package errores;


//Excepción general del código. 
public abstract class ExcepcionCode extends Exception {
	public ExcepcionCode (String msg){
		super(msg);
	}
	
	public ExcepcionCode (String msg, int fila){
		super(msg);
		this.fila = fila;
	}
	
	public ExcepcionCode (String msg, Throwable cause){
		super(msg,cause);
	}
	
	public int getFila (){
		return fila;
	}
	
	private int fila = -1;
	
	private static final long serialVersionUID = -5248683097223713723L;
}
