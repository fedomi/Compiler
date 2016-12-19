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
				" Error en fase de comprobación de tipos (fila " +
				fila +"): " + msg);
	}
	
	//Lanza un error de identificadores.
	public void sendErrorIdentificacion(String msg, int fila){
		error = true;
		System.err.println("[" + module + "]" + 
				" Error en fase de identificación "
				+ "de identificadores (fila " + fila +"): " + msg);
	}
	
	// Lanza un error de compilación.
	public void sendErrorCompilacion(String msg){
		error = true;
		System.err.println("[" + module + "]" + 
				" Error en fase de compilación: " + msg);
	}
	
	/* Lanza un error léxico */
	public void sendLexicalError(int fila, String lexema){
		System.err.println("[" + module + "]" + 
				" Error léxico en la fase de parseado (fila  "+
				fila +") - Caracter inexperado: "+lexema);
		System.out.println("No se pudo completar la compilación, hay errores.");
		System.exit(8);
	}


	//Lanza los errores para el cup.
	
	public void sendSyntaxError (UnidadLexica unidadLexica){
		System.err.println("[" + module + "]" + 
				" Error sintáctico en la fase de parseado (fila  " + 
				unidadLexica.fila() +") - Elemento inexperado: "+
				unidadLexica.lexema());
		System.out.println("No se pudo completar la compilación, hay errores.");
		System.exit(9);
	}
	
	//Permite comprobar si existen errores en lo que va de compilación.
	public boolean error(){
		return error;
	}

	//Carga el módulo que se está analizando para que los errores lo referencien.
	public void setModule(String module) {
		this.module = module;
	}
	
	private String module = "main";
	private boolean error = false;
	private static ErrorDispatcher instance;
}
