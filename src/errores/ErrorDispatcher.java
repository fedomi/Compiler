package errores;

import lexico.JLex.UnidadLexica;

/*
 * Gestiona los errores y se encarga de mostrarlos.
 */
public class ErrorDispatcher {
	private ErrorDispatcher(){}
	
	
	public static ErrorDispatcher getInstance(){
		if (instance == null)
			instance = new ErrorDispatcher();
		return instance;
	}
	
	//Lanza un error de tipos.
	public void sendErrorTipos(String msg, int fila){
		error = true;
		System.err.println("[" + module + "]" + 
				" Error en fase de comprobaci�n de tipos (fila " +
				fila +"): " + msg);
	}
	
	//Lanza un error de identificadores.
	public void sendErrorIdentificacion(String msg, int fila){
		error = true;
		System.err.println("[" + module + "]" + 
				" Error en fase de identificaci�n "
				+ "de identificadores (fila " + fila +"): " + msg);
	}
	
	// Lanza un error de compilaci�n.
	public void sendErrorCompilacion(String msg){
		error = true;
		System.err.println("[" + module + "]" + 
				" Error en fase de compilaci�n: " + msg);
	}
	
	/* Lanza un error l�xico */
	public void sendLexicalError(int fila, String lexema){
		System.err.println("[" + module + "]" + 
				" Error l�xico en la fase de parseado (fila  "+
				fila +") - Caracter inexperado: "+lexema);
		System.out.println("No se pudo completar la compilaci�n, hay errores.");
		System.exit(8);
	}


	//Lanza los errores para el cup.
	
	public void sendSyntaxError (UnidadLexica unidadLexica){
		System.err.println("[" + module + "]" + 
				" Error sint�ctico en la fase de parseado (fila  " + 
				unidadLexica.fila() +") - Elemento inexperado: "+
				unidadLexica.lexema());
		System.out.println("No se pudo completar la compilaci�n, hay errores.");
		System.exit(9);
	}
	
	//Permite comprobar si existen errores en lo que va de compilaci�n.
	public boolean error(){
		return error;
	}

	//Carga el m�dulo que se est� analizando para que los errores lo referencien.
	public void setModule(String module) {
		this.module = module;
	}
	
	private String module = "main";
	private boolean error = false;
	private static ErrorDispatcher instance;
}
